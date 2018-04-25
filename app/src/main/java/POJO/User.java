package POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.firebase.database.IgnoreExtraProperties;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by servabo on 21/03/2018.
 */
@IgnoreExtraProperties
@Entity(tableName = "Users",indices = { @Index("id")})
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "email")
    private String email;

    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "friendsList")
    protected ArrayList<String> friendsList;

    @ColumnInfo(name = "favoriteEventIdsList")
    protected ArrayList<Long> favoriteEventIdsList;

    @NonNull
    @ColumnInfo(name = "password")
    protected String password;

    @ColumnInfo(name = "preference")
    private Preference preference;

    public User(){
        this.setFriendsList(new ArrayList<String>());
        favoriteEventIdsList= new ArrayList<>();
    }

    public User(@NonNull String email, @NonNull String name, String surname, @NonNull String password){
        friendsList = new ArrayList<>();
        favoriteEventIdsList= new ArrayList<>();
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setPassword(password);
        this.preference=new Preference(5000);
    }

    public User(@NonNull String email, @NonNull String name, @NonNull String password){
        friendsList = new ArrayList<>();
        favoriteEventIdsList= new ArrayList<>();
        this.setEmail(email);
        this.setName(name);
        this.setPassword(password);
        this.preference=new Preference(5000);
    }

    public User(@NonNull String email, @NonNull String name, String surname, ArrayList<String> friendsList, @NonNull String password, Preference preference){
        favoriteEventIdsList= new ArrayList<>();
        this.setEmail(email);
        this.setName(name);
        this.setSurname(surname);
        this.setFriendsList(friendsList);
        this.setPassword(password);
        this.setPreference(preference);
    }

    public ArrayList<Long> getFavoriteEventIdsList() {
        return favoriteEventIdsList;
    }

    public Long getFavouriteEventId(){
        if(favoriteEventIdsList.size() > 0) {
            return favoriteEventIdsList.get(0);
        }else{
            return null;
        }
    }

    public void setFavoriteEventIdsList(ArrayList<Long> eventsIdsList) {
        this.favoriteEventIdsList = eventsIdsList;
    }
    public void addFavoriteEventId(Long id){
        this.favoriteEventIdsList.add(id);
    }
    public void removeFavoriteEventId(Long id){
        this.favoriteEventIdsList.remove(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<String> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(ArrayList<String> friendsList) {
        this.friendsList = friendsList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = getMD5(password);
    }

    public Preference getPreference() {
        return preference;
    }

    public void setPreference(Preference preference) {
        this.preference = preference;
    }

    public void addFriend(String email){
        this.friendsList.add(email);
    }
    public void removeFriend(String email){
        this.friendsList.remove(email);
    }

    public boolean checkPassword(String password){
        return this.password.equals(getMD5(password));
    }

    private String getMD5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest)
                hexString.append(Integer.toHexString(0xFF & aMessageDigest));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
