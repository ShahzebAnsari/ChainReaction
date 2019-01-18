package com.example.shahzeb.chainreaction;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //2 for red & 1 for green
    int canPlay=1;
    int currentPlayer=1;
    int lastPlayer=2,noOfRedBall=0,noOfGreenBall=0;
    int n=54;
    int[] noOfBall=new int[n];
    int[] playerPosition=new int[n];
    GridLayout gridLayout;
    ImageView backGround;
    LinearLayout linearLayout;
    Vibrator vibrator;

    public void onClickAtMiddle(int index){
        if(noOfBall[index]<3){
            if(noOfBall[index]==0)
            {
                if(currentPlayer==1) {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.greenballsingle);
                    noOfGreenBall++;
                }else {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.redballsingle);
                    noOfRedBall++;
                }
            }else if(noOfBall[index]==1){
                if(playerPosition[index]==1)
                    noOfGreenBall-=1;
                else
                    noOfRedBall-=1;
                if(currentPlayer==1) {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.greenballdouble);
                    noOfGreenBall+=2;
                }else {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.redballdouble);
                    noOfRedBall+=2;
                }
            }else{
                if(playerPosition[index]==1)
                    noOfGreenBall-=2;
                else
                    noOfRedBall-=2;

                if(currentPlayer==1) {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.greenballtriple);
                noOfGreenBall+=3;
                }else {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.redballtriple);
                noOfRedBall+=3;
                }
                }
            playerPosition[index]=currentPlayer;
            noOfBall[index]++;
            if(noOfGreenBall==0||noOfRedBall==0)
                canPlay=0;
            return;
        }
        if(canPlay==0)return;
        if(playerPosition[index]==1)
            noOfGreenBall-=noOfBall[index];
        else
            noOfRedBall-=noOfBall[index];
        noOfBall[index]=0;
        playerPosition[index]=0;
        ((ImageView)gridLayout.getChildAt(index)).setImageResource(R.drawable.nullblack);
        if(index-6<5){
            onClickSide(index-6);
        }else{
            onClickAtMiddle(index-6);
        }
        if(index+6>48){
            onClickSide(index+6);
        }else{
            onClickAtMiddle(index+6);
        }
        if((index-1)%6==0){
            onClickSide(index-1);
        }else{
            onClickAtMiddle(index-1);
        }
        if((index+2)%6==0){
            onClickSide(index+1);
        }else{
            onClickAtMiddle(index+1);
        }
    }

    public void onClickSide(int index){
        if(noOfBall[index]<2){
            if(noOfBall[index]==0)
            {
                if(currentPlayer==1) {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.greenballsingle);
                    noOfGreenBall++;
                }else {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.redballsingle);
                    noOfRedBall++;
                }
                }
            else{
                if(playerPosition[index]==1)
                    noOfGreenBall-=1;
                else
                    noOfRedBall-=1;
                if(currentPlayer==1) {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.greenballdouble);
                    noOfGreenBall+=noOfBall[index]+1;
                }else {
                    ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.redballdouble);
                    noOfRedBall+=noOfBall[index]+1;
                }
            }
            playerPosition[index]=currentPlayer;
            noOfBall[index]++;
            if(noOfRedBall==0||noOfGreenBall==0)
                canPlay=0;
            return;
        }
        if(canPlay==0)return;
        if(playerPosition[index]==1)
            noOfGreenBall-=noOfBall[index];
        else
            noOfRedBall-=noOfBall[index];
        noOfBall[index]=0;
        playerPosition[index]=0;
        ((ImageView)gridLayout.getChildAt(index)).setImageResource(R.drawable.nullblack);
        for(int i=1;i<=4;i++){
            if(index==i){
                if(i==1){
                    onClickAtCorner(i-1);
                }else{
                    onClickSide(i-1);
                }
                if(i==4){
                    onClickAtCorner(i+1);
                }
                else {
                    onClickSide(i+1);
                }
                onClickAtMiddle(i+6);
                return;
            }
        }
        for(int i=49;i<=52;i++) {
            if (index == i) {
                if (i == 49) {
                    onClickAtCorner(i - 1);
                } else {
                    onClickSide(i - 1);
                }
                if (i == 52) {
                    onClickAtCorner(i + 1);
                } else {
                    onClickSide(i + 1);
                }
                onClickAtMiddle(i - 6);
                return;
            }
        }
        for(int i=6;i<=42;i=i+6){
            if(index==i){
                if(i==6){
                    onClickAtCorner(i-6);
                }else{
                    onClickSide(i-6);
                }
                if(i==42){
                    onClickAtCorner(i+6);
                }else{
                    onClickSide(i+6);
                }
                onClickAtMiddle(i+1);
                return;
            }

        }
        for(int i=11;i<=47;i=i+6){
            if(index==i){
                if(i==11){
                    onClickAtCorner(i-6);
                }else{
                    onClickSide(i-6);
                }
                if(i==47){
                    onClickAtCorner(i+6);
                }
                else {
                    onClickSide(i+6);
                }
                onClickAtMiddle(i-1);
                return;
            }
        }


    }

    public void onClickAtCorner(int index){
        if(noOfBall[index]==0)
        {
            if(currentPlayer==1) {
                ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.greenballsingle);
                noOfGreenBall++;
            }else {
                ((ImageView) gridLayout.getChildAt(index)).setImageResource(R.drawable.redballsingle);
                noOfRedBall++;
            }playerPosition[index]=currentPlayer;
            noOfBall[index]++;
            if(noOfGreenBall==0||noOfRedBall==0)
                canPlay=0;
            return;
        }
        if(canPlay==0)return;
        if(playerPosition[index]==1)
            noOfGreenBall-=noOfBall[index];
        else
            noOfRedBall-=noOfBall[index];
        noOfBall[index]=0;
        playerPosition[index]=0;
        ((ImageView)gridLayout.getChildAt(index)).setImageResource(R.drawable.nullblack);
        if(index==0){
        onClickSide(1);
        onClickSide(6);
        }
        else
            if(index==5){
            onClickSide(4);
            onClickSide(11);
            }
            else
                if(index==48) {
                onClickSide(49);
                onClickSide(42);

        }else {
            onClickSide(52);
            onClickSide(47);
                }
    }

    public void imagePressed(View view){
        if(canPlay==0)return;
        ImageView currentBox=(ImageView)view;
        int index=Integer.parseInt(currentBox.getTag().toString());
        if(playerPosition[index]==lastPlayer)
            return;
        vibrator.vibrate(30);
        if(noOfBall[index]==0) {
            if(currentPlayer==1) {
                currentBox.setImageResource(R.drawable.greenballsingle);
                noOfGreenBall++;
            }else {
                currentBox.setImageResource(R.drawable.redballsingle);
                noOfRedBall++;
            }playerPosition[index]=currentPlayer;
            noOfBall[index]++;
        }

        else if(noOfBall[index]==1){
            if(index!=0&&index!=5&&index!=48&&index!=53) {
                if (currentPlayer == 1) {
                    currentBox.setImageResource(R.drawable.greenballdouble);
                    noOfGreenBall++;
                }else {
                    currentBox.setImageResource(R.drawable.redballdouble);
                    noOfRedBall++;
                }noOfBall[index]++;
            }
            else{
                onClickAtCorner(index);
            }
        }

        else if(noOfBall[index]==2){
            int j=0;
            for(int i=1;i<=4;i++)
            {
                if (index == i) {
                    onClickSide(index);
                    j=1;
                }
            }
            if(j==0)
            for(int i=6;i<=48;i=i+6) {
                if (index == i || index == i - 1) {
                    onClickSide(index);
                    j=1;
                }
            }
            if(j==0)
            for(int i=49;i<=52;i++){
                if(index == i){
                    onClickSide(index);
                    j=1;
                }
            }
            if(j==0) {
                if (currentPlayer == 1) {
                    currentBox.setImageResource(R.drawable.greenballtriple);
                    noOfGreenBall++;
                }else {
                    currentBox.setImageResource(R.drawable.redballtriple);
                    noOfRedBall++;
                }noOfBall[index]++;
            }
        }
        else{
            onClickAtMiddle(index);
        }
        int temp=currentPlayer;
        currentPlayer=lastPlayer;
        lastPlayer=temp;
        if((noOfRedBall==0&&noOfGreenBall>1)||(noOfGreenBall==0&&noOfRedBall>1)){
            canPlay=0;
            ((TextView)linearLayout.getChildAt(1)).setText("Player"+" "+Integer.toString(lastPlayer)+" "+"Won!");
            linearLayout.animate().alpha(1).setDuration(100);
        }else {
            if (currentPlayer == 1)
                backGround.setImageResource(R.drawable.greengrid);
            else
                backGround.setImageResource(R.drawable.redgrid);
        }}

    public void textPressed(View view){
        if(canPlay==1)return;
        canPlay=1;
        currentPlayer=1;
        lastPlayer=2;
        noOfRedBall=0;
        noOfGreenBall=0;
        for(int i=0;i<n;i++) {
            noOfBall[i] = 0;
            playerPosition[i]=0;
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(R.drawable.nullblack);
        }
        linearLayout.animate().alpha(0).setDuration(100);
        backGround.setImageResource(R.drawable.greengrid);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        backGround=(ImageView) findViewById(R.id.backGround);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        backGround.setImageResource(R.drawable.greengrid);
        vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        for(int i=0;i<n;i++) {
            noOfBall[i] = 0;
            playerPosition[i]=0;
        }
    }
}
