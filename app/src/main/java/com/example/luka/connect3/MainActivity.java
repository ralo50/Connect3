package com.example.luka.connect3;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int turn = 1;
    boolean activeGame = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions =  {{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
    }

    public void dropIn(View view) {
        if(activeGame) {
            if (turn < 10) {
                ImageView counter = (ImageView) view;
                int counterTag = Integer.parseInt(counter.getTag().toString());
                if (gameState[counterTag] == 2) {
                    counter.setTranslationY(-1000f);
                    if (turn % 2 == 0) {
                        counter.setImageResource(R.drawable.red);
                        counter.animate().translationY(10f).setDuration(200);
                        turn++;
                        gameState[counterTag] = 0;
                        counter.setAlpha(1f);
                    } else if (turn % 2 != 0) {
                        counter.setImageResource(R.drawable.blue);
                        counter.animate().translationY(10f).setDuration(200);
                        turn++;
                        gameState[counterTag] = 1;
                        counter.setAlpha(1f);
                    }
                }
                String winner = "Red won";
                for (int[] i : winningPositions) {
                    if ((gameState[i[0]] == gameState[i[1]] && gameState[i[1]] == gameState[i[2]] && gameState[i[0]] != 2) || turn == 10) {
                        if (turn == 10) {
                            winner = "Its a tie";
                            activeGame = false;
                        } else if (gameState[i[0]] == 1) {
                            winner = "Blue won";
                            activeGame = false;
                        }
                        Toast.makeText(MainActivity.this, winner, Toast.LENGTH_SHORT).show();
                        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                        layout.setVisibility(View.VISIBLE);
                        activeGame = false;
                        turn = 10;
                        break;
                    }
                }
            }
        }
    }
    public void playAgain(View view){
        turn = 1;
        for(int i = 0; i < gameState.length; i++){
            gameState[i] = 2;
        }
        LinearLayout layout = (LinearLayout)findViewById(R.id.layout);
        layout.setVisibility(View.INVISIBLE);
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        for(int j = 0; j < gridLayout.getChildCount(); j++){
            ((ImageView) gridLayout.getChildAt(j)).setImageResource(0);
        }
        activeGame = true;
    }
}
