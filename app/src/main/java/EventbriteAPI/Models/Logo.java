package EventbriteAPI.Models;

import org.json.JSONObject;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Logo implements JSONPopulator{

    public String Id;
    public String Url;

    @Override
    public void populate(JSONObject data) {
        Id = data.optString("id");
        Url = data.optString("url");
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
