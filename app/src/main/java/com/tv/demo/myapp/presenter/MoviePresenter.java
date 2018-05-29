package com.tv.demo.myapp.presenter;

import android.support.v17.leanback.widget.Presenter;
import android.util.Log;
import android.view.ViewGroup;

import com.tv.demo.myapp.model.Movie;
import com.tv.demo.myapp.widget.MovieView;

/**
 * Created by Krot on 5/28/18.
 */

public class MoviePresenter extends Presenter {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        MovieView movieView = new MovieView(parent.getContext());
        movieView.setFocusable(true);
        movieView.setFocusableInTouchMode(true);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        if (item instanceof Movie) {
            Movie movie = (Movie) item;
            ((MovieView) viewHolder.view).bindData(movie);
        }
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
