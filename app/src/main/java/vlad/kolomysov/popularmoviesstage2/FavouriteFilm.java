package vlad.kolomysov.popularmoviesstage2;

import io.realm.RealmObject;

/**
 * Copyright (C) Created by Vlad Kolomysov on 04.05.16.
 * All rights reserved. United Financial Capital Bank, Moscow
 */
public class FavouriteFilm extends RealmObject {

    private String mId;
    private String mPosterPath;
    private String mOriginalTitle;
    private String mOverview;
    private String mReleaseDate;
    private String mVoteAverage;

    public FavouriteFilm() {

    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(String mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }
}
