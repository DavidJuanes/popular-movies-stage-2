package com.davidjuanes.popular_movies.two.domain;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by davidgonzalez on 18/01/2017.
 */

public class Movie implements Serializable {
    private static final String TITLE_TAG = "title";
    private static final String RELEASE_DATE_TAG = "release_date";
    private static final String POSTER_URL_TAG = "poster_path";
    private static final String VOTE_AVERAGE_TAG = "vote_average";
    private static final String SYNOPSIS_TAG = "overview";

    private static final String posterUrlPrexif = "https://image.tmdb.org/t/p/w600";

    private String title;
    private String releaseDate;
    private String posterUrl;
    private Double voteAverage;
    private String synopsis;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    /**
     * It creates a Movie object from the json object of the TheMovieDb API. The json for a movie is:
     *
     * {
     * "poster_path": "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg",
     * "adult": false,
     * "overview": "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.",
     * "release_date": "1994-09-10",
     * "genre_ids": [
     * 18,
     * 80
     * ],
     * "id": 278,
     * "original_title": "The Shawshank Redemption",
     * "original_language": "en",
     * "title": "The Shawshank Redemption",
     * "backdrop_path": "/xBKGJQsAIeweesB79KC89FpBrVr.jpg",
     * "popularity": 6.741296,
     * "vote_count": 5238,
     * "video": false,
     * "vote_average": 8.32
     * }
     * @return a new Movie object filled with the info from the JSON.
     */
    public static Movie fromJson(String json)
    {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Movie movie = new Movie();
            movie.setTitle(jsonObject.getString(TITLE_TAG));
            movie.setSynopsis(jsonObject.getString(SYNOPSIS_TAG));
            movie.setPosterUrl(posterUrlPrexif + jsonObject.getString(POSTER_URL_TAG));
            movie.setReleaseDate(jsonObject.getString(RELEASE_DATE_TAG));
            movie.setVoteAverage(jsonObject.getDouble(VOTE_AVERAGE_TAG));
            return movie;
        } catch (JSONException e) {
            throw new JsonParsingRuntimeException(e);
        }
    }
}
