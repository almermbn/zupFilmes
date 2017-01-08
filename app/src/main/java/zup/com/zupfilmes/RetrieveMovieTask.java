package zup.com.zupfilmes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class RetrieveMovieTask extends AsyncTask<String, Void, Bitmap> {

    Movie movie;
    ImageView bmImage;
    Context context;

    public RetrieveMovieTask(ImageView bmImage, Movie movie, Context context) {
        this.bmImage = bmImage;
        this.movie = movie;
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
        saveLocalMovie(result);
        Toast.makeText(context, "Movie Added to your list" , Toast.LENGTH_SHORT).show();
    }

    private void saveLocalMovie(Bitmap image){

        MoviesDatabase mDbHelper = new MoviesDatabase(context);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String jsonMovie = new Gson().toJson(movie);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bArray = bos.toByteArray();

        ContentValues values = new ContentValues();
        values.put(MoviesDatabase.COLUMN_MOVIE_TITLE, movie.getTitle());
        values.put(MoviesDatabase.COLUMN_MOVIE_DATA, jsonMovie);
        values.put(MoviesDatabase.COLUMN_MOVIE_IMAGE, bArray);
        db.insert(MoviesDatabase.TABLE_NAME, null, values);

    }

}