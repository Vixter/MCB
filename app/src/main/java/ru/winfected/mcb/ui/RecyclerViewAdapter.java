package ru.winfected.mcb.ui;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }

    public RecyclerViewAdapter(ArrayList<Movie> characters){
        comicArrayList = characters;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView cardView = (CardView) layoutInflater.inflate(R.layout.recycler_item, parent, false);
        ViewHolder vh = new ViewHolder(cardView);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie item = comicArrayList.get(position);

        TextView textView = (TextView) holder.cardView.findViewById(R.id.card_view_textview);
        ImageView imageView = (ImageView) holder.cardView.findViewById(R.id.card_view_image);

        textView.setText(item.getTitle());
        //imageView.setImageBitmap();

    }

    @Override
    public int getItemCount() {
        return comicArrayList.size();
    }
}
