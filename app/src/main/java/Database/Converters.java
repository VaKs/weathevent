package Database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import POJO.Preference;
import POJO.User;

/**
 * Created by vaks on 27/03/2018.
 */

public class Converters {
    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
    @TypeConverter
    public static ArrayList<Long> toIntegerListfromString(String value) {
        Type listType = new TypeToken<ArrayList<Integer>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String toStringfromArrayList(ArrayList<Long> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @TypeConverter
    public static Preference toPreferenceFromString(String preference) {
        Type listType = new TypeToken<User>() {}.getType();
        return new Gson().fromJson(preference, listType);
    }

    @TypeConverter
    public static String fromArrayList(Preference preference) {
        Gson gson = new Gson();
        String json = gson.toJson(preference);
        return json;
    }
}
