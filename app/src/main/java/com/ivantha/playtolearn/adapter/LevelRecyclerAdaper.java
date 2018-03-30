package com.ivantha.playtolearn.adapter;

import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ivantha.playtolearn.R;
import com.ivantha.playtolearn.model.Level;

import java.util.List;

public class LevelRecyclerAdaper extends RecyclerView.Adapter<LevelRecyclerAdaper.LevelViewHolder>{

    private List<Level> levels;

    public LevelRecyclerAdaper(List<Level> levels) {
        this.levels = levels;
    }

    @Override
    public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_level, parent, false);
        LevelViewHolder levelViewHolder = new LevelViewHolder(v);

        return levelViewHolder;
    }

    @Override
    public void onBindViewHolder(LevelViewHolder holder, int position) {
        holder.appCompatButton.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return levels.size();
    }

    class LevelViewHolder extends RecyclerView.ViewHolder{

        private AppCompatButton appCompatButton;

        public LevelViewHolder(View itemView) {
            super(itemView);

            appCompatButton = itemView.findViewById(R.id.levelButton);
        }
    }
}
