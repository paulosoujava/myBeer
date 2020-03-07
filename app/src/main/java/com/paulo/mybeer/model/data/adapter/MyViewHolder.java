package com.paulo.mybeer.model.data.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.paulo.mybeer.R;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView url_image;
    TextView name;
    TextView tag_line;
    TextView note;
    ImageButton favorite;
    ImageButton detail;

    public MyViewHolder(View v) {
        super(v);
        detail = v.findViewById(R.id.detail);
        favorite = v.findViewById(R.id.favorite);
        name = v.findViewById(R.id.name);
        tag_line = v.findViewById(R.id.tag_line);
        note = v.findViewById(R.id.note);
        url_image = v.findViewById(R.id.url_image);

    }
}