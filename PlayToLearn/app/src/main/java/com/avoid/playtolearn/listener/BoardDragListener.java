package com.avoid.playtolearn.listener;

import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;

public class BoardDragListener implements View.OnDragListener {
    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                break;
            case DragEvent.ACTION_DROP:
//                if (v instanceof BackpackTileLayout) {
//                    ViewGroup viewGroup = ((BackpackTileLayout) v);
//
//                    if (viewGroup.getChildCount() == 0) {
//                        BackpackItemButton itemButton = (BackpackItemButton) event.getLocalState();
//
//                        ViewGroup owner = (ViewGroup) itemButton.getParent();
//                        owner.removeView(itemButton);
//
//                        viewGroup.addView(itemButton, itemButton.getWidth(), itemButton.getHeight());
//                    }
//                } else if (v instanceof BackpackActionLayout) {
//                    ViewGroup viewGroup = ((BackpackActionLayout) v);
//
//                    BackpackItemButton itemButton = (BackpackItemButton) event.getLocalState();
//
//                    ViewGroup owner = (ViewGroup) itemButton.getParent();
//                    owner.removeView(itemButton);
//
//                    viewGroup.addView(itemButton, itemButton.getWidth(), itemButton.getHeight());
//                }

                return true;
            case DragEvent.ACTION_DRAG_ENDED:
                break;
            default:
                break;
        }

        return true;
    }
}
