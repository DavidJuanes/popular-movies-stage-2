package com.davidjuanes.popular_movies.one.activities.main;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.davidjuanes.popular_movies.one.R;
import com.davidjuanes.popular_movies.one.activities.MovieDetailsActivity;
import com.davidjuanes.popular_movies.one.domain.Movie;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter for the grid showed in the main activity of the App. It displays the poster of each movie.
 */
public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.ViewHolder> {
    private List<Movie> movies;
    private Context context;
    /**
     * Default Constructor to create this adapter by passing a list of movies to be displayed.
     * @param movies movies to be displayed.
     */
    public MoviePosterAdapter(List<Movie> movies, Context ctx)
    {
        this.movies = movies;
        this.context = ctx;
    }

    public void addList(List<Movie> movies) {
        for (Movie movie : movies) {
            this.movies.add(movie);
            Log.i("MoviePosterAdapter", "Movie '" + movie.getTitle() + "' added. Total: " + this.movies.size());
            notifyItemInserted(this.movies.indexOf(movie));
            notifyDataSetChanged();
        }
    }

    public void cleanDataSet() {
        this.movies.clear();
        notifyDataSetChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MoviePosterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_poster, parent, false);
        return new ViewHolder(v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(final MoviePosterAdapter.ViewHolder holder, int position) {
        //Get element from your dataset at this position
        final Movie movie = movies.get(position);
        //Replace the contents of the view with that element
        Drawable loading = context.getDrawable(R.drawable.ic_photo_size_select_actual_white_24dp);
        loading.setColorFilter(context.getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
        Picasso.with(context).load(movie.getPosterUrl()).placeholder(loading).into(holder.ivPoster);
        holder.ivPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra("movie", movie);
                ActivityOptions options = ActivityOptions
                        .makeSceneTransitionAnimation((Activity) context, holder.ivPoster, "poster");
                context.startActivity(intent, options.toBundle());
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemCount() {
        return movies.size();
    }

    /**
     * View Holder for the Recycle View.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView ivPoster;

        public ViewHolder(View v) {
            super(v);
            ivPoster = (ImageView) v.findViewById(R.id.poster);
        }
    }
}
