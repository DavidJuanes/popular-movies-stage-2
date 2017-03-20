package com.davidjuanes.popular_movies.two.services.themoviedb;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Connector to the TheMovieDb. The responses of the API are returned in plain JSON.
 */

public class TheMovieDbConnector
{
    private static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    private static final String POPULAR_MOVIES_ENDPOINT = "movie/popular";
    private static final String HIGHEST_RATED_MOVIES_ENDPOINT = "movie/top_rated";
    private String apiKey;
    private OkHttpClient client = new OkHttpClient();


    public TheMovieDbConnector(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getPopularMovies() throws TheMovieDbException {
        return performRequest(getPopularMoviesRequest());
    }

    public String getTopRatedMovies() throws TheMovieDbException {
        return performRequest(getHighestRatedRequest());
    }

    private String performRequest(Request request) throws TheMovieDbException {
        try {
            Log.i("MovieDbConnector", "Performing HTTP Request to: " + request.method() + " " + request.url());
            Response response = client.newCall(request).execute();
            if (response.code() != 200)
            {
                throw new TheMovieDbException();
            }
            return response.body().string();
        } catch (IOException e) {
            throw new TheMovieDbException(e);
        }
    }

    public Request getPopularMoviesRequest()
    {
        return new Request.Builder().url(API_BASE_URL + POPULAR_MOVIES_ENDPOINT + getApiKeyParameter()).build();
    }

    public Request getHighestRatedRequest()
    {
        return new Request.Builder().url(API_BASE_URL + HIGHEST_RATED_MOVIES_ENDPOINT + getApiKeyParameter()).build();
    }

    private String getApiKeyParameter()
    {
        return "?api_key=" + apiKey;
    }
}
