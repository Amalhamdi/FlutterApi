package com.amal.miniprojet.model.service;

import com.amal.miniprojet.model.Anime;
import com.amal.miniprojet.model.AnimeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AnimeRepoService {

    @GET("anime/{id}")
    Call <Anime> searchAnime(@Path("id") Integer id);
}
