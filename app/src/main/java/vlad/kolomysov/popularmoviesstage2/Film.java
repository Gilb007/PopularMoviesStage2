package vlad.kolomysov.popularmoviesstage2;

/**
 * Copyright (C) Created by Vlad Kolomysov on 30.09.15.
 */
// 2
// Model for one Movie
// Movie = Film
public class Film
{
    //String adult;
    //String backdrop_path;
    //Integer[] genre_ids;
    String id;
    //String original_language;
    String original_title;
    String overview;
    //String popularity;
    String poster_path;
    String release_date;
    //String title;
    //String video;
    String vote_average;
    //String vote_count;


    public Film() {

    }

    public Film(String id, String original_title, String overview, String poster_path, String release_date, String vote_average) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
