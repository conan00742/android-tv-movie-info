package com.tv.demo.myapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.tv.demo.myapp.R;
import com.tv.demo.myapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Krot on 5/28/18.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesVH> {

    private List<Movie> dataList;
    private Context context;
    private LayoutInflater inflater;
    private RequestManager manager;

    public MoviesAdapter(Context context) {
        dataList = new ArrayList<>();
        this.context = context;
        inflater = LayoutInflater.from(context);
        manager = Glide.with(context);
    }

    public void setDataList(List<Movie> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public MoviesVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.child_item, parent, false);
        return new MoviesVH(view);
    }

    @Override
    public void onBindViewHolder(MoviesVH holder, int position) {
        holder.bindData(getMovieAt(position));
    }

    private Movie getMovieAt(int position) {
        return dataList != null ? dataList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    class MoviesVH extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_poster)
        ImageView mIvPoster;

        @BindView(R.id.tv_title)
        TextView mTvTitle;

        public MoviesVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Movie currentMovie) {
            manager.load(currentMovie.getPosterPath()).into(mIvPoster);
            mTvTitle.setText(currentMovie.getMovieTitle());
        }
    }
}
