package pkg305_project;

import java.sql.Date;

/**
 *
 * @author shaha
 */
public class Event {
    private User user;
    private String eventName;
    private Date eventDate;
    private String Faculty;
    private String location;
    private String time;
    private String Details;
    

    public Event(User user, String name, Date eventDate, String Faculty, String location, String time, String Details) {
        this.user = user;
        this.eventName = name;
        this.eventDate = eventDate;
        this.Faculty = Faculty;
        this.location = location;
        this.time = time;
        this.Details = Details;
    }

    // Getter methods
    public User getUser() { return user; }
    public String getEventName() { return eventName; }
    public Date getEventDate() { return eventDate; }
    public String getFaculty() { return Faculty; }
    public String getLocation() { return location; }
    public String getTime() { return time; }
    public String getDetails() { return Details; }
}
