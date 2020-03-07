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
import com.paulo.mybeer.model.data.presenter.MainActivityPresenter;
import com.paulo.mybeer.utils.Utils;
import com.squareup.picasso.Picasso;


public class ItemBeerAdapterMain extends RecyclerView.Adapter<MyViewHolder> {


    private MainActivityPresenter presenterMain;
    private Context mContext;
    private Beer beer;


    public ItemBeerAdapterMain(MainActivityPresenter presenterMain) {
        this.presenterMain = presenterMain;
        this.mContext = presenterMain.getContext();
    }
    private boolean checkInFavoriteList(Context context, Beer beer) {
        return Beer.getBeer(context, beer);
    }
    private void setColorFavorite(ImageButton holder, boolean isFavored) {
        holder.setImageResource( isFavored ? R.drawable.ic_favorite_full : R.drawable.ic_favorite_black_empty);
    }
    private void onClick(final ImageButton holder, final Beer beer, final boolean isDetail, final boolean isFavored) {
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDetail) {
                    detailPage(beer, isFavored);
                } else {
                    favoriteFromMainPageOrFavoritePAge(beer, holder);
                }
            }
        });
    }
    private void favoriteFromMainPageOrFavoritePAge(Beer beer, ImageButton holder) {
        boolean ok = Utils.saveOrDeleteInFavorite(presenterMain.getContext(), beer);
        Utils.toast(presenterMain.getContext(), ok ? presenterMain.getContext().getString(R.string.favorited) : presenterMain.getContext().getString(R.string.removed));
        holder.setImageResource(ok ? R.drawable.ic_favorite_full : R.drawable.ic_favorite_black_empty);

    }

    private void detailPage(Beer beer, boolean isFavored) {
        presenterMain.clickItemAdpaterBeer(beer, isFavored);

    }

    private void pagination(int position) {
        presenterMain.updateFab((presenterMain.getInitial_page() >= 2) ? true : false);
        if (position == (presenterMain.getTenPerPage() - 1)) {
            int actual = presenterMain.getInitial_page();
            presenterMain.setInitial_page(++actual);
            presenterMain.getFromApi(actual);
            Utils.toast(mContext, mContext.getString(R.string.page) + actual);


        }
    }

    //*******************************************************
    //  METHODS OVERRIDE
    // ********************************************************
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return new MyViewHolder(inflater.inflate(R.layout.item_beer, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        pagination(position);
        beer = presenterMain.getList().get(position);
        boolean isFavored = checkInFavoriteList( mContext, beer);
        setColorFavorite(holder.favorite, isFavored);
        onClick(holder.detail, beer, true, isFavored);
        onClick(holder.favorite, beer, false, isFavored);

        holder.tag_line.setText(beer.getTag_line());
        holder.name.setText(beer.getName());
        holder.note.setText((beer.getPh() == 0F ) ? "0.0" : ""+beer.getPh());

        Picasso.with(mContext)
                .load(beer.getImage_url())
                .placeholder(R.drawable.beer)
                .error(R.drawable.no_image)
                .into(holder.url_image);

    }
    @Override
    public int getItemCount() {
        return presenterMain.getList() == null ? 0 : presenterMain.getList().size();
    }

}
