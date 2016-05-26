package vlad.kolomysov.popularmoviesstage2.fragment;

import io.realm.Realm;

import vlad.kolomysov.popularmoviesstage2.adapter.MoviesAdapter;
import vlad.kolomysov.popularmoviesstage2.datatypes.RealmMovie;
import vlad.kolomysov.popularmoviesstage2.fragment.PopularMoviesFragment;

/**
 * Created by ashish-novelroots on 22/4/16.
 */
public class FavoriteMoviesFragment extends PopularMoviesFragment {


    @Override
    protected void setAdapter() {

        Realm realm = Realm.getInstance(getActivity());
        mResults = realm.where(RealmMovie.class)
                .equalTo("favourite",true)
                .findAll();

        mAdapter= new MoviesAdapter(getActivity(),mResults,true,mTwoPan);

        if(mTwoPan){

            mGridViewPopularMovies.setNumColumns(1);
        }
        else{
            mGridViewPopularMovies.setNumColumns(2);
        }

        mGridViewPopularMovies.setAdapter(mAdapter);
    }

    @Override
    protected void loadMoreMovies() {

    }
}
