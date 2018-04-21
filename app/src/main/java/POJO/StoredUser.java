package POJO;
import android.arch.persistence.room.Ignore;
import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by vaks on 21/04/2018.
 */

public class StoredUser extends User {

    public StoredUser(@NonNull int id,@NonNull String email, @NonNull String name, String surname, ArrayList<User> friendsList, @NonNull String password, Preference preference){
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setFriendsList(friendsList);
        this.password=password;
        this.setPreference(preference);
    }

    @Ignore
    public StoredUser(){}

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

}
