package com.davidjuanes.popular_movies.two.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidgonzalez on 24/05/2017.
 */
public class MovieList extends ArrayList<Movie> implements List<Movie>, Serializable {
}
