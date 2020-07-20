package com.example.garbagem.Retrofit;

import com.example.garbagem.model.AnnnoucmentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiInterface {

    @GET("announcements")
    Call<List<AnnnoucmentModel>> getRetromodel();
}