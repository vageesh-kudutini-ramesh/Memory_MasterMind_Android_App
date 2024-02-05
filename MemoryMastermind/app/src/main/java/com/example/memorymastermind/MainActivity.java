package com.example.memorymastermind;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // About button click
        findViewById(R.id.aboutButton).setOnClickListener(v -> showAboutDialog());

        // Clicky Clicky button click
        findViewById(R.id.clickyClickyButton).setOnClickListener(v -> startGameActivity());
    }

    private void showAboutDialog() {
        // Create a dialog to display your name and email
        // You can customize this dialog based on your design preferences
        AboutDialogFragment dialogFragment = new AboutDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "AboutDialog");
    }

    private void startGameActivity() {
        // Start the GameActivity when Clicky Clicky button is clicked
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}
