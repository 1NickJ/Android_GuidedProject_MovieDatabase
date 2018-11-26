package com.lambdaschool.movieslist;

import android.arch.lifecycle.MutableLiveData;

import com.lambdaschool.movieslist.apiaccess.MovieDbDao;
import com.lambdaschool.movieslist.apiaccess.MovieOverview;

import java.util.ArrayList;

public class MovieOverviewRepo {
    public static MutableLiveData<ArrayList<MovieOverview>> getOverviewList() {
        final MutableLiveData<ArrayList<MovieOverview>> liveDataList =new MutableLiveData<>();
        ArrayList<MovieOverview> overviews = MovieDbDao.getPopularMovies();
        liveDataList.setValue(overviews);
        return liveDataList;
    }
}
