package EventbriteAPI.Models;

import java.util.ArrayList;
import java.util.List;

import EventbriteAPI.EventbriteI;

public class EventsList {

    private List<Event> events=new ArrayList<>();
    public Pagination Pagination;
    public Location Location;

    public List<Event> add(Event event){
        events.add(event);
        return events;
    }

    public List<Event> getEvents() {
            return events;
        }

    public void setEvents(List<Event> events) {
            this.events = events;
        }

}
