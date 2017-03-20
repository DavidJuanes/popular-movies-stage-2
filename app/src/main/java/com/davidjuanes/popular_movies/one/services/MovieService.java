package com.davidjuanes.popular_movies.one.services;

import android.content.Context;

import com.davidjuanes.popular_movies.one.domain.JsonParsingRuntimeException;
import com.davidjuanes.popular_movies.one.domain.Movie;
import com.davidjuanes.popular_movies.one.platform.ApplicationPropertiesLoader;
import com.davidjuanes.popular_movies.one.services.themoviedb.TheMovieDbConnector;
import com.davidjuanes.popular_movies.one.services.themoviedb.TheMovieDbException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Business service that retrieves movie information.
 */

public class MovieService {

    private TheMovieDbConnector theMovieDbConnector;

    public void setTheMovieDbConnector(TheMovieDbConnector theMovieDbConnector) {
        this.theMovieDbConnector = theMovieDbConnector;
    }

    public MovieService(Context context)
    {
        theMovieDbConnector = new TheMovieDbConnector(ApplicationPropertiesLoader.getApiKey(context));
    }

    /**
     * Only used for unit testing
     */
    @Deprecated
    public MovieService()
    {

    }

    public List<Movie> retrievePopularMovies() throws MovieServiceException {
        try {
            String json = theMovieDbConnector.getPopularMovies();
            return parseJsonMoviesList(json);
        } catch (TheMovieDbException e) {
            throw new MovieServiceException(e);
        }
    }

    public List<Movie> retrieveTopRatedMovies() throws MovieServiceException {
        try {
            String json = theMovieDbConnector.getTopRatedMovies();
            return parseJsonMoviesList(json);
        } catch (TheMovieDbException e) {
            throw new MovieServiceException(e);
        }
    }

    private List<Movie> parseJsonMoviesList(String json)
    {
        List<Movie> movieList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jArray = jsonObject.getJSONArray("results");

            for(int i=0; i<jArray.length(); i++)
            {
                Movie movie = Movie.fromJson(jArray.getJSONObject(i).toString());
                movieList.add(movie);
            }

        } catch (JSONException e) {
            throw new JsonParsingRuntimeException(e);
        }
        return movieList;
    }

}
