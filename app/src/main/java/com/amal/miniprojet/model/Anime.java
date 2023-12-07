package com.amal.miniprojet.model;

import com.google.gson.annotations.SerializedName;

public class Anime {

    @SerializedName("data")
    private Data data;

    public Data getData() {
        return data;
    }

    public static class Data {

        @SerializedName("mal_id")
        private int malId;

        @SerializedName("title")
        private String title;

        @SerializedName("images")
        private Images images;

        @SerializedName("rank")
        private String rank;

        @SerializedName("duration")
        private String duration;

        @SerializedName("year")
        private int year;

        public int getMalId() {
            return malId;
        }

        public void setMalId(int malId) {
            this.malId = malId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Images getImages() {
            return images;
        }

        public void setImages(Images images) {
            this.images = images;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }
    }

    public static class Images {

        @SerializedName("jpg")
        private ImageUrls jpg;

        public ImageUrls getJpg() {
            return jpg;
        }

        public void setJpg(ImageUrls jpg) {
            this.jpg = jpg;
        }
    }

    public static class ImageUrls {

        @SerializedName("image_url")
        private String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
