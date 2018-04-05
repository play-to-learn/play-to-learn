package com.ivantha.playtolearn.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.activity.LevelCompleteActivity
import com.ivantha.playtolearn.common.FirebaseSaveHelper
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.game.MovementLogic
import com.ivantha.playtolearn.model.*
import com.ivantha.playtolearn.model.Board.Companion.COLUMN_COUNT
import com.ivantha.playtolearn.model.Board.Companion.ROW_COUNT
import com.ivantha.playtolearn.model.Tile.BoardTileState.*
import com.ivantha.playtolearn.widget.SquareFrameLayout
import kotlin.reflect.KFunction0
import kotlin.reflect.KFunction2

class TileRecyclerAdapter(var board: Board,
                          var showQuestionDialog: KFunction2<
                                  @ParameterName(name = "title") String?,
                                  @ParameterName(name = "description") String?,
                                  Unit>,
                          var updateGoldStatus: KFunction0<Unit>)
    : RecyclerView.Adapter<TileRecyclerAdapter.TileViewHolder>() {

    var viewGroup: ViewGroup? = null
    var tileList: ArrayList<Tile> = ArrayList()

    init {
        for (row in 0 until ROW_COUNT) {
            for (col in 0 until COLUMN_COUNT) {
                tileList.add(board.tileGrid[col][row])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_tile, parent, false)
        viewGroup = parent

        return TileViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tileList.size
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.state = tileList[position].boardTileState
//        holder.question = tileList[position].question
        holder.posX = tileList[position].column
        holder.posY = tileList[position].row

        when (tileList[position].boardTileState) {
            NOT_VISITED -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_not_visited0)
            VISITED -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_visited)
            CURRENT -> holder.tileFrameLayout.setBackgroundResource(R.color.colorTransparent)
            CORRECT_ANSWER -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_correct)
            WRONG_ANSWER -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_wrong)
            else -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_not_visited0)
        }

        // Add BoardTileButton if this is the current tile
        if (tileList[position].boardTileState === CURRENT) {
            var boardTileButton = BoardTileButton()
            holder.tileFrameLayout.addView(boardTileButton)
        }
    }


    inner class TileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tileFrameLayout: SquareFrameLayout = itemView.findViewById(R.id.tileFrameLayout)
        var state: Tile.BoardTileState? = null
//        var question: Question? = null

        // Position of the TileViewHolder in the grid
        var posX = 0
        var posY = 0

        init {
            // When a tile is clicked
            tileFrameLayout.setOnClickListener({
                if (board.tileGrid[posX][posY].question != null) {
                    showQuestionDialog(board.tileGrid[posX][posY].question!!.title, board.tileGrid[posX][posY].question!!.description)
                }
            })

            // When the pointer is dragged over a tile
            tileFrameLayout.setOnDragListener { v, event ->
                if (event.action == DragEvent.ACTION_DROP) {
                    var previousTile = board.tileGrid[board.currentX][board.currentY]

                    val ret = MovementLogic.verifyMove(board.currentX, board.currentY, posX, posY, previousTile.question!!, COLUMN_COUNT)
                    when (ret.first) {
                        Result.CORRECT -> {
                            previousTile.boardTileState = CORRECT_ANSWER
                            Session.saveFile!!.currentLevel.score += previousTile.question!!.correctPoints

                            processMove(ret.second!!)
                        }
                        Result.WRONG -> {
                            previousTile.boardTileState = WRONG_ANSWER
                            Session.saveFile!!.currentLevel.score += previousTile.question!!.wrongPoints

                            processMove(ret.second!!)
                        }
                        Result.INVALID -> {
                            Toast.makeText(viewGroup!!.context, "Invalid move. Better check this out!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    return@setOnDragListener true
                }

                return@setOnDragListener true
            }
        }

        private fun processMove(positions: ArrayList<Position>) {
            updateGoldStatus()

            var preX = board.currentX
            var preY = board.currentY

            // Set as current tile
            board.currentX = posX
            board.currentY = posY
            board.tileGrid[posX][posY].boardTileState = CURRENT

            // Generate random question in the tile
            board.tileGrid[posX][posY].generateQuestion()

            // Set as visited
            for (pos in positions) {
                board.tileGrid[pos.x][pos.y].boardTileState = VISITED
            }

            notifyDataSetChanged()

            FirebaseSaveHelper.saveCurrentLevel(FirebaseAuth.getInstance().currentUser!!.uid)

            if (MovementLogic.isGameOver(posX, posY, board.tileGrid[posX][posY].question!!)) {
                val intent = Intent(viewGroup!!.context,LevelCompleteActivity::class.java)
                intent.putExtra("level", Session.saveFile!!.currentLevel.id)
                intent.putExtra("score", Session.saveFile!!.currentLevel.score)
                intent.putExtra("time", Session.saveFile!!.currentLevel.elapsedTime)
                viewGroup!!.context.startActivity(intent)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class BoardTileButton : AppCompatImageButton(viewGroup!!.context) {

        init {
            this.setOnTouchListener(object : OnTouchListener {
                private var mDownX: Float = 0.toFloat()
                private var mDownY: Float = 0.toFloat()
                private val scrollThreshold = 10f
                private var isOnClick: Boolean = false

                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    when (event!!.action) {
                        MotionEvent.ACTION_DOWN -> {
                            mDownX = event.x
                            mDownY = event.y
                            isOnClick = true
                        }
                        MotionEvent.ACTION_UP -> if (isOnClick) {
                            var currentTile = board.tileGrid[board.currentX][board.currentY]
                            showQuestionDialog(currentTile.question!!.title, currentTile.question!!.description)
                        }
                        MotionEvent.ACTION_MOVE -> if (isOnClick && (Math.abs(mDownX - event.x) > scrollThreshold || Math.abs(mDownY - event.y) > scrollThreshold)) {
                            val shadowBuilder = View.DragShadowBuilder(v)
                            v!!.startDrag(null, shadowBuilder, v, 0)
                            isOnClick = false
                        }
                    }

                    return true
                }
            })

            setBackgroundResource(R.drawable.sprite)
        }
    }

}