package com.nsb.visions.varun.mynsb.Events;

import java.sql.Date;

/**
 * Created by varun on 21/01/2018. Coz varun is awesome as hell :)
 */

public class Event {

    public Integer eventID;
    public String eventName;
    public Date eventStart;
    public Date eventEnd;
    public String eventLocation;
    public String eventOrganiser;
    public String eventShortDesc;
    public String eventPictureUrl;

    public Event(int eventId, String eventName, String eventStart, String eventEnd, String eventLocation,
                 String eventOrganiser, String eventShortDesc, String eventPictureUrl) {

        this.eventID = eventId;
        this.eventName = eventName;
        this.eventLocation = eventLocation;
        this.eventOrganiser = eventOrganiser;
        this.eventShortDesc = eventShortDesc;
        this.eventPictureUrl = eventPictureUrl;

        // Convert the date strings to actual dates
        // TODO: Later


    }
}
