package com.example.homepagetest;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class PoemDetails extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    private Button readButton;
    private Button minusButton;
    private Button plusButton;
    private Button stopButton;
    private float speed = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_detail);

        // Retrieve the selected poem from the intent
        Poem poem = getIntent().getParcelableExtra("selected_poem");

        // Initialize views
        TextView titleTextView = findViewById(R.id.title_text_view);
        TextView authorTextView = findViewById(R.id.author_text_view);
        TextView linesTextView = findViewById(R.id.lines_text_view);
        readButton = findViewById(R.id.read_button);
        minusButton = findViewById(R.id.minus_button);
        plusButton = findViewById(R.id.plus_button);
        stopButton = findViewById(R.id.stop_button);

        // Display poem details
        titleTextView.setText(poem.getTitle());
        authorTextView.setText(poem.getAuthor());

        // Construct lines of the poem
        List<String> lines = poem.getLines();
        StringBuilder linesBuilder = new StringBuilder();
        for (String line : lines) {
            linesBuilder.append(line).append("\n");
        }
        linesTextView.setText(linesBuilder.toString());

        // Initialize TextToSpeech
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.getDefault());
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        // Language is not supported
                        readButton.setEnabled(false);
                    } else {
                        readButton.setEnabled(true);
                    }
                } else {
                    // Initialization failed
                    readButton.setEnabled(false);
                }
            }
        });

        // Set onClickListeners for buttons
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak(poem);
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifySpeed(-0.1f);
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifySpeed(0.1f);
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSpeaking();
            }
        });
    }

    private void speak(Poem poem) {
        textToSpeech.setSpeechRate(speed);
        textToSpeech.speak(poem.getTitle() + " by " + poem.getAuthor(), TextToSpeech.QUEUE_FLUSH, null, null);
        List<String> lines = poem.getLines();
        for (String line : lines) {
            textToSpeech.speak(line, TextToSpeech.QUEUE_ADD, null, null);
        }
    }

    private void modifySpeed(float delta) {
        speed += delta;
        if (speed < 0.1f) {
            speed = 0.1f;
        }
        textToSpeech.setSpeechRate(speed);
    }

    private void stopSpeaking() {
        textToSpeech.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}
