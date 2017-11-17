package com.sample.redditclient.data;

import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by osarapul on 16.11.2017.
 */

public class Entry implements Serializable {
    public String title;
    public String author;
    public URL thumbnail;
    public URL sourceImageUrl;
    public int numberOfComments;
    public long dataTimeCreated;

    public String getDateTimeCreated() {
        Calendar calendar = Calendar.getInstance();
        TimeZone tz = TimeZone.getDefault();
        calendar.setTimeInMillis(dataTimeCreated * 1000);
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currenTimeZone = calendar.getTime();
        return sdf.format(currenTimeZone);
    }
}
