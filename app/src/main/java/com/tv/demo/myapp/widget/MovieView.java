package com.tv.demo.myapp.widget;

import android.content.Context;
import android.support.v17.leanback.widget.BaseCardView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.tv.demo.myapp.R;
import com.tv.demo.myapp.Utils;
import com.tv.demo.myapp.api.client.TheMovieDBApiClient;
import com.tv.demo.myapp.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krot on 5/29/18.
 */

public class MovieView extends BaseCardView {

//    @BindView(R.id.iv_poster)
//    MoviePosterView mIvPoster;
//
//    @BindView(R.id.tv_title)
//    TextView mTvTitle;


    private View mRoot;
    private MoviePosterView mIvPoster;
    private TextView mTvTitle;


    public MovieView(Context context) {
        super(context);
        initLayout();
    }

    public MovieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public MovieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    private void initLayout() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.child_item, null);
        mRoot = view.findViewById(R.id.child_root);
        mIvPoster = view.findViewById(R.id.iv_poster);
        mTvTitle = view.findViewById(R.id.tv_title);
        this.addView(view);
    }

    public void bindData(Movie movie) {
        Log.i("WTF", "url = " + (TheMovieDBApiClient.POSTER_BASE_URL + movie.getPosterPath()));
        if (movie.getPosterPath() != null) {
            Glide.with(getContext()).load(TheMovieDBApiClient.POSTER_BASE_URL + movie.getPosterPath()).apply(new RequestOptions().centerCrop()).into(mIvPoster);
        }

        mTvTitle.setText(movie.getMovieTitle());
    }

}
