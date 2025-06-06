package com.example.testnhanh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    private int luckyNumber; // Instance variable for lucky number

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView luckyNumberText = findViewById(R.id.luckyNumber);
        Button shareButton = findViewById(R.id.shareButton);

        // Generate a random number between 1 and 999
        Random random = new Random();
        luckyNumber = random.nextInt(999) + 1;
        luckyNumberText.setText(String.valueOf(luckyNumber));

        // Get the user's name from the intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME");
        if (userName == null || userName.trim().isEmpty()) {
            userName = "Someone"; // Fallback name if null or empty
        }

        // Make userName final for use in lambda
        final String finalUserName = userName;

        // Set up the share button using a lambda expression
        shareButton.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, finalUserName + "'s lucky number is " + luckyNumber);
            startActivity(Intent.createChooser(shareIntent, "Share your lucky number"));
        });
    }
}