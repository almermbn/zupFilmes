package zup.com.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MoviesDatabase extends SQLiteOpenHelper {


    public static final String TABLE_NAME = "movies";
    public static final String _ID = "id";
    public static final String COLUMN_MOVIE_TITLE = "movie_title";
    public static final String COLUMN_MOVIE_DATA = "movie_data";
    public static final String COLUMN_MOVIE_IMAGE = "movie_image";

    private static final String TEXT_TYPE = " TEXT";
    private static final String BLOB_TYPE = " BLOB";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MoviesDatabase.TABLE_NAME + " (" +
                    MoviesDatabase._ID + " INTEGER PRIMARY KEY," +
                    MoviesDatabase.COLUMN_MOVIE_TITLE + TEXT_TYPE + COMMA_SEP +
                    MoviesDatabase.COLUMN_MOVIE_DATA + TEXT_TYPE + COMMA_SEP +
                    MoviesDatabase.COLUMN_MOVIE_IMAGE + BLOB_TYPE+ " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + MoviesDatabase.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Movies.db";

    public MoviesDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}