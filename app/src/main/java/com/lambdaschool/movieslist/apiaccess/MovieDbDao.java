package com.lambdaschool.movieslist.apiaccess;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class MovieDbDao {
    private static final String BASE_URL          = "https://api.themoviedb.org/3";
    private static final String API_KEY_ENDING    = "?api_key=b98f8f717026d85eb364fe4ac55cd214";
    private static final String POPULAR_MOVIES    = BASE_URL + "/movie/popular" + API_KEY_ENDING;
    private static final String GET_MOVIE_WITH_ID = BASE_URL + "/movie/%s" + API_KEY_ENDING;

    private static final String IMAGE_URL = "https://image.tmdb.org/t/p/";
    private static final String[] POSTER_SIZES = new String[] {
            "w92",
            "w154",
            "w185",
            "w342",
            "w500",
            "w780",
            "original"};

    private static final String JPG  = ".jpg";
    private static final String JSON = ".json";

    public interface OverviewListCallback {
        public void requestFinished(boolean success, ArrayList<MovieOverview> list);
    }

    public interface MemberDetailsCallback {
        public void requestFinished(boolean success, ArrayList<MovieOverview> list);
    }

    public static ArrayList<MovieOverview> getPopularMovies() {
        ArrayList<MovieOverview> data = new ArrayList<>();

        try {
            String     result        = NetworkAdapter.httpGetRequest(POPULAR_MOVIES);
            JSONObject dataObject    = new JSONObject(result);
            JSONArray  dataJsonArray = dataObject.getJSONArray("results");

            for (int i = 0; i < dataJsonArray.length(); ++i) {
                MovieOverview movieOverview = new MovieOverview(dataJsonArray.getJSONObject(i));
                data.add(movieOverview);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static ArrayList<MovieOverview> getPopularMovies(int page) {
        ArrayList<MovieOverview> data = new ArrayList<>();

        try {
            String     result        = NetworkAdapter.httpGetRequest(POPULAR_MOVIES + "&page=" + page);
            JSONObject dataObject    = new JSONObject(result);
            JSONArray  dataJsonArray = dataObject.getJSONArray("results");

            for (int i = 0; i < dataJsonArray.length(); ++i) {
                MovieOverview movieOverview = new MovieOverview(dataJsonArray.getJSONObject(i));
                data.add(movieOverview);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static MovieDetails getMovieDetails(int movieId) {
        MovieDetails movieDetails;
        try {
            String     result         = NetworkAdapter.httpGetRequest(String.format(GET_MOVIE_WITH_ID, movieId));
            JSONObject dataObject     = new JSONObject(result);

            movieDetails = new MovieDetails(dataObject);

        } catch (JSONException e) {
            e.printStackTrace();
            movieDetails = new MovieDetails();
        }
        return movieDetails;
    }

    public static Bitmap getPoster(String imagePath) {
        return NetworkAdapter.getBitmapFromURL(IMAGE_URL + POSTER_SIZES[6] + imagePath);
    }

    public static Bitmap getImage(String id) {
        return NetworkAdapter.getBitmapFromURL(IMAGE_URL + id + JPG);
    }
}
