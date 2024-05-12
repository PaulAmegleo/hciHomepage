package com.example.homepagetest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PoemDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_detail);

        // Retrieve the selected poem from the intent
        Poem poem = getIntent().getParcelableExtra("selected_poem");

        // Display poem details
        TextView titleTextView = findViewById(R.id.title_text_view);
        TextView authorTextView = findViewById(R.id.author_text_view);

        titleTextView.setText(poem.getTitle());
        authorTextView.setText(poem.getAuthor());
    }
}
