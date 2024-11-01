package pkg305_project;

import java.sql.Date;

/**
 *
 * @author shaha
 */
public class Event {
    private String eventName;
    private Date eventDate;
    private User creator;
    private String Faculty;
    private String location;
    private String time;
    private String Details;
    

    public Event(String name, Date eventDate, User creator, String Faculty, String location, String time, String Details) {
        this.eventName = name;
        this.eventDate = eventDate;
        this.creator = creator;
        this.Faculty = Faculty;
        this.location = location;
        this.time = time;
        this.Details = Details;
    }

    // Getter methods
    public String getEventName() { return eventName; }
    public Date getEventDate() { return eventDate; }
    public User getCreator() { return creator; }
    public String getFaculty() { return Faculty; }
    public String getLocation() { return location; }
    public String getTime() { return time; }
    public String getDetails() { return Details; }
}
