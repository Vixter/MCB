package ru.winfected.mcb.ui.themoviedb;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;

import ru.winfected.mcb.R;
import ru.winfected.mcb.model.themoviedb.Movie;


/**
 * Created by winfe on 27.12.2015.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewHolder> {

    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(ArrayList<Movie> characters){
        if(characters != null){
            movieArrayList = characters;
        } else {
            movieArrayList = new ArrayList<>();
        }
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public SimpleDraweeView imageView;


        public CardViewHolder(final LinearLayout item) {
            super(item);
            this.textView = (TextView) item.findViewById(R.id.card_view_textview);
            this.imageView = (SimpleDraweeView) item.findViewById(R.id.card_view_image);
        }

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
                + item.getPoster_path());

        imageView.setImageURI(imageUri);

    }

    public void addAll(@NonNull Collection collection){
        int curSize = getItemCount();
        movieArrayList.addAll(collection);
        notifyItemRangeInserted(curSize, getItemCount());
    }

    public String getMovieID(int position){
        return movieArrayList.get(position).getId();
    }

    public String getMovieTitle(int position){
        return movieArrayList.get(position).getTitle();
    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

}
