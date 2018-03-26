package EventbriteAPI;
import java.lang.reflect.Array;
import java.util.List;

import EventbriteAPI.Models.End;
import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.Start;
import EventbriteAPI.Models.Venue;

/**
 * Eventbrite functions.
 *
 * package Eventbrite_API
 */


public interface EventbriteI {

    /**
     * Get an array of Eventbrite events, in the format expected by EventbriteEvent
     * param array of objects Parameters for the eventbriteGetEvents endpoint, to be passed during the API call.
     * return object API results
     */
    List<Event> eventbriteGetEvents(Object[] params);
    /**
     * Retrieves event data given an event ID.
     *param string id The Eventbrite event ID to be requested.
     * return object Event
     */
    Event eventbriteGetEvent(String id);
    /**
     * Search Eventbrite public events by any user.
     * param  array of objects Parameters for the eventSearch endpoint, to be passed during the API call.
     * return object API results
     */
    Event eventbriteSearch(Object[] params);

    /**
     * Determine if a query is for an event single view.
     *
     * param  Integer PagneNumber, or an Eventbrite_Query object.
     * return bool True if an event single view, false otherwise.
     */
    boolean eventbriteIsSingle(Integer PageCount);
    /**
     * Determine if a given object, given ID, or the current post is an Eventbrite event.
     *
     * param string id The Eventbrite event ID to be requested.
     * return bool True if it's an Eventbrite event, false otherwise.
     */
    boolean eventbriteIsEvent(String id);
    /**
     * Output event information such as date, time, venue, and organizer
     */
    void eventbriteEventMeta();
    /**
     * Return an event's time.
     *
     * return string Event time.
     */
    String eventbriteEventTime();
    /**
     * Determine if an event spans multiple calendar days.
     * return bool True if start and end date are the same, false otherwise.
     */
    boolean eventbriteIsMultidayEvent();
    /**
     * Give the URL to an event's public viewing page on eventbrite.com.
     * return string URL on eventbrite.com
     */
    String eventbriteEventEbUrl();
    /**
     * Give access to the current event's venue properties: address, resourceUri, id, name, latitude, longitude
     * return object Venue info
     */
    Venue eventbriteEventVenue();
    /**
     * Give access to the current event's start time: timezone, local, utc
     * return object Start time properties
     */
    Start eventbriteEventStart();
    /**
     * Give access to the current event's end time: timezone, local, utc
     * return object End time properties
     */
    End eventbriteEventEnd();
    /**
     * Output a link to edit the current event on eventbrite.com.
     * param string $text Anchor text.
     * param string $before Display before edit link.
     * param string $after Display after edit link.
     */
    String eventbriteEditPostLink(String text, String before, String after);
    /**
     * Check if everything we need is working: Keyring is installed, activated, and has a valid user connection to Eventbrite.
     * return bool True if a valid user token exists, false otherwise.
     */
    boolean eventbriteHasActiveConnection();

}
