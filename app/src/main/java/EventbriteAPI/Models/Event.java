package EventbriteAPI.Models;

import org.json.JSONObject;

import java.util.Date;

/* Rafal: I commented that import statement, because it is unknown package
 * and it looks that it is not necesarry for us in that moment
 */
//import sun.security.krb5.internal.Ticket;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Event implements JSONPopulator{

    public Name Name;
    public Description Description;
    public Integer Id;
    public String Url;
    public Start Start;
    public End End;
    public Date Created;
    public Date Changed;
    public boolean OnlineEvent;
    public String LogoId;
    public String OrganizerId;
    public String VenueId;
    public String CategoryId;
    public String ResourceUri;
    public Logo Logo;
    public Venue Venue;
    public Ticket Ticket;

    @Override
    public void populate(JSONObject data) {
        Description = new Description();
        Description.populate(data.optJSONObject("description"));
        Name = new Name();
        Name.populate(data.optJSONObject("name"));
        Id = data.optInt("id");
        Url = data.optString("url");
        Start = new Start();
        Start.populate(data.optJSONObject("start"));
        End = new End();
        End.populate(data.optJSONObject("end"));
        //Created = data.optString("created");
        //Changed = data.optString("changed");
        OnlineEvent = data.optBoolean("online_event");
        LogoId = data.optString("logo_id");
        OrganizerId = data.optString("organizer_id");
        VenueId = data.optString("venue_id");
        CategoryId = data.optString("category_id");
        //SubcategoryId;
        ResourceUri = data.optString("resource_uri");
        Logo = new Logo();
        Logo.populate(data.optJSONObject("logo"));
        Venue = new Venue();
        Venue.populate(data.optJSONObject("venue"));
    }

    public Name getName() {
        return Name;
    }

    public void setName(Name name) {
        Name = name;
    }

    public Description getDescription() {
        return Description;
    }

    public void setDescription(Description description) {
        Description = description;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Start getStart() {
        return Start;
    }

    public void setStart(Start start) {
        Start = start;
    }

    public End getEnd() {
        return End;
    }

    public void setEnd(End end) {
        End = end;
    }

    public Date getCreated() {
        return Created;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    public Date getChanged() {
        return Changed;
    }

    public void setChanged(Date changed) {
        Changed = changed;
    }

    public boolean isOnlineEvent() {
        return OnlineEvent;
    }

    public void setOnlineEvent(boolean onlineEvent) {
        OnlineEvent = onlineEvent;
    }

    public String getLogoId() {
        return LogoId;
    }

    public void setLogoId(String logoId) {
        LogoId = logoId;
    }

    public String getOrganizerId() {
        return OrganizerId;
    }

    public void setOrganizerId(String organizerId) {
        OrganizerId = organizerId;
    }

    public String getVenueId() {
        return VenueId;
    }

    public void setVenueId(String venueId) {
        VenueId = venueId;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public String getResourceUri() {
        return ResourceUri;
    }

    public void setResourceUri(String resourceUri) {
        ResourceUri = resourceUri;
    }

    public Logo getLogo() {
        return Logo;
    }

    public void setLogo(Logo logo) {
        Logo = logo;
    }

    public Venue getVenue() {
        return Venue;
    }

    public void setVenue(Venue venue) {
        Venue = venue;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Ticket getTicket() {
        return Ticket;
    }

    public void setTicket(Ticket ticket) {
        Ticket = ticket;
    }
}
