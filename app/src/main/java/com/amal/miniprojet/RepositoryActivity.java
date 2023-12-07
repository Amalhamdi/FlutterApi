package com.amal.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RepositoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.repository_layout);
        setTitle("details");
        Intent intent= getIntent();
        String title = intent.getStringExtra("anime.title");
        String duree = intent.getStringExtra("anime.duree");
        String year = intent.getStringExtra("anime.year");
        String rank = intent.getStringExtra("anime.rank");

        TextView textViewTitre = findViewById(R.id.textViewTitle);
        TextView textViewDuree = findViewById(R.id.textViewDuree);
        TextView textViewYear = findViewById(R.id.textViewYear);
        TextView textViewRank = findViewById(R.id.textViewRank);

        textViewTitre.setText(title);
        textViewDuree.setText(duree);
        textViewYear.setText(year);
        textViewRank.setText(rank);

    }
}
