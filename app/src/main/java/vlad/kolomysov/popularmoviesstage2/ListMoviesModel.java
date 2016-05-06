package vlad.kolomysov.popularmoviesstage2;


import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) Created by Vlad Kolomysov on 30.09.15.
 */
// 2
// Model for result that we will retrieve from IMDB server
    // ListMoviesModel contains results, that contait list on Movies
public class ListMoviesModel
{
    String page;
    ArrayList<Film> results;
    String total_pages;
    String total_results;
}
