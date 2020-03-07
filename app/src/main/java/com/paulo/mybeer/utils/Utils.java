package com.paulo.mybeer.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.paulo.mybeer.model.data.entity.Beer;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static void  toast(Context context, String txt){
        Toast.makeText(context, txt, Toast.LENGTH_SHORT).show();
    }

    public static boolean saveOrDeleteInFavorite(Context context, Beer beer) {
        List<Beer> list = Beer.getBeers(context );
        //ADD FIRST FAVORITE
        if (list == null) {
            list = new ArrayList<>();
            list.add(beer);
            Beer.save(context, list);
            return true;
        } else {

            //DELETE FAVORITE
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId() == beer.getId()) {
                    list.remove(i);
                    Beer.save(context, list);
                    return false;
                }
            }
            //ADD  FAVORITE
            Beer.saveBeer(context, beer);
            return  true;

        }
    }
}
