package POJO;

import java.util.ArrayList;

/**
 * Created by servabo on 21/03/2018.
 */

public class Preference {
    private Integer distance;
    /*
        Weather Type
     */
    private ArrayList<Category> categoriesList;
    /*
        Notifications And Define Prefered Notifications
     */
    public void Preference(){
        setCategoriesList(new ArrayList<>());
    }
    public void Preference(Integer distance, ArrayList<Category> categoriesList){
        categoriesList = new ArrayList<>();
        this.setDistance(distance);
        this.setCategoriesList(categoriesList);
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
}
