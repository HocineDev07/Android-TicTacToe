package com.hocinedev.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;


    int PLAYER_1 = 0;
    int PLAYER_2 = 1;

    int activePlayer = PLAYER_1;

    int[] filledPos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    boolean isGameActive = true;

    int checkingNumber;
   // Animation shake;
    String namePlayer1, namePlayer2;

    private MaterialCardView cardPlayer_1, cardPlayer_2;
    private TextView textViewPlayer_1Turn, textViewPlayer_2Turn;
    private TextView textViewPlayer_1, textViewPlayer_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namePlayer1 = getIntent().getStringExtra(getString(R.string.txt_player_1));
        namePlayer2 = getIntent().getStringExtra(getString(R.string.txt_player_2));

        cardPlayer_1 = findViewById(R.id.cardPlayer_0);
        cardPlayer_2 = findViewById(R.id.cardPlayer_x);
        textViewPlayer_1Turn = findViewById(R.id.textPlayer_1_turn);
        textViewPlayer_2Turn = findViewById(R.id.textPlayer_2_turn);
        textViewPlayer_1 = findViewById(R.id.name_player1);
        textViewPlayer_2 = findViewById(R.id.name_player2);

        textViewPlayer_1.setText(namePlayer1);
        textViewPlayer_2.setText(namePlayer2);

        cardPlayer_1.setStrokeWidth(2);
        textViewPlayer_1Turn.setText(getString(R.string.txt_your_turn));

      //  shake = AnimationUtils.loadAnimation(this, R.anim.shake_animation);

        checkingNumber = 0;

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        // logic for button press

        if (!isGameActive)
            return;

        checkingNumber += 1;

        ImageView clickedImg = findViewById(v.getId());
        int clickedTag = Integer.parseInt(v.getTag().toString());

        if (filledPos[clickedTag] != -1) {
            return;
        }

        filledPos[clickedTag] = activePlayer;

        ImageView imgClicked = (ImageView) v;
        if (activePlayer == PLAYER_1) {
            imgClicked.setImageResource(R.drawable.image_circle);
         //   v.startAnimation(shake);
            activePlayer = PLAYER_2;
            cardPlayer_1.setStrokeWidth(0);
            cardPlayer_2.setStrokeWidth(2);
            textViewPlayer_1Turn.setText("");
            textViewPlayer_2Turn.setText(getString(R.string.txt_your_turn));
            //    headerText.setText("X turn");
        } else {
            imgClicked.setImageResource(R.drawable.image_cross);
         //   v.startAnimation(shake);
            activePlayer = PLAYER_1;
            cardPlayer_2.setStrokeWidth(0);
            cardPlayer_1.setStrokeWidth(2);
            textViewPlayer_1Turn.setText(getString(R.string.txt_your_turn));
            textViewPlayer_2Turn.setText("");
            //      headerText.setText("O turn");
        }

        checkForWin();

    }

    private void checkForWin() {
        //we will check who is winner and show
        int[][] winningPos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

        for (int i = 0; i < 8; i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if (filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]) {
                if (filledPos[val0] != -1) {
                    //winner declare
                    isGameActive = false;
                    if (filledPos[val0] == PLAYER_1)
                        alertDialogGameOver(namePlayer1 + " is the winner", 1);
                    else
                        alertDialogGameOver(namePlayer2 + " is the  winner", 2);

                    //  return;
                }

            }

        }

        if (isGameActive)
            if (checkingNumber == 9) {
                isGameActive = false;
                alertDialogGameOver("Draw", 0);
            }


    }


    private void restartGame() {
        activePlayer = PLAYER_1;
        cardPlayer_2.setStrokeWidth(0);
        cardPlayer_1.setStrokeWidth(2);
        textViewPlayer_1Turn.setText(getString(R.string.txt_your_turn));
        filledPos = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1};

        textViewPlayer_2Turn.setText("");
        textViewPlayer_1Turn.setText("");

        btn0.setImageResource(0);
        btn1.setImageResource(0);
        btn2.setImageResource(0);
        btn3.setImageResource(0);
        btn4.setImageResource(0);
        btn5.setImageResource(0);
        btn6.setImageResource(0);
        btn7.setImageResource(0);
        btn8.setImageResource(0);

        isGameActive = true;
        checkingNumber = 0;
    }

    void alertDialogGameOver(String text, int winnerImage) {

        final View dialogView = View.inflate(this
                , R.layout.dialog_game_over, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);

        Button btnReplay = dialogView.findViewById(R.id.btn_replay);
        TextView textViewPlayerWinner = dialogView.findViewById(R.id.player_winner);
        ImageView imageViewWinner = dialogView.findViewById(R.id.image_winner);

        textViewPlayerWinner.setText(text);
        if (winnerImage == 1)
            imageViewWinner.setImageResource(R.drawable.image_circle);
        else if (winnerImage == 2)
            imageViewWinner.setImageResource(R.drawable.image_cross);
        else
            imageViewWinner.setImageResource(R.drawable.image_handshake);

        btnReplay.setOnClickListener(view -> {
            restartGame();
            alertDialog.dismiss();
        });
        alertDialog.setView(dialogView);
        alertDialog.show();
    }

}