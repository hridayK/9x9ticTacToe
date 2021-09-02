package com.attobyte.megatictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String winner;

    private Button[] grid1 = new Button[9];
    private Button[] grid2 = new  Button[9];
    private Button[] grid3 = new Button[9];
    private Button[] grid4 = new Button[9];
    private Button[] grid5 = new Button[9];
    private Button[] grid6 = new Button[9];
    private Button[] grid7 = new Button[9];
    private Button[] grid8 = new Button[9];
    private Button[] grid9 = new Button[9];

    private TextView playerBoard;

    ArrayList<Grid> matrix = new ArrayList<>();

    String[] decisionGrid;

    private int count = 0;
    private int countX = 0;
    private int countY = 0;
    private int winnerId=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        decisionGrid = new String[9];
        winner="";
        playerBoard = findViewById(R.id.playerBoard);
        playerBoard.setText("player one");

        for(int i=0;i<decisionGrid.length;i++){
            decisionGrid[i] = "";
        }

        for(int i=0;i<9;i++){
            String idS = "g"+Integer.toString(10+(i+1));
            int id = getResources().getIdentifier(idS,"id",getPackageName());
            grid1[i] = findViewById(id);
        }

        for(int i=0;i<9;i++){
            String idS = "g"+Integer.toString(20+(i+1));
            int id = getResources().getIdentifier(idS,"id",getPackageName());
            grid2[i] = findViewById(id);
        }

        for(int i=0;i<9;i++){
            String idS = "g"+Integer.toString(30+(i+1));
            int id = getResources().getIdentifier(idS,"id",getPackageName());
            grid3[i] = findViewById(id);
        }

        for(int i=0;i<9;i++){
            String idS = "g"+Integer.toString(40+(i+1));
            int id = getResources().getIdentifier(idS,"id",getPackageName());
            grid4[i] = findViewById(id);
        }

        for(int i=0;i<9;i++){
            String idS = "g"+Integer.toString(50+(i+1));
            int id = getResources().getIdentifier(idS,"id",getPackageName());
            grid5[i] = findViewById(id);
        }

        for(int i=0;i<9;i++) {
            String idS = "g" + Integer.toString(60 + (i + 1));
            int id = getResources().getIdentifier(idS, "id", getPackageName());
            grid6[i] = findViewById(id);
        }

        for(int i=0;i<9;i++){
            String idS = "g"+Integer.toString(70+(i+1));
            int id = getResources().getIdentifier(idS,"id",getPackageName());
            grid7[i] = findViewById(id);
        }

        for(int i=0;i<9;i++){
            String idS = "g"+Integer.toString(80+(i+1));
            int id = getResources().getIdentifier(idS,"id",getPackageName());
            grid8[i] = findViewById(id);
        }

        for(int i=0;i<9;i++) {
            String idS = "g" + Integer.toString(90 + (i + 1));
            int id = getResources().getIdentifier(idS, "id", getPackageName());
            grid9[i] = findViewById(id);
        }

        fillObjects();

    }

    private void fillObjects(){
        matrix.add(0, new Grid(grid1,1));
        matrix.add(1,new Grid(grid2, 2));
        matrix.add(2, new Grid(grid3, 3));
        matrix.add(3, new Grid(grid4, 4));
        matrix.add(4, new Grid(grid5, 5));
        matrix.add(5, new Grid(grid6, 6));
        matrix.add(6, new Grid(grid7, 7));
        matrix.add(7, new Grid(grid8, 8));
        matrix.add(8, new Grid(grid9, 9));
    }

    public void act(View view) {
        String ss = getResources().getResourceName(view.getId());
        int identity = Integer.parseInt(ss.substring(ss.length()-2,ss.length()));
        Button buttonPressed = findViewById(view.getId());
        buttonPressed.setClickable(false);

        if(count%2==0){
            buttonPressed.setText("X");
            playerBoard.setText("player two");
            countX++;
        }else{
            buttonPressed.setText("O");
            playerBoard.setText("player one");
            countY++;
        }

        count++;

        if(count>=1){
            stopAll();
            enableGrid(identity%10);
        }
        winCheck();
    }

    private void stopAll(){
        for(int i=0;i<matrix.size();i++){
            matrix.get(i).disableAll();
        }
    }

    private void enableGrid(int gridNo){

        if(!matrix.get(gridNo-1).isSolved()) {
            matrix.get(gridNo-1).enable();
        }
        else{
            //decisionGrid[gridNo-1]=matrix.get(gridNo-1).won;
            enableSuitable();
        }
            //String status = ""+matrix.get(gridNo-1).isSolved();
            //Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
    }

    private void enableSuitable(){
        for(int i=0;i<9;i++){
            if(!matrix.get(i).isSolved())
                matrix.get(i).enable();
            else
                continue;
        }
    }

    private void enableAll(){
        for(int i=0;i< matrix.size();i++){
            matrix.get(i).enable();
        }
    }

    private void winCheck(){

        int index=0;

        for(int i=0;i<matrix.size();i++){
            if(matrix.get(i).isSolved()){
                decisionGrid[i]=matrix.get(i).won;
            }
        }

        //vertical
        for(int i=0;i<3;i++){
            if(decisionGrid[i]=="X" && decisionGrid[i+3]=="X" && decisionGrid[i+6]=="X"){
                winner="X";
                winnerId=1;
                break;
            }
            if(decisionGrid[i]=="O" && decisionGrid[i+3]=="O" && decisionGrid[i+6]=="O"){
                winner="O";
                winnerId=2;
            }
        }

        //horizontal
        for(int i=0;i<9;i+=3){
            if(decisionGrid[i]=="X" && decisionGrid[i+1]=="X" && decisionGrid[i+2]=="X"){
                winner="X";
                winnerId=1;
                break;
            }
            if(decisionGrid[i]=="O" && decisionGrid[i+1]=="O" && decisionGrid[i+2]=="O"){
                winner="O";
                winnerId=2;
            }
        }

        //diagonal
        if(decisionGrid[0]=="X" && decisionGrid[4]=="X" && decisionGrid[8]=="X"){
            winner="X";
            winnerId=1;
        }
        if(decisionGrid[0]=="O" && decisionGrid[4]=="O" && decisionGrid[6]=="O"){
            winner="O";
            winnerId=2;
        }

        if(winner=="X"){
            Toast.makeText(this, "player one wins!!!", Toast.LENGTH_SHORT).show();
        }
        if(winner=="O"){
            Toast.makeText(this, "player one wins!!!", Toast.LENGTH_SHORT).show();
        }

        int count=0;

        //to check for draw, this might require more logic
        if(winner=="") {
            for (int i = 0; i < decisionGrid.length; i++) {
                if (decisionGrid[i] != "") {
                    count++;
                }
            }

            if (count == 9) {
                winner = "none";
                winnerId=0;
            }
        }

        if(winner!=""){
            gameExit();
        }

    }

    private void gameExit(){
        Intent intent = new Intent(this,WinnerBoard.class);
        intent.putExtra("totalSteps",count);
        intent.putExtra("totalStepsX",countX);
        intent.putExtra("totalStepsY",countY);
        intent.putExtra("winnerName",winnerId);
        startActivity(intent);
    }
}