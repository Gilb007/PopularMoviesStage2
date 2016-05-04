package vlad.kolomysov.popularmoviesstage2;

import io.realm.RealmObject;

/**
 * Copyright (C) Created by Vlad Kolomysov on 04.05.16.
 * All rights reserved. United Financial Capital Bank, Moscow
 */
public class FavouriteFilm extends RealmObject {

    private Boolean mFavourite;
    private String mId;

    public FavouriteFilm() {

    }

    public Boolean getmFavourite() {
        return mFavourite;
    }

    public void setmFavourite(Boolean mFavourite) {
        this.mFavourite = mFavourite;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }
}
