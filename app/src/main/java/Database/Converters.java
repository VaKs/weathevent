package Database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import POJO.User;

/**
 * Created by vaks on 27/03/2018.
 */

public class Converters {
    @TypeConverter
    public static ArrayList<User> fromString(String value) {
        Type listType = new TypeToken<ArrayList<User>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<User> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
