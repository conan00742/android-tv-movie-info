package com.tv.demo.myapp.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tv.demo.myapp.R;
import com.tv.demo.myapp.adapter.MoviesAdapter;
import com.tv.demo.myapp.model.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krot on 5/28/18.
 */

public class RowListCustomView extends FrameLayout {

    @BindView(R.id.tv_rowTitle)
    TextView mTvTitle;

    @BindView(R.id.rv_movies)
    RecyclerView mRvMovies;

    private MoviesAdapter adapter;

    public RowListCustomView(@NonNull Context context) {
        super(context);
        initLayout();
    }

    public RowListCustomView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    public RowListCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout();
    }

    public RowListCustomView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout();
    }

    private void initLayout() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_row_item, null);
        ButterKnife.bind(this, view);
        this.addView(view);
    }

    public void initMoviesAdapter() {
        adapter = new MoviesAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRvMovies.setLayoutManager(layoutManager);
    }

    public void setRowTitle(String title) {
        mTvTitle.setText(title);
    }

    public void setAdapterData(List<Movie> dataList) {
        adapter.setDataList(dataList);
    }
}
