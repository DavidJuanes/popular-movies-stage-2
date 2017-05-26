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
import android.widget.TextView;

import com.davidjuanes.popular_movies.two.R;
import com.davidjuanes.popular_movies.two.domain.Review;
import com.davidjuanes.popular_movies.two.domain.YoutubeVideo;
import com.squareup.picasso.Picasso;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Simple class that create CardViews for a movie review
 */

public class ReviewCardBuilder {

    /**
     * Creates a cardView to display the review
     * @param ctx activity context
     * @param review contains the review info
     * @return card ready to be inserted in the layout
     */
    public static CardView buildCard(Context ctx, Review review) {
        CardView cardView = new CardView(ctx);
        // Set the CardView layoutParams
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(15, 30, 15, 5);
        cardView.setLayoutParams(params);


        // Set CardView corner radius
        cardView.setRadius(9);

        // Initialize a new ImageView to put in CardView
        ViewGroup.LayoutParams paramsLinearLayout = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        LinearLayout linearLayout = new LinearLayout(ctx);
        linearLayout.setLayoutParams(params);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        TextView authorTv = new TextView(ctx);
        authorTv.setTextAppearance(R.style.TextAppearance_AppCompat_Title);
        authorTv.setText(review.getAuthor());

        TextView contentTv = new TextView(ctx);
        contentTv.setText(review.getContent());
        addClickListener(review.getUrl(), contentTv, ctx);

        linearLayout.addView(authorTv);
        linearLayout.addView(contentTv);
        cardView.addView(linearLayout);

        // Set cardView content padding
        cardView.setContentPadding(15, 30, 15, 30);
        return cardView;
    }

    /**
     * Adds a click listener so the review can be seen in the web.
     * @param reviewLink link to review
     * @param textView text of the review
     * @param ctx activity context
     */
    private static void addClickListener(final String reviewLink, TextView textView, final Context ctx) {
        textView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                startActivity(ctx, new Intent(Intent.ACTION_VIEW, Uri.parse(reviewLink)), null);
                Log.i("Review", "Opening Review (" + reviewLink + ")...");
            }
        });
    }
}
