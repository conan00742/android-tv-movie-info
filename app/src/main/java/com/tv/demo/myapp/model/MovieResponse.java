package com.tv.demo.myapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Krot on 5/28/18.
 */

public class MovieResponse implements Parcelable {

    @SerializedName("results")
    @Nullable
    private List<Movie> movies;

    @SerializedName("page")
    private int page;

    @SerializedName("total_pages")
    private int totalPage;

    @SerializedName("total_results")
    private int totalResult;

    public MovieResponse(List<Movie> movies, int page, int totalPage, int totalResult) {
        this.movies = movies;
        this.page = page;
        this.totalPage = totalPage;
        this.totalResult = totalResult;
    }

    @Nullable
    public List<Movie> getMovies() {
        return movies;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    protected MovieResponse(Parcel in) {
        movies = in.createTypedArrayList(Movie.CREATOR);
        page = in.readInt();
        totalPage = in.readInt();
        totalResult = in.readInt();
    }

    public static final Creator<MovieResponse> CREATOR = new Creator<MovieResponse>() {
        @Override
        public MovieResponse createFromParcel(Parcel in) {
            return new MovieResponse(in);
        }

        @Override
        public MovieResponse[] newArray(int size) {
            return new MovieResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(movies);
        dest.writeInt(page);
        dest.writeInt(totalPage);
        dest.writeInt(totalResult);
    }
}
