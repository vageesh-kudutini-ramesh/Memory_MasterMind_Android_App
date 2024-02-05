package com.example.memorymastermind;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private TextView pressedText;
    private TextView additionalText;
    private TextView scoreText;

    private List<String> sequence = new ArrayList<>();
    private int currentIndex = 0;
    private int score = 0;

    private boolean isSequenceGenerated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pressedText = findViewById(R.id.pressedText);
        additionalText = findViewById(R.id.additionalText);
        Button goButton = findViewById(R.id.goButton);
        scoreText = findViewById(R.id.scoreText);

        // Buttons A, B, C, D, E, and F
        final Button buttonA = findViewById(R.id.buttonA);
        final Button buttonB = findViewById(R.id.buttonB);
        final Button buttonC = findViewById(R.id.buttonC);
        final Button buttonD = findViewById(R.id.buttonD);
        final Button buttonE = findViewById(R.id.buttonE);
        final Button buttonF = findViewById(R.id.buttonF);

        View.OnClickListener buttonClickListener = v -> {
            String buttonText = ((Button) v).getText().toString();
            pressedText.setText("Pressed: " + buttonText);
            if (isSequenceGenerated) {
                checkSequence(buttonText);
            }
        };

        // Set the click listener for each button
        buttonA.setOnClickListener(buttonClickListener);
        buttonB.setOnClickListener(buttonClickListener);
        buttonC.setOnClickListener(buttonClickListener);
        buttonD.setOnClickListener(buttonClickListener);
        buttonE.setOnClickListener(buttonClickListener);
        buttonF.setOnClickListener(buttonClickListener);

        // Set click listener for the "Go" button
        goButton.setOnClickListener(v -> startNewSequence());

        // Set initial text for the "Pressed," additional text, and score
        pressedText.setText("Pressed: - ");
        additionalText.setText("Additional Text: ");
        scoreText.setText("Score: 0");
    }

    private void startNewSequence() {
        // Generate a random sequence of 3 letters (A - F)
        List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F");
        Collections.shuffle(letters);
        sequence = new ArrayList<>(letters.subList(0, 3));

        // Display the sequence in the "Pressed" text
        pressedText.setText("Pressed: " + TextUtils.join(" ", sequence));

        // Enable buttons for user input
        enableButtons(true);
        isSequenceGenerated = true;

        // Reset the index
        currentIndex = 0;
    }

    private void checkSequence(String buttonPressed) {
        // Check if the pressed button matches the current position in the sequence
        if (buttonPressed.equals(sequence.get(currentIndex))) {
            currentIndex++;

            // Check if the user completed the sequence
            if (currentIndex == sequence.size()) {
                // Disable buttons temporarily
                enableButtons(false);

                // Show a success message
                additionalText.setText("Success! Go again.");

                // Delay the start of the new sequence for a short time
                new Handler().postDelayed(() -> {
                    startNewSequence();
                    additionalText.setText("");
                }, 1000);

                // Update the score
                score += 10;
                scoreText.setText("Score: " + score);
            }
        } else {
            // Disable buttons
            enableButtons(false);

            // Show a failure message
            additionalText.setText("You Lost. Try again.");

            // Allow the user to start a new sequence after a delay
            new Handler().postDelayed(() -> {
                startNewSequence();
                additionalText.setText("");
            }, 2000);
        }
    }

    private void enableButtons(boolean enabled) {
        // Enable or disable all buttons
        findViewById(R.id.buttonA).setEnabled(enabled);
        findViewById(R.id.buttonB).setEnabled(enabled);
        findViewById(R.id.buttonC).setEnabled(enabled);
        findViewById(R.id.buttonD).setEnabled(enabled);
        findViewById(R.id.buttonE).setEnabled(enabled);
        findViewById(R.id.buttonF).setEnabled(enabled);
    }
}
