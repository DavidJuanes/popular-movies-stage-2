package com.davidjuanes.popular_movies.two.services;

/**
 * Created by davidgonzalez on 18/01/2017.
 */
public class MovieServiceException extends Exception {
    MovieServiceException(Throwable e)
    {
        super(e);
    }

    MovieServiceException()
    {
        super();
    }
}
