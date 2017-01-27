package zup.com.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;

import zup.com.models.Movie;
import zup.com.zupfilmes.R;

import static zup.com.zupfilmes.R.id.language;

public class AboutMovieFragment extends Fragment {

    String jsonMovie;
    Movie movie;

    public static AboutMovieFragment newInstance(String jsonMovie) {
        AboutMovieFragment fragmentFirst = new AboutMovieFragment();
        Bundle args = new Bundle();
        args.putString("jsonMovie", jsonMovie);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        jsonMovie = getArguments().getString("jsonMovie");

        movie = new Gson().fromJson(jsonMovie, Movie.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.content_about_movie, container, false);

        TextView tvTitle = (TextView) rootView.findViewById(R.id.movie_title);
        TextView tvDetails = (TextView) rootView.findViewById(R.id.movie_details);
        TextView tvactors = (TextView) rootView.findViewById(R.id.actors);
        TextView tvGenre = (TextView) rootView.findViewById(R.id.genre);
        TextView tvLanguage = (TextView) rootView.findViewById(language);
        RatingBar tvRatingBar = (RatingBar) rootView.findViewById(R.id.ratingBar);


        float rate = Float.parseFloat(movie.getImdbRating()) * 5 / 10;
        tvRatingBar.setClickable(false);
        tvRatingBar.setIsIndicator(true);
        tvRatingBar.setRating(rate);

        tvDetails.setText(movie.getPlot());
        tvactors.setText(movie.getActors());
        tvGenre.setText(movie.getGenre());
        tvLanguage.setText(movie.getLanguage());
        tvTitle.setText(movie.getTitle());

        return rootView;
    }
}
