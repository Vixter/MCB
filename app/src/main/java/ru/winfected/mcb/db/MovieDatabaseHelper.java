package ru.winfected.mcb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ru.winfected.mcb.model.themoviedb.Movie;

/**
 * Created by winfe on 31.12.2015.
 */
public class MovieDatabaseHelper extends SQLiteOpenHelper {

    private static MovieDatabaseHelper sInstance;

    // Database Info
    private static final String DATABASE_NAME = "movieDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_MOVIES = "movies";

    // Movies Table Columns
    private static final String MOVIE_ID = "id";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_BACKDROP = "backdrop_path";
    private static final String MOVIE_POSTER = "poster_path";
    private static final String MOVIE_POPULARITY = "popularity";
    private static final String MOVIE_VOTE_AVERAGE = "vote_average";
    private static final String MOVIE_RELEASE_DATE = "release_date";



    public static synchronized MovieDatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MovieDatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    public MovieDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MOVIES_TABLE = "CREATE TABLE " + TABLE_MOVIES +
                "(" +
                MOVIE_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                MOVIE_TITLE + " TEXT, " +
                MOVIE_BACKDROP + " TEXT, " +
                MOVIE_POSTER + " TEXT, " +
                MOVIE_POPULARITY + " REAL, " +
                MOVIE_VOTE_AVERAGE + " REAL, " +
                MOVIE_RELEASE_DATE + " TEXT" +
                ")";

        db.execSQL(CREATE_MOVIES_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
            onCreate(db);
        }
    }

    public void addMovie(Movie movie){
        SQLiteDatabase db = getWritableDatabase();

        db.beginTransaction();
        try {

            ContentValues values = new ContentValues();
            values.put(MOVIE_ID, Integer.parseInt(movie.getId()));
            values.put(MOVIE_TITLE, movie.getTitle());
            values.put(MOVIE_POPULARITY, movie.getPopularity());
            values.put(MOVIE_BACKDROP, movie.getBackdrop_path());
            values.put(MOVIE_POSTER, movie.getPoster_path());
            values.put(MOVIE_VOTE_AVERAGE, movie.getVote_average());
            values.put(MOVIE_RELEASE_DATE, movie.getRelease_date());

            db.insertOrThrow(TABLE_MOVIES, null, values);
            db.setTransactionSuccessful();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            db.endTransaction();
        }

    }

    private List<Movie> getMovies(int page, boolean byPopularity){

        List<Movie> movies = new ArrayList<>();
        String MOVIE_SELECT_QUERY = null;
        if(byPopularity){
            MOVIE_SELECT_QUERY = String.format("SELECT * FROM %s ORDER BY %s DESC" , TABLE_MOVIES, MOVIE_VOTE_AVERAGE);
        }else {
            MOVIE_SELECT_QUERY = String.format("SELECT * FROM %s" , TABLE_MOVIES);
        }

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(MOVIE_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                int count = 0;
                do {
                    if(count < ((page) *20)) continue;
                    if (count > ((page) * 20 + 20)) break;
                    Movie movie = new Movie();
                    movie.setId(String.valueOf(cursor.getInt(cursor.getColumnIndex(MOVIE_ID))));
                    movie.setBackdrop_path(cursor.getString(cursor.getColumnIndex(MOVIE_BACKDROP)));
                    movie.setPopularity(cursor.getDouble(cursor.getColumnIndex(MOVIE_POPULARITY)));
                    movie.setPoster_path(cursor.getString(cursor.getColumnIndex(MOVIE_POSTER)));
                    movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
                    movie.setVote_average(cursor.getDouble(cursor.getColumnIndex(MOVIE_VOTE_AVERAGE)));
                    movie.setRelease_date(cursor.getString(cursor.getColumnIndex(MOVIE_RELEASE_DATE)));
                    movies.add(movie);
                } while(cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }
        return movies;
    }

    public List<Movie> getMoviesByPopularity(int page){
        return getMovies(page, true);
    }

    public List<Movie> getMoviesToDescribe(int page){
        return getMovies(page, false);
    }
}
