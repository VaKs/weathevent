package POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

/**
 * Created by servabo on 21/03/2018.
 */
@IgnoreExtraProperties
@Entity(tableName = "Users",indices = { @Index("email")})
public class User {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    //@ColumnInfo(name = "friendsList")
    @Ignore
    private ArrayList<User> friendsList;
    @NonNull
    @ColumnInfo(name = "password")
    private String password;

    //@ColumnInfo(name = "preference")
    @Ignore
    private Preference preference;

    public User(){
        setFriendsList(new ArrayList<User>());
    }

    public User(@NonNull String email, @NonNull String name, String surname, @NonNull String password){
        friendsList = new ArrayList<>();
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setPassword(password);
    }

    public User(@NonNull String email, @NonNull String name, String surname, ArrayList<User> friendsList, @NonNull String password, Preference preference){
        friendsList = new ArrayList<>();
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setFriendsList(friendsList);
        this.setPassword(password);
        this.setPreference(preference);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<User> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<User> friendsList) {
        this.friendsList = friendsList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }
}
