package com.ivantha.playtolearn.adapter

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.ivantha.playtolearn.R
import com.ivantha.playtolearn.common.Session
import com.ivantha.playtolearn.model.Tile

class TileRecyclerAdapter(var tiles: ArrayList<Tile>) : RecyclerView.Adapter<TileRecyclerAdapter.TileViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TileViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_tile, parent, false)
        return TileViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tiles.size
    }

    override fun onBindViewHolder(holder: TileViewHolder, position: Int) {
        holder.state = tiles[position].boardTileState
        when (tiles[position].boardTileState) {
            Tile.BoardTileState.NOT_VISITED -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_not_visited0)
            Tile.BoardTileState.VISITED -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_visited)
            Tile.BoardTileState.CURRENT -> holder.tileFrameLayout.setBackgroundResource(R.color.colorTransparent)
            Tile.BoardTileState.CORRECT_ANSWER -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_correct)
            Tile.BoardTileState.WRONG_ANSWER -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_wrong)
            else -> holder.tileFrameLayout.setBackgroundResource(R.drawable.board_tile_background_not_visited0)
        }
    }

    inner class TileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tileFrameLayout: FrameLayout = itemView.findViewById(R.id.tileFrameLayout)
        var state: Tile.BoardTileState? = null

        init {
//            this.setOnTouchListener(BoardTouchListener(context))
//            this.setOnDragListener(BoardDragListener())

            applyCustomDesign()

//            if (tile.boardTileState === Tile.BoardTileState.CURRENT) {
//                this.addView(BoardTileButton(context))
//            }
        }

//        fun setBoardTileState(tileState: Tile.BoardTileState) {
//            this.tile.boardTileState = tileState
//            this.refreshStateChanges()
//        }

//        fun generateQuestion() {
//            println("Generate question>>>>>>>>>>>>>>>>>>")
//        }


        private fun applyCustomDesign() {
            val effectiveWidth = Resources.getSystem().displayMetrics.widthPixels
            val sizePx = effectiveWidth / (Session.COLUMN_COUNT + 1)
            val marginPx = (effectiveWidth - sizePx * Session.COLUMN_COUNT) / (Session.COLUMN_COUNT * 2)

            val params = FrameLayout.LayoutParams(sizePx, sizePx)
            params.setMargins(marginPx, marginPx, marginPx, marginPx)
            tileFrameLayout.layoutParams = params
        }
    }
}