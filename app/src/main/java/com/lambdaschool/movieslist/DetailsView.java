package com.lambdaschool.movieslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lambdaschool.movieslist.apiaccess.MovieDetails;

public class DetailsView extends AppCompatActivity {

    TextView titleText, ratingText;
    MovieDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);

        titleText = findViewById(R.id.title_view);
        ratingText = findViewById(R.id.rating_view);

        int id = getIntent().getIntExtra("MovieId", -1);

        if (id != -1) {
            viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel.class);
            final LiveData<MovieDetails> liveDetailts = viewModel.getDetails(id);
            liveDetailts.observe(this, new Observer<MovieDetails>() {
                @Override
                public void onChanged(@Nullable MovieDetails movieDetails) {
                    if (movieDetails != null) {
                        titleText.setText(movieDetails.getOriginal_title());
                        ratingText.setText(Double.toString(movieDetails.getVote_average()));
                    }
                }
            });
        }

    }
}
