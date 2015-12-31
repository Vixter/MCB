package ru.winfected.mcb.ui.marvel.characters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import ru.winfected.mcb.R;
import ru.winfected.mcb.model.marvel.Character;
import ru.winfected.mcb.ui.CardViewHolder;

/**
 * Created by winfe on 31.12.2015.
 */
public class CharactersAdapter extends RecyclerView.Adapter<CardViewHolder>  {

    private ArrayList<Character> characterArrayList;

    public CharactersAdapter(ArrayList<Character> characters){
        this.characterArrayList = characters;
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
        Character item = characterArrayList.get(position);

        TextView textView = holder.textView;
        SimpleDraweeView imageView = holder.imageView;
        textView.setText(item.getName());


    }

    @Override
    public int getItemCount() {
        return characterArrayList.size();
    }
}
