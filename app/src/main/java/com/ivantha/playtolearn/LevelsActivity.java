package com.ivantha.playtolearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.ivantha.playtolearn.common.Session;
import com.ivantha.playtolearn.model.Level;

import java.util.ArrayList;

public class LevelsActivity extends AppCompatActivity {
    private ArrayList<Level> levels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);

        GridView levelsGridView = findViewById(R.id.levelsGridView);
        LevelAdapter levelAdapter = new LevelAdapter();
        levelsGridView.setAdapter(levelAdapter);

//        Session.database.getReference("levels").
    }

    private class LevelAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return levels.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LevelButton levelButton = new LevelButton();
            levelButton.setText(String.valueOf(i));
            return levelButton;
        }
    }

    private class LevelButton extends android.support.v7.widget.AppCompatButton {

        public LevelButton() {
            super(LevelsActivity.this);
            applyCustomShape();
        }

        private void applyCustomShape() {
            setBackgroundResource(R.drawable.levels_button);
        }
    }
}
