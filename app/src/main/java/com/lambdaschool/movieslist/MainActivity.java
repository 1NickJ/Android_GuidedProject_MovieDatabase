package com.lambdaschool.movieslist;

import android.app.Activity;
import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lambdaschool.movieslist.apiaccess.MovieOverview;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout           parentLayout;
    MovieOverviewViewModel viewModel;
    Context                context;
    Activity               activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ThemeUtils.onActivityCreateSetTheme(activity);
        setContentView(R.layout.activity_main);

//        parentLayout = findViewById(R.id.parent_layout);
        context = this;

        findViewById(R.id.toggle_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemeUtils.toggleTheme(activity);
            }
        });

        viewModel = ViewModelProviders.of(this).get(MovieOverviewViewModel.class);
        viewModel.getOverViewList().observe(this, new Observer<ArrayList<MovieOverview>>() {
            @Override
            public void onChanged(@Nullable ArrayList<MovieOverview> movieOverviews) {
                if(movieOverviews != null) {
                    for(MovieOverview movie: movieOverviews) {
                        parentLayout.addView(getDefaultTextView(movie));
                    }
                }
            }
        });
    }

    TextView getDefaultTextView(final MovieOverview movie) {
        TextView     view        = new TextView(context);
        final String releaseYear = movie.getRelease_date().split("-")[0];
        String       displayText = String.format("%s (%s)", movie.getTitle(), releaseYear);
        view.setText(displayText);
        view.setTextSize(28);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = movie.getId();
                Log.i("MovieElement", Integer.toString(id));

                Intent intent = new Intent(context, DetailsView.class);
                intent.putExtra("MovieId", id);
                startActivity(intent);
            }
        });
        return view;
    }
}
