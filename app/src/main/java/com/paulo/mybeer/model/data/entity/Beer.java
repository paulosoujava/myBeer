package com.paulo.mybeer.model.data.entity;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.paulo.mybeer.utils.Prefs;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Beer implements Parcelable {


    //KEY
    public static final String KEY_BEER = "KEY_BEER";
    public static final String FAVORITE = "FAVORITE";


    private float id;
    private String name;
    @SerializedName("tagline")
    private String tag_line;
    private String first_brewed;
    private String description;
    private String image_url;
    private float attenuation_level;
    private String brewers_tips;
    private String contributed_by;
    private float ph;


    protected Beer(Parcel in) {
        id = in.readFloat();
        name = in.readString();
        tag_line = in.readString();
        first_brewed = in.readString();
        description = in.readString();
        image_url = in.readString();
        attenuation_level = in.readFloat();
        brewers_tips = in.readString();
        contributed_by = in.readString();
        ph = in.readFloat();
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

    //SAVE BEER IN prefs to show in FAVORITE
    public static void saveBeer(Context context, Beer beer) {
        List<Beer> list = getBeers(context);
        if (list == null) {
            list = new ArrayList<>();
            list.add(beer);
            save(context, list);
        } else {
            list.add(beer);
            save(context, list);

        }
        Log.e("LOG", new Gson().toJson(list));
    }

    //get a list of beers in pref
    public static List<Beer> getBeers(Context context) {
        String json = Prefs.getStringInPrefs(context, Beer.KEY_BEER);

        if (json != null) {
            Type type = new TypeToken<List<Beer>>() {
            }.getType();
            return new Gson().fromJson(json, type);
        }

        return null;
    }

    //get a list of beers in pref
    public static boolean getBeer(Context context, Beer beer) {
        List<Beer> list = getBeers(context);
        if (list == null || list.isEmpty())
            return false;
        else{
            for (int i = 0; i < list.size(); i++ ){
                if( list.get(i).getId() == beer.getId() )
                    return  true;
            }
        }
        return false;
    }


    //save a list the beers
    public static void save(Context context, List<Beer> list) {
        Prefs.setStringInPrefs(context, Beer.KEY_BEER, new Gson().toJson(list));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(id);
        dest.writeString(name);
        dest.writeString(tag_line);
        dest.writeString(first_brewed);
        dest.writeString(description);
        dest.writeString(image_url);
        dest.writeFloat(attenuation_level);
        dest.writeString(brewers_tips);
        dest.writeString(contributed_by);
        dest.writeFloat(ph);
    }
}
