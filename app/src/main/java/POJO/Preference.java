package POJO;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * Created by servabo on 21/03/2018.
 */
@IgnoreExtraProperties
public class Preference {

    private String city;

    private Integer distance;

    private ArrayList<Category> categoriesList;

    public Preference(){
        setCategoriesList(new ArrayList<Category>());
    }

    public Preference(Integer distance){
        this.categoriesList = new ArrayList<>();
        this.setDistance(distance);
    }
    public Preference(Integer distance, String city){
        categoriesList = new ArrayList<>();
        this.setDistance(distance);
        this.city=city;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
