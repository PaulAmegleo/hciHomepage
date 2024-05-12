package com.example.homepagetest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PoemAPI {
    @GET("random/10")
    Call<List<Poem>> getRandomPoems();
}