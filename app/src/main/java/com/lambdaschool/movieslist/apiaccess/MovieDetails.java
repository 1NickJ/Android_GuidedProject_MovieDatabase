package com.lambdaschool.movieslist.apiaccess;

import org.json.JSONException;
import org.json.JSONObject;

public class MovieDetails {
    private Long    budget;
    private double  vote_average;
    private String  backdrop_path;
    private String  status;
    private int     runtime;
    private boolean adult;
    private String  homepage;
    private int     id;
    private String  title;
    private String  original_language;
    private String  overview;
    private String  imdb_id;
    private String  release_date;
    private String  original_title;
    private int     vote_count;
    private String  poster_path;
    private boolean video;
    private String  tagline;
    private long    revenue;
    private double  popularity;

    public MovieDetails() {
    }

    public MovieDetails(JSONObject json) {
        try {
            this.budget = json.getLong("budget");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.vote_average = json.getDouble("vote_average");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.backdrop_path = json.getString("backdrop_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.status = json.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.runtime = json.getInt("runtime");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.adult = json.getBoolean("adult");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.homepage = json.getString("homepage");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.id = json.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.original_language = json.getString("original_language");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.overview = json.getString("overview");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.imdb_id = json.getString("imdb_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.release_date = json.getString("release_date");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.original_title = json.getString("original_title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.vote_count = json.getInt("vote_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.poster_path = json.getString("poster_path");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.video = json.getBoolean("video");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.tagline = json.getString("tagline");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.revenue = json.getLong("revenue");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            this.popularity = json.getDouble("popularity");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Long getBudget() {
        return budget;
    }

    public double getVote_average() {
        return vote_average;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getStatus() {
        return status;
    }

    public int getRuntime() {
        return runtime;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getHomepage() {
        return homepage;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOverview() {
        return overview;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public boolean isVideo() {
        return video;
    }

    public String getTagline() {
        return tagline;
    }

    public long getRevenue() {
        return revenue;
    }

    public double getPopularity() {
        return popularity;
    }
}


