package Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import POJO.Preference;
import POJO.User;

/**
 * Created by vaks on 25/03/2018.
 */
@Dao
public interface PreferenceDAO {

    @Insert
    void addPreference(Preference preference);

    @Update
    void updatePreference(Preference preference);

    @Query ("SELECT * FROM Preferences WHERE userEmail = :email ")
    Preference getPreferenceByUserEmail(String email);

    // Delete all
    @Query("DELETE FROM Preferences")
    void clearPreferences();
}
