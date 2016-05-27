package vlad.kolomysov.popularmoviesstage2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Copyright (C) Created by Vlad Kolomysov on 28.09.15.
 */

// Main activity
public class GridViewActivity extends AppCompatActivity
{


    //condition that there must be equal to or more than 50 votes (to eliminate movies with only 1 vote)
    public static final String VOTE_COUNT_GTE = "500";

    private GridView mGridView;
    private ProgressBar mProgressBar;
    private GridViewAdapter mGridAdapter;

    ArrayList<Film> mListFilm = new ArrayList<Film>();

    TheMovieDBService mTheMovieDBService;

    Intent mIntent;

    Realm mFavouriteRealm;

    RealmResults<FavouriteFilm> results;

    public final String TAG = "10May";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        //mRealm = Realm.getDefaultInstance();
        mFavouriteRealm =
                Realm.getInstance(new RealmConfiguration
                        .Builder(this)
                        .deleteRealmIfMigrationNeeded()
                        .build());

        mGridView = (GridView) findViewById(R.id.gridView);
        mIntent = new Intent(this,DetailsActivity.class);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.VISIBLE);

        // costomize adapter, set end points
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.SERVICE_ENDPOINT) // API base URL
                .addConverterFactory(GsonConverterFactory.create()) // A converter which uses Gson for JSON.
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // for RxJava
                .build(); // Create the Retrofit instances.

        //Create an implementation of the API defined by the service interface.
        mTheMovieDBService = retrofit.create(TheMovieDBService.class);

        // listener for clicking in item from grid
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // customize intent for passing data to details activity
                mIntent.putExtra("id",mListFilm.get(position).id);
                mIntent.putExtra( "poster_path",mListFilm.get(position).poster_path);
                mIntent.putExtra( "original_title",mListFilm.get(position).original_title);
                mIntent.putExtra( "overview",mListFilm.get(position).overview);
                mIntent.putExtra( "release_date",mListFilm.get(position).release_date);
                mIntent.putExtra( "vote_average",mListFilm.get(position).vote_average);

//                original title
//                movie poster image thumbnail
//                A plot synopsis (called overview in the api)
//                user rating (called vote_average in the api)
//                release date

                // open new activity through intent
                startActivity(mIntent);


            }
        });

        // load page == 2
        loadData("2");

    }


    // load data defined by number of pages
    // I've used modern library Retrofit and RxJAVA for ASYNChronous
    // safethreading, handling error, fast and reliable
    public void loadData(String page)
    {
        mTheMovieDBService.getListFilm(Constants.API_KEY_TMDB, page)
                .subscribeOn(Schedulers.newThread()) // downloading in background thread
                .observeOn(AndroidSchedulers.mainThread()) // results was passed to main thred for updating gridview
                .subscribe(new Subscriber<ListMoviesModel>() {
                    @Override
                    public void onCompleted() {
                        // after complete loading data GONE progress bar
                        mProgressBar.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError(Throwable e) {
                        // if error occur - handle it
                        // if error GONE progress bar
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(GridViewActivity.this, "Error occur, please load again!", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNext(ListMoviesModel listMoviesModel) {
                        // listMoviesModel contain result from IMDB server
                        mListFilm = listMoviesModel.results; // save list of movies
                        // initilaze adapter
                        mGridAdapter = new GridViewAdapter(GridViewActivity.this, R.layout.row_grid, mListFilm);
                        // set adapter
                        mGridView.setAdapter(mGridAdapter);
                    }
                });
    }

    // load data defined by sort criterion - Most Popular
    // I've used modern library Retrofit and RxJAVA for ASYNChronous
    // safe threading, handling error, fast and reliable
    public  void loadDataSortByMostPopular(String sorting){

        mTheMovieDBService.getListFilmSortedByMostPopular(sorting, Constants.API_KEY_TMDB)
                .subscribeOn(Schedulers.newThread()) // downloading in background thread
                .observeOn(AndroidSchedulers.mainThread()) // results was passed to main thred for updating gridview
                .subscribe(new Subscriber<ListMoviesModel>() {
                    @Override
                    public void onCompleted() {
                        // after complete loading data GONE progress bar
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // if error occur - handle it
                        // if error GONE progress bar
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(GridViewActivity.this, "Error occur, please load again!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ListMoviesModel listMoviesModel) {

                        // clear adapter filled in previous step
                        mGridAdapter.clear();
                        // listMoviesModel contain result from IMDB server
                        mListFilm = listMoviesModel.results; // save list of movies
                        // initilaze adapter
                        mGridAdapter = new GridViewAdapter(GridViewActivity.this, R.layout.row_grid, mListFilm);
                        // set adapter
                        mGridView.setAdapter(mGridAdapter);

                    }
                });
    }


    // load data defined by sort criterion - Highest Rated
    // I've used modern library Retrofit and RxJAVA for ASYNChronous
    // safe threading, handling error, fast and reliable
    public  void loadDataSortByHighestRated(String sorting){

        mTheMovieDBService.getListFilmSortedByHighestRated(sorting, VOTE_COUNT_GTE, Constants.API_KEY_TMDB)
                .subscribeOn(Schedulers.newThread()) // downloading in background thread
                .observeOn(AndroidSchedulers.mainThread()) // results was passed to main thred for updating gridview
                .subscribe(new Subscriber<ListMoviesModel>() {
                    @Override
                    public void onCompleted() {
                        // after complete loading data GONE progress bar
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // if error occur - handle it
                        // if error GONE progress bar
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(GridViewActivity.this, "Error occur, please load again!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(ListMoviesModel listMoviesModel) {

                        // clear adapter filled in previous step
                        mGridAdapter.clear();
                        // listMoviesModel contain result from IMDB server
                        mListFilm = listMoviesModel.results; // save list of movies
                        // initilaze adapter
                        mGridAdapter = new GridViewAdapter(GridViewActivity.this, R.layout.row_grid, mListFilm);
                        // set adapter
                        mGridView.setAdapter(mGridAdapter);

                    }
                });
    }

    // create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // for selected item menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the most popular and highest rated button
        int id = item.getItemId();
        // perform operation when click on most popular or hoghest rated item
        switch (id)
        {

            case R.id.action_most_popular:

                // Visible progress bar
                mProgressBar.setVisibility(View.VISIBLE);
                loadDataSortByMostPopular(Constants.MOST_POPULAR);
                return true;

            case R.id.action_highest_rated:

                // Visible progress bar
                mProgressBar.setVisibility(View.VISIBLE);
                mGridAdapter.clear();
                loadDataSortByHighestRated(Constants.HIGHEST_RATED);
                return true;

            case R.id.action_favorite:

                // Visible progress bar
            /*    mProgressBar.setVisibility(View.VISIBLE);
                mGridAdapter.clear();
                loadDataSortByHighestRated(Constants.highest_rated);*/
                // TODO: 25.11.15 - load favorite data
                mProgressBar.setVisibility(View.VISIBLE);
                mGridAdapter.clear();
                results = mFavouriteRealm.where(FavouriteFilm.class).findAll();
                Log.d("click",results.toString());
                Log.d("click","size = "+results.size());

                //mListFilm.clear();

                Log.i("click","1 - mListFilm size = "+mListFilm.size());

                if (results.size() !=0){
                    for (int i = 0; i < results.size(); i++)
                    {

                        FavouriteFilm u = results.get(i);
                        Film u2 = new Film(u.getmId(),u.getmOriginalTitle(),u.getmOverview(),u.getmPosterPath(),u.getmReleaseDate(),u.getmVoteAverage());

                       /* u2.setId(u.getmId());
                        u2.setOriginal_title(u.getmOriginalTitle());
                        u2.setOverview(u.getmOverview());
                        u2.setPoster_path(u.getmPosterPath());
                        u2.setRelease_date(u.getmReleaseDate());
                        u2.setVote_average(u.getmVoteAverage());*/

                   /*     Log.i("click", u.getmId());
                        Log.i("click", u.getmPosterPath());
                        Log.i("click", u.getmOriginalTitle());
                        Log.i("click", u.getmOverview());
                        Log.i("click", u.getmReleaseDate());
                        Log.i("click", u.getmVoteAverage());*/
                        // ... do something with the object ...

                      /*  Log.d("click","i = "+i);
                        Log.d("click","u2.getOriginal_title() = "+u2.getOriginal_title());
                        Log.d("click","u2.getPoster_path() = "+u2.getPoster_path());
                        Log.d("click","u2.getId() = "+u2.getId());
                        Log.d("click","u2.getOverview() = "+u2.getOverview());
                        Log.d("click","u2.getRelease_date() = "+u2.getRelease_date());
                        Log.d("click","u2.getVote_average() = "+u2.getVote_average());*/

                        //mListFilm.set(i,u2);

                        mListFilm.add(u2);
                        Log.d("click","mListFilm.size() = "+mListFilm.size());

                    }

                    Log.d("click","mListFilm.size() 1 = "+mListFilm.size());
                    // clear adapter filled in previous step
                    //mGridAdapter.clear();
                    Log.d("click","mListFilm.size() 2 = "+mListFilm.size());
                    //Log.i("click","mListFilm size = "+mListFilm.toString());
                    // listMoviesModel contain result from IMDB server
                    //mListFilm = listMoviesModel.results; // save list of movies
                    // initilaze adapter

                    mGridAdapter = new GridViewAdapter(GridViewActivity.this, R.layout.row_grid, mListFilm);
                    // set adapter
                    mGridView.setAdapter(mGridAdapter);

                }
                else {
                    Toast.makeText(getApplicationContext(),"В базе данных ничего нет!",Toast.LENGTH_SHORT);
                }

                return true;

            case R.id.clear_db:
               /* Realm realm = Realm.getInstance(getApplicationContext());
                realm.where(FavouriteFilm.class).findAll().clear();*/
                mFavouriteRealm.beginTransaction();
                //trip.removeFromRealm();
                mFavouriteRealm.where(FavouriteFilm.class).findAll().clear();
                mFavouriteRealm.commitTransaction();

                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
