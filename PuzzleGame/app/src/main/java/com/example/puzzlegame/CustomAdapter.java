package com.example.puzzlegame;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private ArrayList<Button> mButtons;
    private int mColumnWidth,mColumnHeight;

    public CustomAdapter(ArrayList<Button> buttons, int columnWidth, int columnHeight){
        mButtons = buttons;
        mColumnWidth = columnWidth;
        mColumnHeight = columnHeight;

    }
    @Override
    public int getCount() {
        return mButtons.size();
    }

    @Override
    public Object getItem(int position) {
        return(Object) mButtons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;

        if (convertView == null){
            button = mButtons.get(position);
        }else {
            button = (Button) convertView;
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(mColumnWidth,mColumnHeight);
        button.setLayoutParams(params);
        return button ;
    }
}
