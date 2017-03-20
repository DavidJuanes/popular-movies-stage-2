package com.davidjuanes.popular_movies.one;

import android.app.Application;

import com.davidjuanes.popular_movies.one.services.MovieService;

/**
 * Application class for this app.
 *
 * I'll use this class to keep a reference to the singleton services.
 */

public class PopularMoviesApp extends Application {
    private static MovieService movieService;

    @Override
    public void onCreate() {
        super.onCreate();
        movieService = new MovieService(this);
    }

    /**
     * Getter for the Movie Service
     * @return the movie service
     */
    public static MovieService getMovieService() {
        return movieService;
    }
}
