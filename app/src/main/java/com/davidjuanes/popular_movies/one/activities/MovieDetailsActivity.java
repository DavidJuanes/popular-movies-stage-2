package com.davidjuanes.popular_movies.one.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.davidjuanes.popular_movies.one.R;
import com.davidjuanes.popular_movies.one.domain.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvVoteAverage;
    private TextView tvSynopsis;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
        toolbar.setTitle(movie.getTitle());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        ImageView poster = (ImageView) findViewById(R.id.poster_toolbar);
        Picasso.with(this).load(movie.getPosterUrl()).into(poster);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(movie.getTitle());
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvReleaseDate.setText(movie.getReleaseDate());
        tvVoteAverage = (TextView) findViewById(R.id.tv_vote_average);
        tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        tvSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        tvSynopsis.setText(movie.getSynopsis());

        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setRating(movie.getVoteAverage().floatValue());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

}
