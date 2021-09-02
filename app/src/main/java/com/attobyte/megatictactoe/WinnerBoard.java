package com.attobyte.megatictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class WinnerBoard extends AppCompatActivity {

    private TextView winner;
    private TextView totalMoves;
    private TextView pOneMoves;
    private TextView pTwoMoves;

    private int winnerName;
    private int steps;
    private int stepsX;
    private int stepsY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_board);

        winnerName=steps=stepsX=stepsY=0;


        totalMoves = findViewById(R.id.totalSteps);
        winner = findViewById(R.id.winnerName);
        pOneMoves = findViewById(R.id.pOneSteps);
        pTwoMoves = findViewById(R.id.pTwoSteps);

        Intent intent = getIntent();
        steps = intent.getIntExtra("totalSteps",0);
        stepsX = intent.getIntExtra("totalStepsX",0);
        stepsY = intent.getIntExtra("totalStepsY",0);
        winnerName = intent.getIntExtra("winnerName",0);

        String tempTotal=totalMoves.getText().toString();
        String tempX=pOneMoves.getText().toString();
        String tempY=pTwoMoves.getText().toString();

        if(winnerName==1)
            winner.setText("Player One");
        else if(winnerName==2)
            winner.setText("Player Two");
        else if(winnerName==0)
            winner.setText("It's a draw");
        else
            winner.setText("winner ka naam transfer nahi ho raha hai");

        totalMoves.setText(tempTotal+" "+Integer.toString(steps));
        pOneMoves.setText(tempX+" "+Integer.toString(stepsX));
        pTwoMoves.setText(tempY+" "+Integer.toString(stepsY));
    }
}