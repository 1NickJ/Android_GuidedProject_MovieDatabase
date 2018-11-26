package com.lambdaschool.movieslist;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.movieslist.apiaccess.MovieDbDao;
import com.lambdaschool.movieslist.apiaccess.MovieDetails;

import java.util.ArrayList;

public class MovieDetailsRepo {
    public static MutableLiveData<MovieDetails> getDetails(int id) {
        final MutableLiveData<MovieDetails> liveDataList =new MutableLiveData<>();
        MovieDetails details = MovieDbDao.getMovieDetails(id);
        liveDataList.setValue(details);
        return liveDataList;
    }

}
