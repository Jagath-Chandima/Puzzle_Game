package com.example.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class HardGame extends AppCompatActivity {

    private static final int COLUMNS = 5;
    private static final int DIMENSIONS = COLUMNS * COLUMNS;
    public static final String UP = "up";
    public static final String DOWN = "down";
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    private static String[] tileList;

    private static HardGestureGridView mGridView;
    private static Context hContext;

    private static int mColumnWidth,mColumnHeight;

    public static void moveTiles(Context context, Object up, int position) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_game);

        init();
        scramble();
        setDimensions();
        hContext = this;
    }
    private void setDimensions(){
        ViewTreeObserver vto = mGridView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mGridView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int displayWidth = mGridView.getMeasuredWidth();
                int displayHeight = mGridView.getMeasuredHeight();

                int statusbarHeight = getStatusbarHeight(getApplicationContext());
                int requiredHeight = displayHeight - statusbarHeight;

                mColumnWidth = displayWidth/ COLUMNS;
                mColumnHeight = requiredHeight/ COLUMNS;

                display(getApplicationContext());


            }
        });
    }
    private int getStatusbarHeight(Context context){
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen","android");

        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private static void display(Context context){
        ArrayList<Button> buttons = new ArrayList<>();
        Button button;

        for (int i = 0; i < tileList.length;i++){
            button = new Button(context);

            if (tileList[i].equals("0"))
                button.setBackgroundResource(R.drawable.hr_01);
            else if (tileList[i].equals("1"))
                button.setBackgroundResource(R.drawable.hr_02);
            else if (tileList[i].equals("2"))
                button.setBackgroundResource(R.drawable.hr_03);
            else if (tileList[i].equals("3"))
                button.setBackgroundResource(R.drawable.hr_04);
            else if (tileList[i].equals("4"))
                button.setBackgroundResource(R.drawable.hr_05);
            else if (tileList[i].equals("5"))
                button.setBackgroundResource(R.drawable.hr_06);
            else if (tileList[i].equals("6"))
                button.setBackgroundResource(R.drawable.hr_07);
            else if (tileList[i].equals("7"))
                button.setBackgroundResource(R.drawable.hr_08);
            else if (tileList[i].equals("8"))
                button.setBackgroundResource(R.drawable.hr_09);
            else if (tileList[i].equals("9"))
                button.setBackgroundResource(R.drawable.hr_10);
            else if (tileList[i].equals("10"))
                button.setBackgroundResource(R.drawable.hr_11);
            else if (tileList[i].equals("11"))
                button.setBackgroundResource(R.drawable.hr_12);
            else if (tileList[i].equals("12"))
                button.setBackgroundResource(R.drawable.hr_13);
            else if (tileList[i].equals("13"))
                button.setBackgroundResource(R.drawable.hr_14);
            else if (tileList[i].equals("14"))
                button.setBackgroundResource(R.drawable.hr_15);
            else if (tileList[i].equals("15"))
                button.setBackgroundResource(R.drawable.hr_16);
            else if (tileList[i].equals("16"))
                button.setBackgroundResource(R.drawable.hr_17);
            else if (tileList[i].equals("17"))
                button.setBackgroundResource(R.drawable.hr_18);
            else if (tileList[i].equals("18"))
                button.setBackgroundResource(R.drawable.hr_19);
            else if (tileList[i].equals("19"))
                button.setBackgroundResource(R.drawable.hr_20);
            else if (tileList[i].equals("20"))
                button.setBackgroundResource(R.drawable.hr_21);
            else if (tileList[i].equals("21"))
                button.setBackgroundResource(R.drawable.hr_22);
            else if (tileList[i].equals("22"))
                button.setBackgroundResource(R.drawable.hr_23);
            else if (tileList[i].equals("23"))
                button.setBackgroundResource(R.drawable.hr_24);
            else if (tileList[i].equals("24"))
                button.setBackgroundResource(R.drawable.hr_25);

            buttons.add(button);
        }

        mGridView.setAdapter(new CustomAdapter(buttons,mColumnWidth,mColumnHeight));
    }
    private void scramble(){
        int index;
        String temp;
        Random random = new Random();

        for (int i = tileList.length - 1; i > 0; i--){
            index = random.nextInt(i + 1);
            temp = tileList[index];
            tileList[index] = tileList[i];
            tileList[i] = temp;
        }
    }

    private void init(){
        mGridView = (HardGestureGridView) findViewById(R.id.hardGrid);
        mGridView.setNumColumns(COLUMNS);
        tileList = new String[DIMENSIONS];
        for (int i = 0; i< DIMENSIONS;i++) {
            tileList[i] = String.valueOf(i);
        }
    }
    private static void swap(Context context, int position, int swap){
        String newPosition = tileList[position + swap];
        tileList[position + swap] = tileList[position];
        tileList[position] = newPosition;
        display(context);

        if (isSolved()) {
            Intent intent = new Intent(hContext, EndGame.class);
            hContext.startActivity(intent);
        }

    }
    private static boolean isSolved(){
        boolean solved = false;

        for (int i = 0; i < tileList.length; i++) {
            if (tileList[i].equals(String.valueOf(i))){
                solved = true;
            } else {
                solved = false;
                break;
            }
        }
        return solved;
    }

    public static void moveTiles(Context context , String direction ,int position){

        // Upper-left-corner tile
        if (position == 0) {

            if (direction.equals(RIGHT)) swap(context, position, 1);
            else if (direction.equals(DOWN)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (position > 0 && position < COLUMNS - 1) {
            if (direction.equals(LEFT)) swap(context, position, -1);
            else if (direction.equals(DOWN)) swap(context, position, COLUMNS);
            else if (direction.equals(RIGHT)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (position == COLUMNS - 1) {
            if (direction.equals(LEFT)) swap(context, position, -1);
            else if (direction.equals(DOWN)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (position > COLUMNS - 1 && position < DIMENSIONS - COLUMNS &&
                position % COLUMNS == 0) {
            if (direction.equals(UP)) swap(context, position, -COLUMNS);
            else if (direction.equals(RIGHT)) swap(context, position, 1);
            else if (direction.equals(DOWN)) swap(context, position, COLUMNS);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (position == COLUMNS * 4 - 1 || position == COLUMNS * 5 - 1) {
            if (direction.equals(UP)) swap(context, position, -COLUMNS);
            else if (direction.equals(LEFT)) swap(context, position, -1);
            else if (direction.equals(DOWN)) {

                // Tolerates only the right-side tiles to swap downwards as opposed to the bottom-
                // right-corner tile.
                if (position <= DIMENSIONS - COLUMNS - 1) swap(context, position,
                        COLUMNS);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (position == DIMENSIONS - COLUMNS) {
            if (direction.equals(UP)) swap(context, position, -COLUMNS);
            else if (direction.equals(RIGHT)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (position < DIMENSIONS - 1 && position > DIMENSIONS - COLUMNS) {
            if (direction.equals(UP)) swap(context, position, -COLUMNS);
            else if (direction.equals(LEFT)) swap(context, position, -1);
            else if (direction.equals(RIGHT)) swap(context, position, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(UP)) swap(context, position, -COLUMNS);
            else if (direction.equals(LEFT)) swap(context, position, -1);
            else if (direction.equals(RIGHT)) swap(context, position, 1);
            else swap(context, position, COLUMNS);
        }
    }
}
