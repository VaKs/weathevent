package POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

//import com.google.firebase.database.IgnoreExtraProperties;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by servabo on 21/03/2018.
 */
@IgnoreExtraProperties
@Entity(tableName = "Preferences",
        foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = CASCADE))
public class Preference {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "userId")
    private int userId;

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
    public Preference(int userId){
        this.setUserId(userId);
        setCategoriesList(new ArrayList<Category>());
    }

    public Preference(int userId, Integer distance){
        this.categoriesList = new ArrayList<>();
        this.setUserId(userId);
        this.setDistance(distance);
    }

    @Ignore
    public Preference(int userId, Integer distance, ArrayList<Category> categoriesList){
        categoriesList = new ArrayList<>();
        this.setUserId(userId);
        this.setDistance(distance);
        this.setCategoriesList(categoriesList);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
