package vlad.kolomysov.popularmoviesstage2;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Copyright (C) Created by Vlad Kolomysov on 05.05.16.
 * All rights reserved. United Financial Capital Bank, Moscow
 */
public class DetailsFragment extends Fragment {




    @Bind(R.id.title) TextView mTitle;
    @Bind(R.id.movie_poster) ImageView mMoviePoster;
    @Bind(R.id.vote_average) TextView mVoteAverage;
    @Bind(R.id.plot_synopsis) TextView mPlotSynopsis;
    @Bind(R.id.release_date) TextView mReleaseDate;
    @Bind(R.id.button_play_trailer) Button mButtonPlayTrailer;
    @Bind(R.id.heart_button) Button mHeartButton;

    Realm mFavouriteRealm;

    private String mId;
    private String mPosterPath;
    private String mOriginalTitle;
    private String mOverview;
    private String mReleaseDate2;
    private String mVoteAverage2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details, container, false);

    ButterKnife.bind(this,view);


        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/fontawesome-webfont.ttf");

       /* DatabaseHelper dbHelper = new DatabaseHelper(this);
        final SQLiteDatabase sqdb = dbHelper.getWritableDatabase();*/

    mFavouriteRealm =
            Realm.getInstance(new RealmConfiguration
            .Builder(getActivity())
            .deleteRealmIfMigrationNeeded()
    .build());

      /*  mFavouriteRealm =
                Realm.getInstance(new RealmConfiguration.Builder(getApplicationContext())
                        .name("favourite.realm")
                        .build());*/


    mButtonPlayTrailer = (Button) view.findViewById(R.id.button_play_trailer);
    //   mCheckBox = (Button) findViewById(R.id.heart_button);

  //  mHeartButton.setTypeface(typeface);
 //   mButtonPlayTrailer.setTypeface(typeface);
    // mRatingBar.setRating(1);

    Intent intent = getActivity().getIntent();

    // Step 1 - get data that were passed throuh intent in GridViewActivity
    // Step 2 - set learned data to UI elements detail activity

    String title = intent.getStringExtra("original_title");
        mTitle.setText(title);

    String image = intent.getStringExtra("poster_path");
    Picasso.with(getActivity()).load("http://image.tmdb.org/t/p/w342/" +image).into(mMoviePoster);

    String overview = intent.getStringExtra("overview");
    mPlotSynopsis.setText("Overview: " + overview);

    String voteAverage = intent.getStringExtra("vote_average");
    mVoteAverage.setText("Average vote:   " + voteAverage);

    String releaseDate = intent.getStringExtra("release_date");
    mReleaseDate.setText("Release date:   " + releaseDate);

        mId = intent.getStringExtra("id");
        mPosterPath = intent.getStringExtra("poster_path");
        mOriginalTitle = intent.getStringExtra("original_title");
        mOverview = intent.getStringExtra("overview");
        mReleaseDate2 = intent.getStringExtra("release_date");
        mVoteAverage2 = intent.getStringExtra("vote_average");


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

        return view;
}

    // click on Heart Button to add favourite film
    @OnClick(R.id.heart_button)
    public void heartButton(){
        Toast.makeText(getActivity(),"HEART",Toast.LENGTH_SHORT);

        mFavouriteRealm.beginTransaction();

        FavouriteFilm  favouriteFilm = mFavouriteRealm.createObject(FavouriteFilm.class);

        // Set its fields
        favouriteFilm.setmId(mId);
        favouriteFilm.setmPosterPath(mPosterPath);
        favouriteFilm.setmOriginalTitle(mOriginalTitle);
        favouriteFilm.setmOverview(mOverview);
        favouriteFilm.setmReleaseDate(mReleaseDate2);
        favouriteFilm.setmVoteAverage(mVoteAverage2);

        Log.i("click", mId);
        Log.i("click", mPosterPath);
        Log.i("click", mOriginalTitle);
        Log.i("click", mOverview);
        Log.i("click", mReleaseDate2);
        Log.i("click", mVoteAverage2);

        mFavouriteRealm.commitTransaction();

        Log.i("click", "i clickHearButton....");

        Log.d("click","d clickHearButton");
    }

    // click play button
    @OnClick(R.id.button_play_trailer)
    public void buttonPlayButton(){
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=Hxy8BZGQ5Jo")));
        Log.i("click", "i Video Playing....");
        Log.d("click","d clickPlayTrailer");
    }

}
