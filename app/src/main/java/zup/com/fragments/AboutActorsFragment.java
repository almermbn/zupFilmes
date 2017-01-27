package zup.com.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import zup.com.models.Movie;
import zup.com.zupfilmes.R;

public class AboutActorsFragment extends Fragment {

    String jsonMovie;
    Movie movie;

    public static AboutActorsFragment newInstance(String jsonMovie) {
        AboutActorsFragment fragmentFirst = new AboutActorsFragment();
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
        View rootView = inflater.inflate(R.layout.content_about_directors, container, false);

        TextView tvDetalhes = (TextView) rootView.findViewById(R.id.writer);
        TextView tvDirector = (TextView) rootView.findViewById(R.id.director);
        TextView tvAwards = (TextView) rootView.findViewById(R.id.awards);

        tvDetalhes.setText(movie.getWriter());
        tvDirector.setText(movie.getDirector());
        tvAwards.setText(movie.getAwards());

        return rootView;
    }
}
