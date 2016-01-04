package ru.winfected.mcb.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import ru.winfected.mcb.R;
import ru.winfected.mcb.model.themoviedb.Movie;

/**
 * Created by winfe on 31.12.2015.
 */
public class CardViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;
    public SimpleDraweeView imageView;

    public CardViewHolder(LinearLayout item) {
        super(item);
        this.textView = (TextView) item.findViewById(R.id.card_view_textview);
        this.imageView = (SimpleDraweeView) item.findViewById(R.id.card_view_image);
    }

}