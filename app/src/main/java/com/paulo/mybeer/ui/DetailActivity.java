package com.paulo.mybeer.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.paulo.mybeer.R;
import com.paulo.mybeer.model.data.entity.Beer;
import com.paulo.mybeer.model.data.presenter.DetailActivityPresenter;
import com.paulo.mybeer.model.data.presenter.MVP;
import com.squareup.picasso.Picasso;

import java.text.MessageFormat;

public class DetailActivity extends AppCompatActivity implements MVP.DetailActivityView {

    private DetailActivityPresenter presenter;
    private  ImageView url_image;
    private  TextView name;
    private  TextView tag_line;
    private  TextView desc;
    private  ImageButton favorite;
    private  Beer beer;
    private  TextView attenuation_level;
    private  TextView brewers_tips;
    private  TextView contributed_by;
    private  TextView first_brewed;
    private  TextView ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        bindIds();
        presenter = new DetailActivityPresenter(this);
        checkDataBeer();
        click(favorite);
    }

    private void checkDataBeer() {
        beer = presenter.hasData(getIntent());
        Log.d("LOG", beer.toString() );
        if (beer == null) onBackPressed();
        setData();
    }
    private void setData() {
        setPhoto(beer.getImage_url());

        String att = ( beer.getAttenuation_level() == 0F) ? getString(R.string.zero):  ""+beer.getAttenuation_level();
        attenuation_level.setText(getString(R.string.attenuation) +" "+att);
        String bre = ( beer.getBrewers_tips() == null) ? getString(R.string.no_data_brewers):  ""+beer.getBrewers_tips();
        brewers_tips.setText(getString(R.string.brewers_tip) +""+bre);
        String con = ( beer.getContributed_by() == null) ? getString(R.string.no_data_contributed):  ""+beer.getContributed_by();
        contributed_by.setText(getString(R.string.contributed) +""+ con);
        String fi = ( beer.getFirst_brewed() == null) ? getString(R.string.no_first_brewed):  ""+beer.getFirst_brewed();
        first_brewed.setText(getString(R.string.first_brewed) +""+fi);
        ph.setText(beer.getPh() == 0F ? getString(R.string.zero) : ""+beer.getPh());
        name.setText(beer.getName());
        desc.setText(beer.getDescription());
        tag_line.setText(beer.getTag_line());
    }
    private void setPhoto(String image_url) {
        Picasso.with(this)
                .load(image_url)
                .placeholder(R.drawable.beer)
                .error(R.drawable.no_image)
                .into(url_image);
    }
    private void click(ImageButton favorite) {
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveOrDeleteInFavorite();
            }
        });
    }
    private void bindIds() {
        url_image = findViewById(R.id.url_image);
        name = findViewById(R.id.name);
        tag_line = findViewById(R.id.tag_line);
        desc = findViewById(R.id.desc);
        favorite = findViewById(R.id.favorite);
        first_brewed = findViewById(R.id.first_brewed);
        brewers_tips = findViewById(R.id.brewers_tips);
        attenuation_level = findViewById(R.id.attenuation_level);
        contributed_by = findViewById(R.id.contributed_by);
        ph = findViewById(R.id.ph);
    }
    @Override
    public void updateFavorite(boolean isUpdate) {
        if (isUpdate) {
            update(R.drawable.ic_favorite_full);
        } else {
            update(R.drawable.ic_favorite_black_empty);
        }
    }
    @Override
    public Context getContext() {
        return this;
    }

    private void update(final int resource) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                favorite.setImageResource(resource);
            }
        });
    }
}
