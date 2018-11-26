package com.lambdaschool.movieslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.lambdaschool.movieslist.apiaccess.MovieDetails;

public class MovieDetailsViewModel extends ViewModel {
    private MutableLiveData<MovieDetails> liveDetails;

    public LiveData<MovieDetails> getDetails(int id) {
        if(liveDetails == null) {
            loadData(id);
        }
        return liveDetails;
    }

    private void loadData(int id) {
        liveDetails = MovieDetailsRepo.getDetails(id);
    }
}
