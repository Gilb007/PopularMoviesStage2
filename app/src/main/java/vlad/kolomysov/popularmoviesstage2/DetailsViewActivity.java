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

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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

    @BindView(R.id.title) TextView mTitle;
    @BindView(R.id.movie_poster) ImageView mMoviePoster;
    @BindView(R.id.vote_average) TextView mVoteAverage;
    @BindView(R.id.plot_synopsis) TextView mPlotSynopsis;
    @BindView(R.id.release_date) TextView mReleaseDate;
    @BindView(R.id.button_play_trailer) Button mButtonPlayTrailer;
    @BindView(R.id.heart_button) Button mHeartButton;

    Realm mFavouriteRealm;

    private String mId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        ButterKnife.bind(this);

       /* DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase sqdb = dbHelper.getWritableDatabase();*/

         mFavouriteRealm =
                Realm.getInstance(
                        new RealmConfiguration.Builder(getApplicationContext())
                                .name("favourite.realm")
                                .build()
                );


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

        mId = intent.getStringExtra("id");


       /* mButtonPlayTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
                Log.i("Video", "Video Playing....");
            }
        });*/

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

    // click on Heart Button to add favourite film
    @OnClick(R.id.heart_button)
    public void heartButton(){
        Toast.makeText(getApplicationContext(),"HEART",Toast.LENGTH_SHORT);

        mFavouriteRealm.beginTransaction();

        FavouriteFilm  favouriteFilm = mFavouriteRealm.createObject(FavouriteFilm.class);

        // Set its fields
        favouriteFilm.setmId(mId);

        mFavouriteRealm.commitTransaction();

        Log.i("click", "i clickHearButton....");

        Log.d("click","d clickHearButton");
    }

    @OnClick(R.id.button_play_trailer)
    public void buttonPlayButton(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
        Log.i("click", "i Video Playing....");
        Log.d("click","d clickPlayTrailer");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
