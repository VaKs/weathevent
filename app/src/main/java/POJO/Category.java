package POJO;

/**
 * Created by servabo on 21/03/2018.
 */

public class Category {
    private String name;

    public Category(){
    }

    public Category(String name){
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
