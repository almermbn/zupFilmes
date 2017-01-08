package zup.com.zupfilmes;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MovieListFragment extends Fragment {

    ArrayAdapter<String> adapter;
    public ArrayList<Movie> moviesList = new ArrayList<Movie>();
    ArrayList<String> movies = new ArrayList<String>();
    List<Movie> movieListJson = new ArrayList<Movie>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_fragment, container, false);
        getActivity().setTitle("Meus Filmes");

        ListView listView = (ListView)rootView.findViewById(R.id.movie_list);
        TextView emptyList = (TextView)rootView.findViewById(R.id.emptyList);

        AHBottomNavigation bottomNavigation = (AHBottomNavigation) getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigation.setNotification("", 1);

        //get movies datas from storage
        retrieveMovies();
        if(movies.size() > 0){
            Type collectionType = new TypeToken<List<Movie>>(){}.getType();
            movieListJson = (List<Movie>) new Gson().fromJson( movies.toString() , collectionType);
            emptyList.setText("");
        }

        MovieListAdapter adapter = new MovieListAdapter(rootView.getContext(), 0, (ArrayList<Movie>) movieListJson);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getBaseContext(), MovieDetails.class);

                String movieData = new Gson().toJson(movieListJson.get(position));
                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                Movie movie = movieListJson.get(position);
                Bitmap image = retrieveImage(movie);
                image.compress(Bitmap.CompressFormat.PNG, 50, bs);
                byte[] bArray = bs.toByteArray();

                intent.putExtra("movie", movieData);
                intent.putExtra("movieImage", bArray);
                intent.putExtra("isEdit", true);
                getActivity().startActivity(intent);
            }
        });

        listView.setAdapter(adapter);

        return rootView;
    }

    private void retrieveMovies(){
        MoviesDatabase mDbHelper = new MoviesDatabase(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " +MoviesDatabase.TABLE_NAME, null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            String movieData = cursor.getString(cursor.getColumnIndexOrThrow(MoviesDatabase.COLUMN_MOVIE_DATA));
            movies.add(movieData);
            cursor.moveToNext();
        }
    }

    private Bitmap retrieveImage(Movie movie){
        MoviesDatabase mDbHelper = new MoviesDatabase(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();


        Cursor cursor = db.query(
                MoviesDatabase.TABLE_NAME,
                new String[] {MoviesDatabase.COLUMN_MOVIE_IMAGE},
                MoviesDatabase.COLUMN_MOVIE_TITLE + " = ?",
                new String[] {movie.getTitle()},
                null,
                null,
                null);
        cursor.moveToFirst();
        if(cursor != null && cursor.isAfterLast() == false) {
            byte[] image = cursor.getBlob(cursor.getColumnIndexOrThrow(MoviesDatabase.COLUMN_MOVIE_IMAGE));
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        }
        return null;
    }
}
