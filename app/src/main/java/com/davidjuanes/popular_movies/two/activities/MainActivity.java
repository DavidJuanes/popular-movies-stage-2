package com.davidjuanes.popular_movies.two.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.davidjuanes.popular_movies.two.PopularMoviesApp;
import com.davidjuanes.popular_movies.two.R;
import com.davidjuanes.popular_movies.two.activities.main.MoviePosterAdapter;
import com.davidjuanes.popular_movies.two.domain.Movie;
import com.davidjuanes.popular_movies.two.domain.MovieList;
import com.davidjuanes.popular_movies.two.services.MovieService;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView moviesGrid;
    private RecyclerView.LayoutManager layoutManager;
    private MoviePosterAdapter adapter;
    private MovieService movieService;
    private Integer selectedPosition;
    private SharedPreferences sharedPref;
    private MovieList loadedMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        //Retrieve the movie service
        movieService = PopularMoviesApp.getMovieService();
        moviesGrid = (RecyclerView) findViewById(R.id.movies_grid);
        moviesGrid.setHasFixedSize(true);
        //moviesGrid.setHasFixedSize(true);
        //Create the layout manager
        layoutManager = new GridLayoutManager(this, 2);
        moviesGrid.setLayoutManager(layoutManager);

        if (savedInstanceState != null) {
            loadedMovies = (MovieList) savedInstanceState.getSerializable("movies");
        }
        //Create and attach the adapter
        //Load movies
        updateMoviesList(loadedMovies == null);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("movies", loadedMovies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            modalDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private ListType getListType() {
        return ListType.valueOf(sharedPref.getString("listType", "POPULAR_MOVIES"));
    }

    private void setListType(ListType listType) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("listType", listType.name());
        editor.commit();
    }

    private void updateMoviesList(Boolean reloadFromSource) {

        AsyncMoviesLoad moviesLoad = new AsyncMoviesLoad(getListType());
        if (reloadFromSource) {
            moviesLoad.execute();
        }
        else {
            moviesLoad.onPostExecute(loadedMovies);
        }
    }

    ///////////////////////////////////////////////////////////

    private enum ListType {
        TOP_RATED_MOVIES, POPULAR_MOVIES
    }

    private class AsyncMoviesLoad extends AsyncTask<Void, Void, List<Movie>>
    {
        private ListType listType;
        public AsyncMoviesLoad(ListType listType) {
            this.listType = listType;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<Movie> doInBackground(Void... params) {
            try {
                switch (listType) {
                    case TOP_RATED_MOVIES:
                        return movieService.retrieveTopRatedMovies();
                    case POPULAR_MOVIES:
                        return movieService.retrievePopularMovies();
                }
            }
            catch (Exception ex)
            {
                Log.e("Init", "Error loading movies list", ex);
            }
            return Collections.emptyList();
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            try {
                //adapter.addList(movies);
                //adapter.notifyDataSetChanged();
                //moviesGrid.invalidate();
                adapter = new MoviePosterAdapter(movies, MainActivity.this);
                moviesGrid.setAdapter(adapter);
                MovieList movieList = new MovieList();
                movieList.addAll(movies);
                loadedMovies = movieList;
            }
            catch (Exception ex) {
                Log.e("MainActivity", "Error adding movies to the Grid", ex);
            }
        }
    }


    private ListType modalDialog() {
        String[] options = {"Popular Movies", "Top Rated Movies"};
        final ListType selectedValue = null;
        final Integer activeType = getListType() == ListType.POPULAR_MOVIES ? 0 : 1;
        selectedPosition = activeType;
        Log.i("FilterDialog", "Initial Position: " + activeType);
        AlertDialog.Builder builder = new AlertDialog.Builder(this).setCancelable(true).setSingleChoiceItems(
                options, activeType, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("FilterDialog", "New selected position: " + which);
                selectedPosition = which;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Log.i("FilterDialog", "Selected Position: " + selectedPosition);
                ListType previous = getListType();
                setListType(selectedPosition == 0 ? ListType.POPULAR_MOVIES : ListType.TOP_RATED_MOVIES);
                updateMoviesList(!previous.equals(getListType()));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
                dialog.dismiss();
            }
        });
        builder.create().show();
        return selectedValue;
    }
}
