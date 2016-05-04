package vlad.kolomysov.popularmoviesstage2;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;

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

    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.movie_poster) ImageView mMoviePoster;
    @Bind(R.id.vote_average) TextView mVoteAverage;
    @Bind(R.id.plot_synopsis) TextView mPlotSynopsis;
    @Bind(R.id.release_date) TextView mReleaseDate;
    @Bind(R.id.button_play_trailer) Button mButtonPlayTrailer;
    @Bind(R.id.heart_button) Button mHeartButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase sqdb = dbHelper.getWritableDatabase();


        mButtonPlayTrailer = (Button) findViewById(R.id.button_play_trailer);
     //   mCheckBox = (Button) findViewById(R.id.heart_button);

        mHeartButton.setTypeface(typeface);
        mButtonPlayTrailer.setTypeface(typeface);
       // mRatingBar.setRating(1);

        Intent intent = getIntent();

        // Step 1 - get data that were passed throuh intent in GridViewActivity
        // Step 2 - set learned data to UI elements detail activity

        String title = intent.getStringExtra("original_title");
        mTitle.setText(title);

        String image = intent.getStringExtra("poster_path");
        Picasso.with(this).load("http://image.tmdb.org/t/p/w342/" + image).into(mMoviePoster);

        String overview = intent.getStringExtra("overview");
        mPlotSynopsis.setText("Overview: " + overview);

        String voteAverage = intent.getStringExtra("vote_average");
        mVoteAverage.setText("Average vote:   " + voteAverage);

        String releaseDate = intent.getStringExtra("release_date");
        mReleaseDate.setText("Release date:   " + releaseDate);


        mButtonPlayTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
                Log.i("Video", "Video Playing....");
            }
        });

   /*     mCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("checkbox","checkbox = "+mCheckBox.isChecked());

                //Log.d("push", "count = " + getRequestsResponse.requests.size());
                Cursor cursorPush = sqdb.query(DatabaseHelper.TABLE_PUSH, new String[]
                        {
                        DatabaseHelper.BRANCH_ID},
                        null, // The columns for the WHERE clause
                        null, // The values for the WHERE clause
                        null, // don't group the rows
                        null, // don't filter by row groups
                        null // The sort order
                );
            }
        });*/
    }

    @OnClick(R.id.heart_button) void heartButton(){
        Toast.makeText(getApplicationContext(),"HEART",Toast.LENGTH_SHORT);
    }

    @OnClick(R.id.button_play_trailer) void buttonPlayButton(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
        Log.i("Video", "Video Playing....");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
