package zup.com.zupfilmes;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public MovieListAdapter(Context context, int textViewResourceId, ArrayList<Movie> items) {
        super(context, textViewResourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.movie_row, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.movieTitle);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        ShortcutBadger.removeCount(getContext());

        Movie movie = getItem(position);

        textView.setText(movie.getTitle());

        Bitmap image = retrieveImage(movie);

        imageView.setImageBitmap(image);

        return rowView;
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