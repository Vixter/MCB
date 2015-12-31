package ru.winfected.mcb.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import ru.winfected.mcb.R;

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