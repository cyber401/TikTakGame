package com.example.tiktak;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView tabimage;
    GridLayout gridLayout;
    boolean One, Two,gameWinner;

    enum Player {
        One, Two
    }

    private TextView scoreOne, scoreTwo;
    private int playerOne, playerTwo;
    String winnerOfGame;

    private Player currentPlayer = Player.One;
    private Player[] nowPlaying = new Player[9];
    private int[][] winner = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 4, 8},
            {2, 4, 6}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridlayout);
        scoreOne = findViewById(R.id.scoreone);
        scoreTwo = findViewById(R.id.scoretwo);
        scoreOne.setText(playerOne + "");
        scoreTwo.setText(playerTwo + "");
    }

    public void clickit(View imageview) {
        tabimage = (ImageView) imageview;
        int tagValue = Integer.parseInt(tabimage.getTag().toString());

        if (nowPlaying[tagValue] == null) {
            nowPlaying[tagValue] = currentPlayer;
            if (currentPlayer == Player.One) {
                tabimage.setImageResource(R.drawable.x);
                currentPlayer = Player.Two;
            } else if (currentPlayer == Player.Two) {
                tabimage.setImageResource(R.drawable.zero);
                currentPlayer = Player.One;
            }
        }
        for (int[] test : winner){
            if (nowPlaying[test[0]] == nowPlaying[test[1]] &&
                    nowPlaying[test[1]] == nowPlaying[test[2]] &&
                    nowPlaying[test[2]] == Player.One &&
                    nowPlaying[test[2]] != null) {
                winnerOfGame = "Player One win";
                One = true;
                gameWinner=true;
                playerOne++;
                scoreOne.setText(playerOne + "");


            } else if (nowPlaying[test[0]] == nowPlaying[test[1]] &&
                    nowPlaying[test[1]] == nowPlaying[test[2]] &&
                    nowPlaying[test[2]] == Player.Two &&
                    nowPlaying[test[1]] != null) {
                winnerOfGame = "Player Two win";
                Two = true;
                gameWinner=true;
                playerTwo++;
                scoreTwo.setText(playerTwo + "");
            }
            if (One == true) {
                restart();
                One = false;
            } else if (Two == true) {
                restart();
                Two = false;
            }
        }
        if (nowPlaying[0] != null && nowPlaying[1] != null && nowPlaying[2] != null &&
                nowPlaying[3] != null && nowPlaying[4] != null && nowPlaying[5] != null && nowPlaying[6] != null &&
                nowPlaying[7] != null && nowPlaying[8] != null&&gameWinner==false&&gameWinner==false) {
            AlertDialog.Builder draw=new AlertDialog.Builder(MainActivity.this);
            draw.setMessage("Match is Draw").setPositiveButton("Play again", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    for (int i = 0; i < gridLayout.getChildCount(); i++) {
                        ImageView image = (ImageView) gridLayout.getChildAt(i);
                        image.setImageDrawable(null);
                    }
                    nowPlaying = new Player[9];
                    currentPlayer=Player.One;
                }
            }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    System.exit(0);
                }
            }).setCancelable(false);
            AlertDialog finish=draw.create();
            finish.show();
        }
    }


    public void restart() {
        AlertDialog.Builder alertBox=new AlertDialog.Builder(MainActivity.this);
        alertBox.setMessage(winnerOfGame).setPositiveButton("Play again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < gridLayout.getChildCount(); i++) {
                    ImageView image = (ImageView) gridLayout.getChildAt(i);
                    image.setImageDrawable(null);
            }
                nowPlaying = new Player[9];
        }
        }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    finish();
                    System.exit(0);
            }
        }).setCancelable(false);
        AlertDialog finish=alertBox.create();
        finish.show();
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder exit=new AlertDialog.Builder(MainActivity.this);
        exit.setMessage("Are You Sure?").setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
        }}).setNegativeButton("Cancel",null).setCancelable(false);
        AlertDialog finish=exit.create();
        finish.show();//hay
    }
}
