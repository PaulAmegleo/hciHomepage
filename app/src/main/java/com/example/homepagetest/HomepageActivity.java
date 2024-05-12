package com.example.homepagetest;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomepageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PoemAPI poemAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://poetrydb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        poemAPI = retrofit.create(PoemAPI.class);

        loadRandomPoems();
    }

    private void loadRandomPoems() {
        poemAPI.getRandomPoems().enqueue(new Callback<List<Poem>>() {
            @Override
            public void onResponse(Call<List<Poem>> call, Response<List<Poem>> response) {
                List<Poem> poems = response.body();
                if (poems != null) {
                    PoemAdapter adapter = new PoemAdapter(poems, HomepageActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Poem>> call, Throwable t) {
                // Handle error
            }
        });
    }
}
