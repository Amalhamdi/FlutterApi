package com.amal.miniprojet.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class AnimeResponse {
    @SerializedName("last_visible_page")
    public int lastVisiblePage;

    @SerializedName("has_next_page")
    public boolean hasNextPage;

    @SerializedName("current_page")
    public int currentPage;
    @SerializedName("items")
    public List<Anime> animes = new ArrayList<>();

    public static class Items {
        @SerializedName("count")
        public int count;

        @SerializedName("total")
        public int total;

        @SerializedName("per_page")
        public int perPage;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }
    }
    public List<Anime> getAnimes() {
        return animes;
    }

    public void setAnimes(List<Anime> animes) {
        this.animes = animes;
    }
    public int getLastVisiblePage() {
        return lastVisiblePage;
    }

    public void setLastVisiblePage(int lastVisiblePage) {
        this.lastVisiblePage = lastVisiblePage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

}
