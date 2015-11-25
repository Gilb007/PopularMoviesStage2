package vlad.kolomysov.popularmoviesstage2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Copyright (C) Created by Vlad Kolomysov on 29.09.15.
 */


/**
 *  Activity for details movie
 *
 *  title
 *  overview
 *  image poster
 *  average vote
 *  release date
 *
 */

public class DetailsViewActivity extends Activity {

    private TextView mTitle;
    private ImageView mImage;
    private TextView mOverview;
    private TextView mVoteAverage;
    private TextView mReleaseDate;
    private RatingBar mRatingBar;
    private Button mButtonPlayTrailer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);




        mTitle = (TextView) findViewById(R.id.original_title);
        mImage = (ImageView) findViewById(R.id.image_thumbnail);
        mOverview = (TextView) findViewById(R.id.overview);
        mVoteAverage = (TextView) findViewById(R.id.vote_average);
        mReleaseDate = (TextView) findViewById(R.id.release_date);
        mRatingBar = (RatingBar) findViewById(R.id.rating);
        mButtonPlayTrailer = (Button) findViewById(R.id.button_play_trailer);

       // mRatingBar.setRating(1);

        Intent intent = getIntent();

        // Step 1 - get data that were passed throuh intent in GridViewActivity
        // Step 2 - set learned data to UI elements detail activity

        String title = intent.getStringExtra("original_title");
        mTitle.setText(title);

        String image = intent.getStringExtra("poster_path");
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/" + image).into(mImage);

        String overview = intent.getStringExtra("overview");
        mOverview.setText("Overview: "+overview);

        String voteAverage = intent.getStringExtra("vote_average");
        mVoteAverage.setText("Average vote:   "+voteAverage);

        String releaseDate = intent.getStringExtra("release_date");
        mReleaseDate.setText("Release date:   "+releaseDate);


        mButtonPlayTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
                Log.i("Video", "Video Playing....");
            }
        });

    }


}
