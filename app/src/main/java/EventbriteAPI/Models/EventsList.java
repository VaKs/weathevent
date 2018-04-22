package EventbriteAPI.Models;

import java.util.ArrayList;
import java.util.List;

import EventbriteAPI.EventbriteI;

public class EventsList {

    private ArrayList<Event> events=new ArrayList<>();
    public Pagination Pagination;
    public Location Location;

    public List<Event> add(Event event){
        events.add(event);
        return events;
    }

    public ArrayList<Event> getEvents() {
            return events;
        }

    public void setEvents(ArrayList<Event> events) {
            this.events = events;
        }

}
