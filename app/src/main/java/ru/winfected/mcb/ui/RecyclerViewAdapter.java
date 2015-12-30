package ru.winfected.mcb.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import ru.winfected.mcb.R;
import ru.winfected.mcb.model.marvel.Character;
import ru.winfected.mcb.model.themoviedb.Movie;


/**
 * Created by winfe on 27.12.2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Movie> comicArrayList;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(LinearLayout item) {
            super(item);
            this.textView = (TextView) item.findViewById(R.id.card_view_textview);
            this.imageView = (ImageView) item.findViewById(R.id.card_view_image);
        }
    }

    public RecyclerViewAdapter(ArrayList<Movie> characters){
        comicArrayList = characters;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LinearLayout view = (LinearLayout) layoutInflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Movie item = comicArrayList.get(position);

        TextView textView = holder.textView;
        ImageView imageView = holder.imageView;
        textView.setText(item.getTitle());

        //imageView.setImageBitmap();

    }

    @Override
    public int getItemCount() {
        return comicArrayList.size();
    }
}
