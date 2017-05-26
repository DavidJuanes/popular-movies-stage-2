package com.davidjuanes.popular_movies.two.services;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.davidjuanes.popular_movies.two.domain.YoutubeVideo;
import com.squareup.picasso.Picasso;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Simple class that create CardViews for a Youtube Trailer
 */

public class TrailerCardBuilder {

    /**
     * Creates a cardView to display the trailer
     * @param ctx activity context
     * @param trailer contains the video info
     * @return card ready to be inserted in the layout
     */
    public static CardView buildCard(Context ctx, YoutubeVideo  trailer) {
        CardView cardView = new CardView(ctx);
        // Set the CardView layoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 30, 15, 0);
        cardView.setLayoutParams(params);


        // Set CardView corner radius
        cardView.setRadius(9);

        // Initialize a new ImageView to put in CardView
        ViewGroup.LayoutParams paramsImage = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        ImageView trailerImage = new ImageView(ctx);
        trailerImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE );
        trailerImage.setAdjustViewBounds(true);
        trailerImage.setLayoutParams(paramsImage);
        Picasso.with(ctx).load(trailer.getVideoImageUrl()).into(trailerImage);
        addClickListener(trailer.getVideoUrl(), trailerImage, ctx);
        cardView.addView(trailerImage);

        // Set cardView content padding
        cardView.setContentPadding(15, 15, 15, 15);
        return cardView;
    }

    /**
     * Adds a click listener so the trailer starts playing in youtube when clicked.
     * @param youtubeLink link to video
     * @param imageView image of the trailer
     * @param ctx activity context
     */
    private static void addClickListener(final String youtubeLink, ImageView imageView, final Context ctx) {
        imageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                startActivity(ctx, new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink)), null);
                Log.i("Video", "Video Playing (" + youtubeLink + ")...");
            }
        });
    }
}
