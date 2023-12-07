package com.amal.miniprojet.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amal.miniprojet.R;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AnimeListViewModel extends ArrayAdapter <Anime>{
    private List<Anime> animes;
    private int resource;
    public AnimeListViewModel(@NonNull Context context, int resource,List<Anime> data) {

        super(context, resource,data);
        this.animes=data;
        this.resource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }
        ImageView imageViewAnime = listViewItem.findViewById(R.id.imageViewAnime);
        TextView textViewTitle = listViewItem.findViewById(R.id.titre);

        // Use Picasso for image loading
        Picasso.get().load(animes.get(position).getData().getImages().getJpg().getImageUrl()).into(imageViewAnime);

        textViewTitle.setText(animes.get(position).getData().getTitle());
        return listViewItem;
    }
}