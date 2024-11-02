package pkg305_project;

import java.sql.Date;

/**
 *
 * @author shaha
 */
public class Event {
    private String user;
    private String eventName;
    private String eventDate;
    private String Faculty;
    private String location;
    private String time;
    private String Details;

    public Event(String name, String user, String eventDate, String time, String location, String Faculty, String Details) {
        this.user = user;
        this.eventName = name;
        this.eventDate = eventDate;
        this.Faculty = Faculty;
        this.location = location;
        this.time = time;
        this.Details = Details;
    }

    // Getter methods
    public String getUser() {
        return user;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getFaculty() {
        return Faculty;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public String getDetails() {
        return Details;
    }
}
