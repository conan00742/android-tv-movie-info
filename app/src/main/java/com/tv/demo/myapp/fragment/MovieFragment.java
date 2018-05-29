package com.tv.demo.myapp.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.app.RowsFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.BaseOnItemViewClickedListener;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.PageRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import com.tv.demo.myapp.AppConfig;
import com.tv.demo.myapp.R;
import com.tv.demo.myapp.api.TheMovieDBApi;
import com.tv.demo.myapp.api.client.TheMovieDBApiClient;
import com.tv.demo.myapp.model.Movie;
import com.tv.demo.myapp.model.MovieResponse;
import com.tv.demo.myapp.model.MovieRow;
import com.tv.demo.myapp.presenter.MoviePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MovieFragment extends BrowseFragment {

    private ArrayObjectAdapter rowsAdapter;
    private BackgroundManager mBackgroundManager;

    public static final int HEADER_1 = 8;
    public static final int HEADER_2 = 9;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpUI();
        loadData();
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        getMainFragmentRegistry().registerFragment(PageRow.class,
                new PageRowFragmentFactory(mBackgroundManager));
    }


    private void setUpUI() {
        setBadgeDrawable(ContextCompat.getDrawable(getContext(), R.drawable.powered_by));
        setHeadersState(HEADERS_HIDDEN);
        setHeadersTransitionOnBackEnabled(true);
        setBrandColor(ContextCompat.getColor(getContext(), R.color.fastlane_background));
        setSearchAffordanceColor(ContextCompat.getColor(getContext(), R.color.search_opaque));

        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(
                        getActivity(), getString(R.string.implement_search), Toast.LENGTH_SHORT)
                        .show();
            }
        });

        prepareEntranceTransition();
    }


    private void loadData() {
        rowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        HeaderItem headerItem1 = new HeaderItem(HEADER_1, "HEADER 1");
        PageRow pageRow1 = new PageRow(headerItem1);
        rowsAdapter.add(pageRow1);

        HeaderItem headerItem2 = new HeaderItem(HEADER_2, "HEADER 2");
        PageRow pageRow2 = new PageRow(headerItem2);
        rowsAdapter.add(pageRow2);

        setAdapter(rowsAdapter);
        startEntranceTransition();
    }

    private static class PageRowFragmentFactory extends BrowseFragment.FragmentFactory {

        private final BackgroundManager mBackgroundManager;

        PageRowFragmentFactory(BackgroundManager backgroundManager) {
            this.mBackgroundManager = backgroundManager;
        }

        @Override
        public Fragment createFragment(Object row) {
            Log.i("WTF", "createFragment");
            Row currentRow = (Row) row;
            mBackgroundManager.setDrawable(null);
            Log.i("WTF", "createFragment: type = " + currentRow.getHeaderItem().getId());
            if (currentRow.getHeaderItem().getId() == MovieFragment.HEADER_1) {
                return new ListFragment();
            } else if (currentRow.getHeaderItem().getId() == MovieFragment.HEADER_2) {
                return new ListFragment();
            }

            throw new IllegalArgumentException(String.format("Invalid row %s", row));
        }
    }

    public static class ListFragment extends RowsFragment {
        private final ArrayObjectAdapter mRowsAdapter;
        private SparseArray<MovieRow> movieRows;
        private TheMovieDBApi theMovieDBApi;
        private Disposable nowPlayingDisposable;
        private Disposable popularDisposable;
        private Disposable topRatedDisposable;
        private Disposable upComingDisposable;

        private final int NOW_PLAYING = 1;
        private final int POPULAR = 2;
        private final int TOP_RATED = 3;
        private final int UP_COMING = 4;

        public ListFragment() {
            Log.i("WTF", "ListFragment()");
            mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());
            setAdapter(mRowsAdapter);
            setOnItemViewClickedListener(new BaseOnItemViewClickedListener() {
                @Override
                public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Object row) {
                    Toast.makeText(getActivity(), "Implement click handler", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            theMovieDBApi = TheMovieDBApiClient.getRetrofit().create(TheMovieDBApi.class);
            getMainFragmentAdapter().getFragmentHost().notifyDataReady(getMainFragmentAdapter());
        }

        @Override
        public void onStart() {
            super.onStart();
            Log.i("WTF", "onStart");
            start();
        }


//        private void init() {
//            movieRows = new SparseArray<>();
//            MoviePresenter presenter = new MoviePresenter();
//
//            movieRows.put(NOW_PLAYING, new MovieRow()
//                    .setId(NOW_PLAYING)
//                    .setPage(1)
//                    .setTitle("Now Playing")
//                    .setAdapter(new ArrayObjectAdapter(presenter)));
//
//            //POPULAR
//            movieRows.put(POPULAR, new MovieRow()
//                    .setId(POPULAR)
//                    .setTitle("Popular")
//                    .setAdapter(new ArrayObjectAdapter(presenter))
//                    .setPage(1));
//
//            //TOP RATED
//            movieRows.put(TOP_RATED, new MovieRow()
//                    .setId(TOP_RATED)
//                    .setTitle("Top Rated")
//                    .setAdapter(new ArrayObjectAdapter(presenter))
//                    .setPage(1));
//
//            //UP COMING
//            movieRows.put(UP_COMING, new MovieRow()
//                    .setId(UP_COMING)
//                    .setTitle("Up Coming")
//                    .setAdapter(new ArrayObjectAdapter(presenter))
//                    .setPage(1));
//        }

        private void start() {
            //fetch API
            fetchNowPlayingMovies();
            fetchPopularMovies();
            fetchTopRatedMovies();
            fetchUpComingMovies();

        }

        private void stop() {
            if (nowPlayingDisposable != null && !nowPlayingDisposable.isDisposed()) {
                nowPlayingDisposable.dispose();
            }
            if (popularDisposable != null && !popularDisposable.isDisposed()) {
                popularDisposable.dispose();
            }
            if (topRatedDisposable != null && !topRatedDisposable.isDisposed()) {
                topRatedDisposable.dispose();
            }
            if (upComingDisposable != null && !upComingDisposable.isDisposed()) {
                upComingDisposable.dispose();
            }
        }

        private Row createCardRow(MovieResponse movieResponse, int type) {
            switch (type) {
                case NOW_PLAYING:
                    HeaderItem nowPlayingHeaderItem = new HeaderItem(NOW_PLAYING, "Now Playing");
                    ArrayObjectAdapter nowPlayingAdapter = new ArrayObjectAdapter(new MoviePresenter());
                    for (Movie movie : movieResponse.getMovies()) {
                        nowPlayingAdapter.add(movie);
                    }
                    return new ListRow(nowPlayingHeaderItem, nowPlayingAdapter);
                case POPULAR:
                    HeaderItem popularHeaderItem = new HeaderItem(POPULAR, "Popular");
                    ArrayObjectAdapter popularAdapter = new ArrayObjectAdapter(new MoviePresenter());
                    for (Movie movie : movieResponse.getMovies()) {
                        popularAdapter.add(movie);
                    }
                    return new ListRow(popularHeaderItem, popularAdapter);
                case TOP_RATED:
                    HeaderItem topRatedHeaderItem = new HeaderItem(TOP_RATED, "Top Rated");
                    ArrayObjectAdapter topRatedAdapter = new ArrayObjectAdapter(new MoviePresenter());
                    for (Movie movie : movieResponse.getMovies()) {
                        topRatedAdapter.add(movie);
                    }
                    return new ListRow(topRatedHeaderItem, topRatedAdapter);
                case UP_COMING:
                    HeaderItem upComingHeaderItem = new HeaderItem(UP_COMING, "Up Coming");
                    ArrayObjectAdapter upComingAdapter = new ArrayObjectAdapter(new MoviePresenter());
                    for (Movie movie : movieResponse.getMovies()) {
                        upComingAdapter.add(movie);
                    }
                    return new ListRow(upComingHeaderItem, upComingAdapter);
                default:
                    return null;
            }
        }


        /**
         * API call
         **/
        public void fetchNowPlayingMovies() {
            nowPlayingDisposable = theMovieDBApi.getNowPlayingMovies(AppConfig.API_KEY, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<MovieResponse>() {
                                @Override
                                public void accept(MovieResponse movieResponse) throws Exception {
                                    Log.i("WTF", "success");
                                    if (movieResponse != null) {
                                        mRowsAdapter.add(createCardRow(movieResponse, NOW_PLAYING));
                                    }
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Log.i("WTF", "fetchNowPlayingMovies: Error: " + throwable.getCause());
                                }
                            });
        }

        public void fetchPopularMovies() {
            popularDisposable = theMovieDBApi.getPopularMovies(AppConfig.API_KEY, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<MovieResponse>() {
                                @Override
                                public void accept(MovieResponse movieResponse) throws Exception {
                                    if (movieResponse != null) {
                                        mRowsAdapter.add(createCardRow(movieResponse, POPULAR));
                                    }
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });
        }

        public void fetchTopRatedMovies() {
            topRatedDisposable = theMovieDBApi.getTopRatedMovies(AppConfig.API_KEY, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<MovieResponse>() {
                                @Override
                                public void accept(MovieResponse movieResponse) throws Exception {
                                    if (movieResponse != null) {
                                        mRowsAdapter.add(createCardRow(movieResponse, TOP_RATED));
                                    }
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });
        }

        public void fetchUpComingMovies() {
            upComingDisposable = theMovieDBApi.getUpcomingMovies(AppConfig.API_KEY, 1)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            new Consumer<MovieResponse>() {
                                @Override
                                public void accept(MovieResponse movieResponse) throws Exception {
                                    if (movieResponse != null) {
                                        mRowsAdapter.add(createCardRow(movieResponse, UP_COMING));
                                    }
                                }
                            },
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });
        }


    }

}
