package com.ivantha.playtolearn.adapter

import android.annotation.SuppressLint
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.common.Session.COLUMN_COUNT
import com.ivantha.playtolearn.game.MovementLogic
import com.ivantha.playtolearn.model.Board
import com.ivantha.playtolearn.model.Question
import com.ivantha.playtolearn.model.Result
import com.ivantha.playtolearn.model.Tile
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_tile, parent, false)
        viewGroup = parent

        return TileViewHolder(v)
    }

    override fun getItemCount(): Int {
        return board.tileList.size
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.state = board.tileList[position].boardTileState
        holder.question = board.tileList[position].question
        holder.posX = board.tileList[position].column
        holder.posY = board.tileList[position].row

        when (board.tileList[position].boardTileState) {
            NOT_VISITED -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_not_visited0)
            VISITED -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_visited)
            CURRENT -> holder.tileFrameLayout.setBackgroundResource(R.color.colorTransparent)
            CORRECT_ANSWER -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_correct)
            WRONG_ANSWER -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_wrong)
            else -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_not_visited0)
        }

        // Add BoardTileButton if this is the current tile
        if (board.tileList[position].boardTileState === CURRENT) {
            var boardTileButton = BoardTileButton()
            holder.tileFrameLayout.addView(boardTileButton)
        }
    }



    inner class TileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tileFrameLayout: SquareFrameLayout = itemView.findViewById(R.id.tileFrameLayout)
        var state: Tile.BoardTileState? = null
        var question: Question? = null

        // Position of the TileViewHolder in the grid
        var posX = 0
        var posY = 0

        init {
            // When a tile is clicked
            tileFrameLayout.setOnClickListener({
                if (question != null) {
                    showQuestionDialog(question!!.title, question!!.description)
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
                            Session.currentLevel!!.score += previousTile.question!!.correctPoints
                            updateGoldStatus()

                            // Set current tile
                            board.currentX = posX
                            board.currentY = posY
                            board.tileGrid[posX][posY].boardTileState = CURRENT

//                           TODO("Generate question in current tile")

                            // Set tiles as visited
                            for (pos in ret.second!!) {
                                board.tileGrid[pos.x][pos.y].boardTileState = VISITED
                            }

                            notifyDataSetChanged()

                            if(MovementLogic.isGameOver(posX, posY, question!!)){

                            }
                        }
                        Result.WRONG -> {
                            previousTile.boardTileState = WRONG_ANSWER
                            Session.currentLevel!!.score += previousTile.question!!.wrongPoints
                            updateGoldStatus()

                            // Set current tile
                            board.currentX = posX
                            board.currentY = posY
                            board.tileGrid[posX][posY].boardTileState = CURRENT

//                           TODO("Generate question in current tile")

                            // Set tiles as visited
                            for (pos in ret.second!!) {
                                board.tileGrid[pos.x][pos.y].boardTileState = VISITED
                            }

                            notifyDataSetChanged()

                            if(MovementLogic.isGameOver(posX, posY, question!!)){

                            }
                        }
                        Result.INVALID -> {
                            Toast.makeText(viewGroup!!.context, "Invalid move. Better check this out!", Toast.LENGTH_SHORT).show()
                        }
                    }

//                    TODO("Save game")

                    return@setOnDragListener true
                }

                return@setOnDragListener true
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    inner class BoardTileButton : AppCompatImageButton(viewGroup!!.context) {

        init {
            this.setOnTouchListener(object : OnTouchListener{
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