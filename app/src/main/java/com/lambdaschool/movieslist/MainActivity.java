package com.lambdaschool.movieslist;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lambdaschool.movieslist.apiaccess.MovieDbDao;
import com.lambdaschool.movieslist.apiaccess.MovieOverview;



import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ScrollView             parentLayout;
    MovieOverviewViewModel viewModel;
    Context                context;
    Activity               activity;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        ThemeUtils.onActivityCreateSetTheme(activity);
        setContentView(R.layout.activity_main);

        parentLayout = findViewById(R.id.scroll_view);
        context = this;

        findViewById(R.id.toggle_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemeUtils.toggleTheme(activity);
            }
        });
        progressBar = findViewById(R.id.progress_bar);

        (new GetMoviesTask()).execute();

        /*(new Thread(new Runnable() {
            @Override
            public void run() {
                viewModel = ViewModelProviders.of((FragmentActivity) activity).get(MovieOverviewViewModel.class);
                viewModel.getOverViewList().observe((LifecycleOwner) activity, new Observer<ArrayList<MovieOverview>>() {
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
        })).start();*/


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

    public class GetMoviesTask extends AsyncTask<Void, Integer, View> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(25);
        }

        @Override
        protected void onPostExecute(View view) {
            super.onPostExecute(view);
            progressBar.setVisibility(View.GONE);
            parentLayout.addView(view);
        }

        @Override
        protected View doInBackground(Void... voids) {
            ArrayList<MovieOverview> movieOverviews = MovieDbDao.getPopularMovies();
            LinearLayout view = new LinearLayout(context);
            view.setOrientation(LinearLayout.VERTICAL);
            if(movieOverviews != null) {
                for(int i = 0; i < movieOverviews.size(); ++i) {
                    (view).addView(getDefaultTextView(movieOverviews.get(i)));
                    publishProgress(i+5);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return view;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
//            parentLayout.addView(values[0]);
            progressBar.setProgress(values[0]);
        }
    }
}
