package com.tv.demo.myapp.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Krot on 5/28/18.
 */

public class Movie implements Parcelable {

    @SerializedName("id")
    @NonNull
    private String movieId;

    @SerializedName("title")
    @NonNull
    private String movieTitle;

    @SerializedName("poster_path")
    @Nullable
    private String posterPath;

    @SerializedName("vote_average")
    @NonNull
    private String voteAverage;

    @SerializedName("backdrop_path")
    @Nullable
    private String backdropPath;

    @SerializedName("overview")
    @NonNull
    private String overview;

    @SerializedName("release_date")
    @NonNull
    private String releaseDate;

    public Movie(@NonNull String movieId, @NonNull String movieTitle,@Nullable String posterPath, @NonNull String voteAverage,
                 @Nullable String backdropPath, @NonNull String overview, @NonNull String releaseDate) {
        this.movieId = movieId;
        this.movieTitle = movieTitle;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.backdropPath = backdropPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    @NonNull
    public String getMovieId() {
        return movieId;
    }

    @NonNull
    public String getMovieTitle() {
        return movieTitle;
    }

    @Nullable
    public String getPosterPath() {
        return posterPath;
    }

    @NonNull
    public String getVoteAverage() {
        return voteAverage;
    }

    @Nullable
    public String getBackdropPath() {
        return backdropPath;
    }

    @NonNull
    public String getOverview() {
        return overview;
    }

    @NonNull
    public String getReleaseDate() {
        return releaseDate;
    }

    protected Movie(Parcel in) {
        movieId = in.readString();
        movieTitle = in.readString();
        posterPath = in.readString();
        voteAverage = in.readString();
        backdropPath = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieId);
        dest.writeString(movieTitle);
        dest.writeString(posterPath);
        dest.writeString(voteAverage);
        dest.writeString(backdropPath);
        dest.writeString(overview);
        dest.writeString(releaseDate);
    }
}
