package POJO;

import java.util.ArrayList;

/**
 * Created by servabo on 21/03/2018.
 */

public class User {
    private String email;
    private String name;
    private String surname;
    private ArrayList<User> friendsList;
    private String password;
    private Preference preference;

    public void User(){
        setFriendsList(new ArrayList<User>());
    }
    public void User(String email, String name, String surname, ArrayList<User> friendsList, String password, Preference preference){
        friendsList = new ArrayList<User>();
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
