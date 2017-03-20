package com.davidjuanes.popular_movies.one.services.themoviedb;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import okhttp3.Request;

import static org.junit.Assert.*;

/**
 * Created by davidgonzalez on 28/01/2017.
 */
public class TheMovieDbConnectorTest {
    private TheMovieDbConnector theMovieDbConnector;

    @Before
    public void setUp() {
        theMovieDbConnector = new TheMovieDbConnector("apikey");
    }

    @Test
    public void getPopularMoviesRequest() throws Exception {
        Request request = theMovieDbConnector.getPopularMoviesRequest();
        Assert.assertEquals("https://api.themoviedb.org/3/movie/popular?api_key=apikey", request.url().toString());
        Assert.assertEquals("GET", request.method());

    }

    @Test
    public void getHighestRatedRequest() throws Exception {
        Request request = theMovieDbConnector.getHighestRatedRequest();
        Assert.assertEquals("https://api.themoviedb.org/3/movie/top_rated?api_key=apikey", request.url().toString());
        Assert.assertEquals("GET", request.method());
    }

}