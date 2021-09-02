package com.attobyte.megatictactoe;

import android.graphics.Color;
import android.widget.Button;

public class Grid {

    //stores which character( X or O) won the individual grid
    public String won;

    private Button[] buttons;
    private int type;

    Grid(Button[] buttons, int type){
        won="";
        this.buttons = buttons;
        this.type = type;
    }

    public void disableAll(){
        for(int i=0;i<buttons.length;i++){
            if(type%2==0)
                buttons[i].setBackgroundColor(Color.parseColor("#707070"));
            else
                buttons[i].setBackgroundColor(Color.parseColor("#232121"));
            buttons[i].setClickable(false);
        }
    }

    public void enable(){
        for(int i=0;i<9;i++){
            if(buttons[i].getText().toString()!="X" && buttons[i].getText().toString()!="O"){
                if(type%2==0)
                    buttons[i].setBackgroundColor(Color.parseColor("#FFAB00"));
                else
                    buttons[i].setBackgroundColor(Color.parseColor("#C51162"));
                buttons[i].setClickable(true);
            }
        }
    }

    public boolean isSolved(){

        boolean flag = false;
        won = "";

        //horizontal check
        for(int i=0;i<9;i+=3){
            if(buttons[i].getText().toString()=="X" && buttons[i+1].getText().toString()=="X" && buttons[i+2].getText().toString()=="X"){
                won = "X";
                flag=true;
            }else if(buttons[i].getText().toString()=="O" && buttons[i+1].getText().toString()=="O" && buttons[i+2].getText().toString()=="O"){
                won = "O";
                flag=true;
            }
        }

        //vertical check
        for(int i=0;i<3;i++){
            if(buttons[i].getText().toString()=="X" && buttons[i+3].getText().toString()=="X" && buttons[i+6].getText().toString()=="X"){
                won = "X";
                flag=true;
            }else if(buttons[i].getText().toString()=="O" && buttons[i+3].getText().toString()=="O" && buttons[i+6].getText().toString()=="O"){
                won = "0";
                flag=true;
            }
        }

        //diagonal checks
        if(buttons[0].getText().toString()=="X" && buttons[4].getText().toString()=="X" && buttons[8].getText().toString()=="X"){
            won = "X";
            flag=true;
        }else if(buttons[0].getText().toString()=="O" && buttons[4].getText().toString()=="O" && buttons[8].getText().toString()=="O"){
            won = "O";
            flag=true;
        }

        if(buttons[2].getText().toString()=="X" && buttons[4].getText().toString()=="X" && buttons[6].getText().toString()=="X"){
            won = "X";
            flag=true;
        }else if(buttons[2].getText().toString()=="O" && buttons[4].getText().toString()=="O" && buttons[6].getText().toString()=="O"){
            won = "O";
            flag=true;
        }


        //draw checks
        int notEmpty=0;

        for(int i=0;i<9;i++){
            if(buttons[i].getText().toString()=="X" || buttons[i].getText().toString()=="O"){
                notEmpty++;
            }
        }

        if (notEmpty==9)
            flag=true;

        return flag;
    }

}
