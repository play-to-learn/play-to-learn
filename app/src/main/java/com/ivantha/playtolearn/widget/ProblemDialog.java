package com.ivantha.playtolearn.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ivantha.playtolearn.R;

public class ProblemDialog extends Dialog {
    private LabelTextView titleTextView;
    private LabelTextView descriptionTextView;

    public ProblemDialog(@NonNull Context context) {
        super(context, R.style.ProblemDialog);
        this.setContentView(R.layout.dialog_problem);

        this.titleTextView = findViewById(R.id.title_text_view);
        this.descriptionTextView = findViewById(R.id.description_text_view);
    }

    public void setTitle(String title){
        this.titleTextView.setText(title);
    }

    public void setDescription(String description){
        this.descriptionTextView.setText(description);
    }
}
