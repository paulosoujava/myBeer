package com.paulo.mybeer.model.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.paulo.mybeer.R;
import com.paulo.mybeer.model.data.entity.Beer;
import com.paulo.mybeer.model.data.presenter.FavoriteActivityPresenter;
import com.squareup.picasso.Picasso;



public class ItemBeerAdapterFavorite extends RecyclerView.Adapter<MyViewHolder> {


    private FavoriteActivityPresenter presenterFavorite;
    private Beer beer;



    public ItemBeerAdapterFavorite(FavoriteActivityPresenter presenterFavorite) {
        this.presenterFavorite = presenterFavorite;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(presenterFavorite.getContext());
        return new MyViewHolder(inflater.inflate(R.layout.item_beer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        beer = presenterFavorite.getList().get(position);
         setColorFavorite(holder.favorite);

        onClick(holder.detail, beer, true);
        onClick(holder.favorite, beer, false);
        holder.note.setText((beer.getPh() == 0F  ) ? "0.0" : ""+beer.getPh());
        holder.tag_line.setText(beer.getTag_line());
        holder.name.setText(beer.getName());
        //set image
        Picasso.with(presenterFavorite.getContext())
                .load(beer.getImage_url())
                .placeholder(R.drawable.beer)
                .error(R.drawable.no_image)
                .into(holder.url_image);

    }

    private boolean checkInFavoriteList(Context context, Beer beer) {
        return Beer.getBeer(context, beer);
    }

    private void setColorFavorite(ImageButton holder) {
        holder.setImageResource(R.drawable.ic_favorite_full);
    }

    private void onClick(final ImageButton holder, final Beer beer, final boolean isDetail) {
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDetail) {
                    presenterFavorite.clickItemAdpaterFavoriteBeer(beer);
                } else {
                    favoriteFromMainPageOrFavoritePAge(beer, holder);

                }

            }
        });
    }
    private void favoriteFromMainPageOrFavoritePAge(Beer beer, ImageButton holder) {
            updateFavoriteColor(holder, presenterFavorite.clickItemAdpaterFavoriteRemoveBeer(beer));
    }
    private void updateFavoriteColor(ImageButton holder, boolean ok) {
        holder.setImageResource(ok ? R.drawable.ic_favorite_full : R.drawable.ic_favorite_black_empty);
    }
    @Override
    public int getItemCount() {
            return presenterFavorite.getList() == null ? 0 : presenterFavorite.getList().size();

    }


}
