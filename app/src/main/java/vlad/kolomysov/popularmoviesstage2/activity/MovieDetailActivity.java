package vlad.kolomysov.popularmoviesstage2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import vlad.kolomysov.popularmoviesstage2.R;
import vlad.kolomysov.popularmoviesstage2.fragment.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE = "MOVIE";

    private String mMovieId;


    public static Intent createIntent(Context context,String movie){

        Intent intent= new Intent(context,MovieDetailActivity.class);
        intent.putExtra(MOVIE, movie);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getExtras();

        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.content, MovieDetailFragment.getNewInstance(mMovieId)).commit();
        }



    }

    private void getExtras() {

        mMovieId = getIntent().getStringExtra(MOVIE);

    }


}
