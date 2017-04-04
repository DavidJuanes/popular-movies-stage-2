package com.davidjuanes.popular_movies.two.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.davidjuanes.popular_movies.two.R;
import com.davidjuanes.popular_movies.two.domain.Movie;
import com.davidjuanes.popular_movies.two.domain.YoutubeVideo;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvVoteAverage;
    private TextView tvSynopsis;
    private RatingBar ratingBar;
    private LinearLayout detailsContentLayout;

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

        detailsContentLayout = (LinearLayout) findViewById(R.id.movie_details_content_container);

        for (YoutubeVideo trailer : movie.getTrailers()) {
            CardView cardView = new CardView(this);
            // Set the CardView layoutParams
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(15, 30, 15, 0);
            cardView.setLayoutParams(params);


            // Set CardView corner radius
            cardView.setRadius(9);

            // Initialize a new ImageView to put in CardView
            ViewGroup.LayoutParams paramsImage = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            ImageView trailerImage = new ImageView(this);
            trailerImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE );
            trailerImage.setAdjustViewBounds(true);
            trailerImage.setLayoutParams(paramsImage);
            Picasso.with(this).load(trailer.getVideoImageUrl()).into(trailerImage);

            cardView.addView(trailerImage);

            // Set cardView content padding
            cardView.setContentPadding(15, 15, 15, 15);
            detailsContentLayout.addView(cardView);
        }
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
