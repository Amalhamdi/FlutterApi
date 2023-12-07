package com.amal.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.amal.miniprojet.model.Anime;
import com.amal.miniprojet.model.AnimeListViewModel;
import com.amal.miniprojet.model.AnimeResponse;
import com.amal.miniprojet.model.service.AnimeRepoService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<Anime> data;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("animes");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        EditText editTextQuery = findViewById(R.id.search);
        Button buttonSearch = findViewById(R.id.button);

        ListView listViewAnime = findViewById(R.id.listViewAnime);

        data = new ArrayList<>();
     //   arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        AnimeListViewModel listViewModel=new AnimeListViewModel(this,R.layout.anime_list_view_layout,data);
        listViewAnime.setAdapter(listViewModel);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.jikan.moe/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AnimeRepoService animeRepoService = retrofit.create(AnimeRepoService.class);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = editTextQuery.getText().toString();
                int animeId = Integer.parseInt(query);

                Call<Anime> callAnime = animeRepoService.searchAnime(animeId);
                callAnime.enqueue(new Callback<Anime>() {
                    @Override
                    public void onResponse(Call<Anime> call, Response<Anime> response) {
                        Log.i("API Request", "URL: " + call.request().url().toString());

                        if (response.isSuccessful() && response.body() != null) {
                            Log.i("successful", "API works");

                            Anime anime = response.body();
                            if (anime != null && anime.getData() != null) {
                                Log.i("successful", anime.getData().toString());

                                Anime.Data animeData = anime.getData();
                                if (animeData.getTitle() != null) {
                                    Log.i("title =", animeData.getTitle());
                                    data.clear();
                                    data.add(anime);
                                    listViewModel.notifyDataSetChanged();
                                } else {
                                    Log.e("API Response", "Anime title is null");
                                }
                            } else {
                                Log.e("API Response", "Empty list of Animes");
                            }
                        } else {
                            Log.e("API Response", "Code: " + response.code() + ", Body: " + response.errorBody());
                        }
                    }

                    @Override
                    public void onFailure(Call<Anime> call, Throwable t) {
                        Log.e("API Failure", "Error", t);
                        // Handle failure or show a user-friendly message
                    }
                });
            }
        });

        listViewAnime.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String duree=data.get(position).getData().getDuration();
                String title=data.get(position).getData().getTitle();
                String rank=data.get(position).getData().getRank();
                String year= String.valueOf(data.get(position).getData().getYear());

                Log.i("info",duree);
                Intent intent = new Intent(getApplicationContext(),RepositoryActivity.class);
                intent.putExtra("anime.title",title);
                intent.putExtra("anime.duree",duree);
                intent.putExtra("anime.year",year);
                intent.putExtra("anime.rank",rank);

                startActivity(intent);

            }
        });
    }
}
