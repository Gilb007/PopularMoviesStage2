package vlad.kolomysov.popularmoviesstage2.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import vlad.kolomysov.popularmoviesstage2.R;
import vlad.kolomysov.popularmoviesstage2.fragment.MovieDetailFragment;
import vlad.kolomysov.popularmoviesstage2.fragment.PopularMoviesFragment;
import vlad.kolomysov.popularmoviesstage2.utils.AppUtils;


public class MainActivity extends AppCompatActivity implements PopularMoviesFragment.OnFragmentInteractionListener{



    public static String TAG= MainActivity.class.getSimpleName();

    String mSortBy = null;
    private PopupMenu mMenu;

    boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();


    }

    protected void init() {


        setContentView(R.layout.activity_main);

        PopularMoviesFragment fragment= (PopularMoviesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_popularmovies);

        if(findViewById(R.id.movie_detail_container)!=null){
            fragment.setTwoPane(true);
            mTwoPane=true;
        }
        else{
            fragment.setTwoPane(false);
            mTwoPane=false;
        }

        ImageView moreoption= (ImageView) findViewById(R.id.iv_more_option);

        mMenu= new PopupMenu(this,moreoption);
        mMenu.getMenuInflater().inflate(R.menu.menu_main, mMenu.getMenu());
        moreoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handleMoreOption();
            }
        });

        mMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {

                    case R.id.action_settings:
                        handleSettingsAction();
                        break;

                    case R.id.action_fav:
                        handleFavoriteMoviesAction();
                        break;


                }


                return true;
            }
        });
        Log.d(TAG, "onCreate");
    }

    private void handleFavoriteMoviesAction() {

        Intent intent= new Intent(this, FavoriteMoviesActivity.class);
        startActivity(intent);

    }

    private void handleMoreOption() {

        mMenu.show();
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPosterClick(String movie) {

//        Toast.makeText(this,"Movie: "+movie.getTitle(),Toast.LENGTH_SHORT).show();
        if(!mTwoPane) {
            Intent intent = MovieDetailActivity.createIntent(this, movie);
            startActivity(intent);
        }
        else{
            MovieDetailFragment fragment= MovieDetailFragment.getNewInstance(movie);
            fragment.setTwoSpan(true);
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.movie_detail_container, fragment).commit();
        }
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
        if (id == R.id.action_settings) {
            handleSettingsAction();
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleSettingsAction() {


        Intent intent= new Intent(this, SettingsActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onResume() {
        super.onResume();


        if(mSortBy==null){
            mSortBy= AppUtils.getSortByOption();
        }
        else if(!mSortBy.equals(AppUtils.getSortByOption()))
        {
            mSortBy= AppUtils.getSortByOption();
            PopularMoviesFragment fragment= (PopularMoviesFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_popularmovies);
            fragment.refreshData();


        }
    }
}
