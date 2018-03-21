package POJO;

import android.location.Location;
import android.media.Image;

import java.util.ArrayList;
import java.util.Date;

public class Event {

    private Location location;
    private ArrayList<User> userList;
    private String name;
    private String description;
    private Date dateBeginning;
    private Date dateEnd;
    private Integer price;
    private Category category;
    private Image image;

    public void event(){
        userList = new ArrayList<>();
    }

    public void event(Location location, ArrayList<User> userList, String name, String description, Date dateBeginning, Date dateEnd, Integer price, Category category, Image image){
        userList = new ArrayList<>();
        this.location = location;
        this.userList = userList;
        this.name = name;
        this.description = description;
        this.dateBeginning = dateBeginning;
        this.dateEnd = dateEnd;
        this.price = price;
        this.category = category;
        this.image = image;

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateBeginning() {
        return dateBeginning;
    }

    public void setDateBeginning(Date dateBeginning) {
        this.dateBeginning = dateBeginning;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
