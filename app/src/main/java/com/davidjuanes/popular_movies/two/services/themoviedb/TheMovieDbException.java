package com.davidjuanes.popular_movies.two.services.themoviedb;

/**
 * Created by davidgonzalez on 18/01/2017.
 */

public class TheMovieDbException extends Exception {

    public TheMovieDbException(Throwable exception)
    {
        super(exception);
    }

    public TheMovieDbException()
    {
        super();
    }
}
