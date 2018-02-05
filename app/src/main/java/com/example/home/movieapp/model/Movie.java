package com.example.home.movieapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Home on 2.2.2018..
 */

public class Movie {
    @SerializedName("id") private String id;
    @SerializedName("TITLE") private String title;
    @SerializedName("YEAR") private String year;
    @SerializedName("GENRE")  private String genre;
    @SerializedName("DIRECTOR") private String director;
    @SerializedName("ACTORS") private String actors;
    @SerializedName("AWARDS")  private  String awards;
    @SerializedName("POSTER")  private  String poster;
    @SerializedName("IMDBRATING") private double imdbRating;
    @SerializedName("MY_RATE") private String myRate;
    @SerializedName("MY_COMMENT") private String myComment;
    @SerializedName("USERID") private Integer userId;
    @SerializedName("WATCHED") private  Boolean watched;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getWatched() {
        return watched;
    }

    public void setWatched(Boolean watched) {
        this.watched = watched;
    }

    public Movie(String id, String title, String year, String genre, String director, String actors, String awards, String poster, double imdbRating, String myRate, String myComment, Integer userId, Boolean watched) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.awards = awards;
        this.poster = poster;
        this.imdbRating = imdbRating;
        this.myRate = myRate;
        this.myComment = myComment;
        this.userId = userId;
        this.watched = watched;
    }

    public Movie(String id, String title, double imdbRating)
    {
        this.id = id;
        this.title = title;
        this.imdbRating = imdbRating;
    }

    public String getMyRate() {
        return myRate;
    }

    public void setMyRate(String myRate) {
        this.myRate = myRate;
    }

    public String getMyComment() {
        return myComment;
    }

    public void setMyComment(String myComment) {
        this.myComment = myComment;
    }

    public Movie()
    {

    }
    public Movie(String id, String title, String year, String genre, String director, String actors, String awards, String poster, double imdbRating) {
        this.id = id;
        this.title = title;
        this.year = year;

        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.awards = awards;
        this.poster = poster;
        this.imdbRating = imdbRating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }
}
