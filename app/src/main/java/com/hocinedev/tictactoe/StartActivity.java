package com.hocinedev.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {

    private EditText editTextPlayer1, editTextPlayer2;
    private Button btnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Disable night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        editTextPlayer1 = findViewById(R.id.editPlayer1);
        editTextPlayer2 = findViewById(R.id.editPlayer2);
        btnPlay = findViewById(R.id.btn_play);

        btnPlay.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            String namePlayer1 = editTextPlayer1.getText().toString();
            String namePlayer2 = editTextPlayer2.getText().toString();
            if (namePlayer1.equals(""))
                intent.putExtra(getString(R.string.txt_player_1), getString(R.string.txt_player_1));
            else
                intent.putExtra(getString(R.string.txt_player_1), namePlayer1);
            if (namePlayer2.equals(""))
                intent.putExtra(getString(R.string.txt_player_2), getString(R.string.txt_player_2));
            else
                intent.putExtra(getString(R.string.txt_player_2), namePlayer2);

            startActivity(intent);
        });
    }
}