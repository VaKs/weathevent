package Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import POJO.Preference;
import POJO.User;

/**
 * Created by sergio on 25/03/2018.
 */

@Database(version = 1, entities = {User.class, Preference.class})
public abstract class AppDatabase extends RoomDatabase {

    // Singleton pattern to centralize access to the database
    private static AppDatabase database;

    public synchronized static AppDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, AppDatabase.class, "weathevent_db")
                    //.allowMainThreadQueries() // This should only be used for testing
                    .build();
        }
        return database;
    }

    // Provides access to available operations on the database
    public abstract UserDAO userDAO();
    public abstract PreferenceDAO preferenceDAO();
}
