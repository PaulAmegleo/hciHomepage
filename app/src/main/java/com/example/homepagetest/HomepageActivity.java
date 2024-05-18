package com.example.homepagetest;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomepageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PoemAPI poemAPI;
    private PoemAdapter poemAdapter;
    private SearchView searchView; // Add search view
    private FloatingActionButton refreshButton; // Add refresh button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = findViewById(R.id.poem_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        poemAdapter = new PoemAdapter(new ArrayList<>(), this); // Initialize adapter with empty list
        recyclerView.setAdapter(poemAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://poetrydb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        poemAPI = retrofit.create(PoemAPI.class);

        loadRandomPoems();






        // Add refresh button
        refreshButton = findViewById(R.id.refresh_button);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRandomPoems();
            }
        });
    }

    private void loadRandomPoems() {
        poemAPI.getRandomPoems().enqueue(new Callback<List<Poem>>() {
            @Override
            public void onResponse(Call<List<Poem>> call, Response<List<Poem>> response) {
                List<Poem> poems = response.body();
                if (poems != null) {
                    poemAdapter.updatePoems(poems);
                }
            }

            @Override
            public void onFailure(Call<List<Poem>> call, Throwable t) {
                // Handle error
            }
        });
    }
}