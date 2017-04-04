package com.davidjuanes.popular_movies.two.services;

import android.content.Context;
import android.util.Log;

import com.davidjuanes.popular_movies.two.domain.JsonParsingRuntimeException;
import com.davidjuanes.popular_movies.two.domain.Movie;
import com.davidjuanes.popular_movies.two.platform.ApplicationPropertiesLoader;
import com.davidjuanes.popular_movies.two.services.themoviedb.TheMovieDbConnector;
import com.davidjuanes.popular_movies.two.services.themoviedb.TheMovieDbException;

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

    private List<Movie> parseJsonMoviesList(String json) throws MovieServiceException {
        List<Movie> movieList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jArray = jsonObject.getJSONArray("results");

            for(int i=0; i<jArray.length(); i++)
            {
                Movie movie = Movie.fromJson(jArray.getJSONObject(i).toString());
                loadTrailersIntoMovie(movie);
                movieList.add(movie);
            }

        } catch (JSONException e) {
            throw new JsonParsingRuntimeException(e);
        }
        return movieList;
    }

    private void loadTrailersIntoMovie(Movie movie) throws MovieServiceException {
        try {
            String json = theMovieDbConnector.getTrailersForMovie(movie.getId().toString());
            Log.i("MovieService", "Trailers info in JSON: " + json);
            movie.addTrailers(json);
        } catch (Exception e) {
            Log.e("MovieService", e.getMessage());
            //throw new MovieServiceException(e);
        }
    }

}
