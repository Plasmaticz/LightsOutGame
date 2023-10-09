package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

//Author @Eric Su
//Version October 2023
public class MainActivity extends AppCompatActivity {

    private static final int GRID_SIZE = 5;
    private GridLayout gridLayout;
    private Button[][] buttons;
    private LightsOutGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new LightsOutGame();

        gridLayout = findViewById(R.id.gridLayout);
        buttons = new Button[GRID_SIZE][GRID_SIZE];

        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = new Button(this);
                buttons[i][j].setText("");
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Button clickedButton = (Button) view;
                        int x = gridLayout.indexOfChild(clickedButton) / GRID_SIZE;
                        int y = gridLayout.indexOfChild(clickedButton) % GRID_SIZE;
                        game.toggle(x, y);
                        updateUI();
                        checkWin();
                    }
                });
                gridLayout.addView(buttons[i][j]);
            }
        }
        updateUI();
    }

    private void updateUI() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j].setBackgroundColor(game.getValue(i, j) ? Color.BLACK : Color.WHITE);
            }
        }
    }

    private void checkWin() {
        if (game.isComplete()) {
            RelativeLayout mainLayout = findViewById(R.id.mainLayout);
            mainLayout.setBackgroundColor(Color.GREEN);
        } else {
            RelativeLayout mainLayout = findViewById(R.id.mainLayout);
            mainLayout.setBackgroundColor(Color.WHITE);
        }
    }

    public void resetPuzzle(View view) {
        game.randomize();
        updateUI();
    }
}
