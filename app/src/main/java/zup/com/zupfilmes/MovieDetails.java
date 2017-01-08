package zup.com.zupfilmes;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.UUID;

import static zup.com.zupfilmes.R.id.movieYear;

public class MovieDetails extends AppCompatActivity {


    private static final String PREFS = "movieDetailsList";
    private String movieDetailsJson = "";
    private String absolutePath = "";
    final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
    final int cacheSize = maxMemory / 8;
    Bitmap movieImage;

    private ImageView imageHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);

        Movie movie = new Movie();

        //get params
        Intent intent = getIntent();
        movieDetailsJson = intent.getStringExtra("movie");
        Boolean isEdit = intent.getBooleanExtra("isEdit", false);
        if(intent.getByteArrayExtra("movieImage") != null){
            movieImage =  BitmapFactory.decodeByteArray(intent.getByteArrayExtra("movieImage"), 0,intent.getByteArrayExtra("movieImage").length);
        }
        movie = new Gson().fromJson(movieDetailsJson, Movie.class);

        imageHeader = (ImageView) findViewById(R.id.imageTitle);

        TextView tvTitle = (TextView)findViewById(R.id.resumo);
        tvTitle.setText(movie.getTitle());

        TextView tvYear = (TextView)findViewById(movieYear);
        tvYear.setText(movie.getYear());

        TextView tvDescription = (TextView)findViewById(R.id.description);
        tvDescription.setText(movie.getPlot());

        TextView tvGenre = (TextView)findViewById(R.id.genre);
        tvGenre.setText(movie.getGenre());

        TextView tvRate = (TextView)findViewById(R.id.scoreRate);
        tvRate.setText(movie.getImdbRating());

        TextView tvDirector = (TextView)findViewById(R.id.directorPeople);
        tvDirector.setText(movie.getDirector());

        TextView tvActors = (TextView)findViewById(R.id.actors);
        tvActors.setText(movie.getActors());

        TextView tvAwards = (TextView)findViewById(R.id.awardsText);
        tvAwards.setText(movie.getAwards());

        if(isEdit){
            loadImageParam(movieImage);
        } else {
            String imageName = UUID.randomUUID().toString();
            DownloadImageTask task = new DownloadImageTask(imageHeader, movie, getApplicationContext());
            task.execute(movie.getPoster());
        }
    }

    private void loadImageParam(Bitmap image){
        if(image != null){
            imageHeader.setImageBitmap(image);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out);
    }
}
