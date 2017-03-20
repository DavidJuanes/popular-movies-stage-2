package com.davidjuanes.popular_movies.two.services;

import com.davidjuanes.popular_movies.two.domain.Movie;
import com.davidjuanes.popular_movies.two.services.themoviedb.TheMovieDbConnector;
import com.davidjuanes.popular_movies.two.services.themoviedb.TheMovieDbException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

/**
 * Created by davidgonzalez on 18/01/2017.
 */
public class MovieServiceTest {
    private static final String posterUrlPrefix = "https://image.tmdb.org/t/p/w500";
    private MovieService movieService;

    @Before
    public void setup() throws TheMovieDbException {
        movieService = new MovieService();
        movieService.setTheMovieDbConnector(mockTheMovieDbConnector());
    }

    @Test
    public void retrievePopularMovies() throws Exception {
        List<Movie> movies = movieService.retrievePopularMovies();
        Assert.assertEquals(2, movies.size());

        Movie movie1 = movies.get(0);
        Assert.assertEquals("Suicide Squad", movie1.getTitle());
        Assert.assertTrue(movie1.getSynopsis().contains("From DC Comics comes the Suicide Squad"));
        Assert.assertEquals(posterUrlPrefix + "/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg", movie1.getPosterUrl());
        Assert.assertEquals("2016-08-03", movie1.getReleaseDate());
        Assert.assertEquals(5.91, movie1.getVoteAverage());

        Movie movie2 = movies.get(1);
        Assert.assertEquals("Jason Bourne", movie2.getTitle());
        Assert.assertTrue(movie2.getSynopsis().contains("The most dangerous former operative of the CIA"));
        Assert.assertEquals(posterUrlPrefix + "/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg", movie2.getPosterUrl());
        Assert.assertEquals("2016-07-27", movie2.getReleaseDate());
        Assert.assertEquals(5.25, movie2.getVoteAverage());
    }

    @Test
    public void retrieveTopRatedMovies() throws Exception {
        List<Movie> movies = movieService.retrieveTopRatedMovies();
        Assert.assertEquals(4, movies.size());

        Movie movie1 = movies.get(0);
        Assert.assertEquals("The Shawshank Redemption", movie1.getTitle());
        Assert.assertTrue(movie1.getSynopsis().contains("Framed in the 1940s for the double murder of his"));
        Assert.assertEquals(posterUrlPrefix + "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg", movie1.getPosterUrl());
        Assert.assertEquals("1994-09-10", movie1.getReleaseDate());
        Assert.assertEquals(8.32, movie1.getVoteAverage());

        Movie movie2 = movies.get(1);
        Assert.assertEquals("Whiplash", movie2.getTitle());
        Assert.assertTrue(movie2.getSynopsis().contains("Under the direction of a ruthless instructor"));
        Assert.assertEquals(posterUrlPrefix + "/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg", movie2.getPosterUrl());
        Assert.assertEquals("2014-10-10", movie2.getReleaseDate());
        Assert.assertEquals(8.29, movie2.getVoteAverage());

        Movie movie3 = movies.get(2);
        Assert.assertEquals("The Godfather", movie3.getTitle());
        Assert.assertTrue(movie3.getSynopsis().contains("chronicles the fictional Italian-American Corleone crime family"));
        Assert.assertEquals(posterUrlPrefix + "/d4KNaTrltq6bpkFS01pYtyXa09m.jpg", movie3.getPosterUrl());
        Assert.assertEquals("1972-03-15", movie3.getReleaseDate());
        Assert.assertEquals(8.26, movie3.getVoteAverage());

        Movie movie4 = movies.get(3);
        Assert.assertEquals("Spirited Away", movie4.getTitle());
        Assert.assertTrue(movie4.getSynopsis().contains("Oscar winning Japanese animated film about a ten year old girl"));
        Assert.assertEquals(posterUrlPrefix + "/ynXoOxmDHNQ4UAy0oU6avW71HVW.jpg", movie4.getPosterUrl());
        Assert.assertEquals("2001-07-20", movie4.getReleaseDate());
        Assert.assertEquals(8.15, movie4.getVoteAverage());
    }

    private TheMovieDbConnector mockTheMovieDbConnector() throws TheMovieDbException {
        String topRatedJson = "{ \"page\": 1, \"results\": [ { \"poster_path\": \"/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg\"" +
                ", \"adult\": false, \"overview\": \"Framed in the 1940s for the double murder of his wife and her lover," +
                " upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting" +
                " skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by" +
                " the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of" +
                " hope.\", \"release_date\": \"1994-09-10\", \"genre_ids\": [ 18, 80 ], \"id\": 278, \"original_title\": " +
                "\"The Shawshank Redemption\", \"original_language\": \"en\", \"title\": \"The Shawshank Redemption\", " +
                "\"backdrop_path\": \"/xBKGJQsAIeweesB79KC89FpBrVr.jpg\", \"popularity\": 6.741296, \"vote_count\": 5238, " +
                "\"video\": false, \"vote_average\": 8.32 }, { \"poster_path\": \"/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg\", " +
                "\"adult\": false, \"overview\": \"Under the direction of a ruthless instructor, a talented young drummer " +
                "begins to pursue perfection at any cost, even his humanity.\", \"release_date\": \"2014-10-10\", " +
                "\"genre_ids\": [ 18, 10402 ], \"id\": 244786, \"original_title\": \"Whiplash\", \"original_language\": " +
                "\"en\", \"title\": \"Whiplash\", \"backdrop_path\": \"/6bbZ6XyvgfjhQwbplnUh1LSj1ky.jpg\", \"popularity" +
                "\": 10.776056, \"vote_count\": 2059, \"video\": false, \"vote_average\": 8.29 }, { \"poster_path\": " +
                "\"/d4KNaTrltq6bpkFS01pYtyXa09m.jpg\", \"adult\": false, \"overview\": \"The story spans the years " +
                "from 1945 to 1955 and chronicles the fictional Italian-American Corleone crime family. When organized " +
                "crime family patriarch Vito Corleone barely survives an attempt on his life, his youngest son, Michael, " +
                "steps in to take care of the would-be killers, launching a campaign of bloody revenge.\", \"release_date" +
                "\": \"1972-03-15\", \"genre_ids\": [ 18, 80 ], \"id\": 238, \"original_title\": \"The Godfather\", " +
                "\"original_language\": \"en\", \"title\": \"The Godfather\", \"backdrop_path\": \"/6xKCYgH16UuwEGAyroLU" +
                "6p8HLIn.jpg\", \"popularity\": 4.554654, \"vote_count\": 3570, \"video\": false, \"vote_average\": 8.26" +
                " }, { \"poster_path\": \"/ynXoOxmDHNQ4UAy0oU6avW71HVW.jpg\", \"adult\": false, \"overview\": \"Spirited" +
                " Away is an Oscar winning Japanese animated film about a ten year old girl who wanders away from her pa" +
                "rents along a path that leads to a world ruled by strange and unusual monster-like animals. Her parents" +
                " have been changed into pigs along with others inside a bathhouse full of these creatures. Will she eve" +
                "r see the world how it once was?\", \"release_date\": \"2001-07-20\", \"genre_ids\": [ 14, 12, 16, 10751" +
                " ], \"id\": 129, \"original_title\": \"千と千尋の神隠し\", \"original_language\": \"ja\", \"title\": \"Spir" +
                "ited Away\", \"backdrop_path\": \"/djgM2d3e42p9GFQObg6lwK2SVw2.jpg\", \"popularity\": 6.886678, \"vote_c" +
                "ount\": 2000, \"video\": false, \"vote_average\": 8.15 } ], \"total_results\": 5206, \"total_pages\": 2" +
                "61 }";

        String popularJson="{ \"page\": 1, \"results\": [ { \"poster_path\": \"/e1mjopzAS2KNsvpbpahQ1a6SkSn.jpg\", \"adult\": " +
                "false, \"overview\": \"From DC Comics comes the Suicide Squad, an antihero team of incarcerated supervillains" +
                " who act as deniable assets for the United States government, undertaking high-risk black ops missions in excha" +
                "nge for commuted prison sentences.\", \"release_date\": \"2016-08-03\", \"genre_ids\": [ 14, 28, 80 ], \"id\": " +
                "297761, \"original_title\": \"Suicide Squad\", \"original_language\": \"en\", \"title\": \"Suicide Squad\", \"b" +
                "ackdrop_path\": \"/ndlQ2Cuc3cjTL7lTynw6I4boP4S.jpg\", \"popularity\": 48.261451, \"vote_count\": 1466, \"video\": " +
                "false, \"vote_average\": 5.91 }, { \"poster_path\": \"/lFSSLTlFozwpaGlO31OoUeirBgQ.jpg\", \"adult\": false, \"ove" +
                "rview\": \"The most dangerous former operative of the CIA is drawn out of hiding to uncover hidden truths about h" +
                "is past.\", \"release_date\": \"2016-07-27\", \"genre_ids\": [ 28, 53 ], \"id\": 324668, \"original_title\": \"Jas" +
                "on Bourne\", \"original_language\": \"en\", \"title\": \"Jason Bourne\", \"backdrop_path\": \"/AoT2YrJUJlg5vKE3iM" +
                "OLvHlTd3m.jpg\", \"popularity\": 30.690177, \"vote_count\": 649, \"video\": false, \"vote_average\": 5.25 } ], \"" +
                "total_results\": 19629, \"total_pages\": 982 }";

        TheMovieDbConnector connector = Mockito.mock(TheMovieDbConnector.class);
        Mockito.when(connector.getPopularMovies()).thenReturn(popularJson);
        Mockito.when(connector.getTopRatedMovies()).thenReturn(topRatedJson);

        return connector;
    }

}