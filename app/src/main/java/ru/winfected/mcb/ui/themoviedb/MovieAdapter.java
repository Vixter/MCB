package ru.winfected.mcb.ui.themoviedb;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import ru.winfected.mcb.R;
import ru.winfected.mcb.model.themoviedb.Movie;


/**
 * Created by winfe on 27.12.2015.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewHolder> {

    private ArrayList<Movie> movieArrayList;

    public MovieAdapter(ArrayList<Movie> characters){
        movieArrayList = characters;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        public SimpleDraweeView imageView;
        private WeakReference<Context> contextWeakReference;


        public CardViewHolder(LinearLayout item) {
            super(item);
            contextWeakReference = new WeakReference<Context>(item.getContext());
            this.textView = (TextView) item.findViewById(R.id.card_view_textview);
            this.imageView = (SimpleDraweeView) item.findViewById(R.id.card_view_image);
        }


        @Override
        public void onClick(View v) {
             Toast.makeText(contextWeakReference.get(),
                     movieArrayList.get(getLayoutPosition()).getTitle(),
                     Toast.LENGTH_SHORT).show();
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

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }
}
