package vlad.kolomysov.popularmoviesstage2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import vlad.kolomysov.popularmoviesstage2.DetailsFragment;
import vlad.kolomysov.popularmoviesstage2.R;

/**
 * Created by admin on 09.10.15.
 */
public class DetailsActivity extends FragmentActivity {

    private Fragment mContent;
    DetailsFragment mFragment;
    public final String TAG = "save";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);


        if (savedInstanceState == null){
            mFragment = new DetailsFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, new DetailsFragment(),"DetailsFragment")
                    .commit();
        }
        else {
            mContent = getSupportFragmentManager().getFragment(savedInstanceState, "DetailsFragment");
            getSupportFragmentManager().findFragmentByTag("DetailsFragment");
        }

    }

}
