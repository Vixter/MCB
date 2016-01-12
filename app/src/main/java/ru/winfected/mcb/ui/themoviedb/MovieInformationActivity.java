package ru.winfected.mcb.ui.themoviedb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import ru.winfected.mcb.R;
import ru.winfected.mcb.model.themoviedb.MovieItem;
import ru.winfected.mcb.network.themoviedb.MovieConfig;
import ru.winfected.mcb.network.themoviedb.MoviesRestRequest;

public class MovieInformationActivity extends AppCompatActivity implements Callback<MovieItem> {

    TextView textViewTitle;
    SimpleDraweeView imageView;
    TextView textViewScore;
    TextView textViewAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_information);
        Intent intent = getIntent();
        String id = intent.getStringExtra("MovieID");
        String title = intent.getStringExtra("MovieTitle");

        textViewTitle = (TextView) findViewById(R.id.titleText);
        textViewTitle.setText(title);
        imageView = (SimpleDraweeView) findViewById(R.id.view_image);
        textViewScore = (TextView) findViewById(R.id.textScore);
        textViewAbout = (TextView) findViewById(R.id.textAbout);

        MovieConfig config = new MovieConfig(getString(R.string.themoviedb_api_key));
        config.getRetrofit().create(MoviesRestRequest.class).getMovieByID(id).enqueue(this);

    }

    @Override
    public void onResponse(Response<MovieItem> response, Retrofit retrofit) {
        MovieItem movieItem = response.body();
        textViewScore.setText(movieItem.getVote_average());
        textViewAbout.setText(movieItem.getOverview());
        imageView.setImageURI(Uri.parse("http://image.tmdb.org/t/p/w185" + movieItem.getBackdrop_path()));
    }

    @Override
    public void onFailure(Throwable t) {

    }
}
