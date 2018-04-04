package POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.firebase.client.annotations.NotNull;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by servabo on 21/03/2018.
 */
@IgnoreExtraProperties
@Entity(tableName = "Preferences",
        foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "email",
        childColumns = "userEmail",
        onDelete = CASCADE))
public class Preference {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NotNull
    @ColumnInfo(name = "userEmail")
    private String userEmail;

    @ColumnInfo(name = "distance")
    private Integer distance;
    /*
            Weather Type
         */
    @Ignore
    private ArrayList<Category> categoriesList;
    /*
        Notifications And Define Prefered Notifications
     */
    @Ignore
    public Preference(@NotNull String userEmail){
        this.setUserEmail(userEmail);
        setCategoriesList(new ArrayList<Category>());
    }

    public Preference(@NotNull String userEmail, Integer distance){
        this.categoriesList = new ArrayList<>();
        this.setUserEmail(userEmail);
        this.setDistance(distance);
    }

    @Ignore
    public Preference(@NotNull String userEmail, Integer distance, ArrayList<Category> categoriesList){
        categoriesList = new ArrayList<>();
        this.setUserEmail(userEmail);
        this.setDistance(distance);
        this.setCategoriesList(categoriesList);
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public ArrayList<Category> getCategoriesList() {
        return categoriesList;
    }

    public void setCategoriesList(ArrayList<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
