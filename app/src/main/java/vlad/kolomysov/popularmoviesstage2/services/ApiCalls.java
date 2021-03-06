package vlad.kolomysov.popularmoviesstage2.services;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import vlad.kolomysov.popularmoviesstage2.datatypes.ReviewsListResult;
import vlad.kolomysov.popularmoviesstage2.datatypes.VideosResult;


/**
 * Created by ashish-novelroots on 6/3/16.
 */
public interface ApiCalls {

    @GET("popular")
    Call<ResponseBody> getMoviesList(@Query("api_key") String apikey, @Query("page") int page
            , @Query("sortby") String sortby);

    @GET("{id}/reviews")
    Call<ReviewsListResult> getReviewsList(@Path("id") String movieId, @Query("api_key") String apikey, @Query("page") int page
    );

    @GET("{id}/videos")
    Call<VideosResult> getVideosList(@Path("id") String movieId, @Query("api_key") String apikey);

}
