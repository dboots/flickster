package com.donboots.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movie {
    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;
    Double voteAverage;

    String baseImageUrl = "https://image.tmdb.org/t/p/w342/";

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.voteAverage = jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x < jsonArray.length(); x++) {
            try {
                results.add(new Movie(jsonArray.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    public String getBackdropPath() {
        return baseImageUrl + backdropPath;
    }
    public String getPosterPath() {
        return baseImageUrl + posterPath;
    }
    public String getOriginalTitle() {
        return originalTitle;
    }
    public String getOverview() {
        return overview;
    }
    public Double getVoteAverage() { return voteAverage; }
}
