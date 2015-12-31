package ru.winfected.mcb.ui.themoviedb;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import ru.winfected.mcb.R;
import ru.winfected.mcb.model.themoviedb.Movie;
import ru.winfected.mcb.ui.CardViewHolder;


/**
 * Created by winfe on 27.12.2015.
 */
public class MovieAdapter extends RecyclerView.Adapter<CardViewHolder> {

    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(ArrayList<Movie> characters){
        movieArrayList = characters;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.recycler_item, parent, false);
        CardViewHolder vh = new CardViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Movie item = movieArrayList.get(position);

        TextView textView = holder.textView;
        SimpleDraweeView imageView = holder.imageView;
        textView.setText(item.getTitle());
        Uri imageUri = Uri.parse("http://image.tmdb.org/t/p/w185"
                + item.getBackdrop_path());

        imageView.setImageURI(imageUri);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }
}
