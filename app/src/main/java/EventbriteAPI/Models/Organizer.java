package EventbriteAPI.Models;

import org.json.JSONObject;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Organizer implements JSONPopulator{
    public String Name;
    public Description Description;
    public String Url;

    @Override
    public void populate(JSONObject data) {
        Name = data.optString("name");
        Url = data.optString("url");
        Description = new Description();
        Description.populate(data.optJSONObject("description"));
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public EventbriteAPI.Models.Description getDescription() {
        return Description;
    }

    public void setDescription(EventbriteAPI.Models.Description description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
