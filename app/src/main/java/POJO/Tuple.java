package POJO;

/**
 * Created by David on 22/04/2018.
 */

public class Tuple<LatLang, String> {
    private LatLang latLang;
    private String name;

    public Tuple(LatLang latLang, String name){
        this.latLang = latLang;
        this.name = name;
    }
    public LatLang getLatLang() {return latLang;}
    public String getName() {return  name;}
}
