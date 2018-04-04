package POJO;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.location.Location;
import android.media.Image;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.Date;
@IgnoreExtraProperties
@Entity(tableName = "Events",indices = { @Index("id")})
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "location")
    private Location location;
    private ArrayList<User> userList;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "dateBeginning")
    private Date dateBeginning;
    @ColumnInfo(name = "dateEnd")
    private Date dateEnd;
    @ColumnInfo(name = "price")
    private Integer price;
    @Ignore
    private Category category;
    @Ignore
    private Image image;

    public Event(){
        userList = new ArrayList<>();
    }

    public Event(Location location, ArrayList<User> userList, String name, String description, Date dateBeginning, Date dateEnd, Integer price, Category category, Image image){
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
