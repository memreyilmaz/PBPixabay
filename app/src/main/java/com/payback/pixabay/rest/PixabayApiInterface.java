package com.payback.pixabay.rest;

import com.payback.pixabay.model.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApiInterface {
    String KEY = "?key=11868336-ddbb35d81dbcb178d2e654d7d";

    @GET(KEY)
    Call<ImageResponse> getSearched(
            @Query("q") String query,
            @Query("image_type") String photo);
}