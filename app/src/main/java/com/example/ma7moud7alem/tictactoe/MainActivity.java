package com.example.ma7moud7alem.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 is O blue
    //1 is X red
    int activePlayer = 0;
    boolean gameisActive = true;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedPosition = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedPosition] == 2 && gameisActive) {
            gameState[tappedPosition] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }

            for (int[] winningPosition : winningPositions) {


                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2) {
                    gameisActive = false;
                    String winner = "Red";

                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Blue";
                    }
                    TextView msg = (TextView) findViewById(R.id.winnertextmessage);
                    msg.setText(winner + " " + "has won!");
                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playagainlayout);
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    boolean gameIsover = true;
                    for (int counterState : gameState) {
                        if (counterState == 2) gameIsover = false;
                    }
                    if(gameIsover){
                            TextView msg = (TextView) findViewById(R.id.winnertextmessage);
                            msg.setText("It's a draw");
                            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playagainlayout);
                            linearLayout.setVisibility(View.VISIBLE);
                        }

                    }

                }
            }
        }


    public void playAgain(View view) {
        gameisActive= true ;
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.playagainlayout);
        linearLayout.setVisibility(View.INVISIBLE);
        activePlayer = 0;

        for(int i=0;i < gameState.length; i++ ){
            gameState[i]=2;
        }
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        for(int i=0 ; i<gridLayout.getChildCount(); i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }
}
