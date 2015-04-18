package hr.tvz.natjecanje.karmapp.wrappers.todoist;

import android.util.Log;
import hr.tvz.natjecanje.karmapp.wrappers.Doable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tomislav on 1.4.2015..
 */
public class TodoistTask implements Doable {
    private String TAG = getClass().getName();

    private String dueDate;
    private long id;
    private String content;

    public TodoistTask(long id, String dueDate, String content) {
        this.id = id;
        this.dueDate = dueDate;
        this.content = content;
    }

    public String getDueDate() {
        return dueDate;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public boolean isOverdue() {
        if (dueDate == null)
            return false;

        // Example Todoist date: "Sat 04 Apr 2015 23:59:59"
        SimpleDateFormat formatter = new SimpleDateFormat("ccc dd MMM yyyy HH:mm:ss");

        try {
            Date date = formatter.parse(dueDate);

            return date.before(new Date());
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int daysOverdue() {
        if (dueDate == null)
            return 0;

        // Example Todoist date: "Sat 04 Apr 2015 23:59:59"
        SimpleDateFormat formatter = new SimpleDateFormat("ccc dd MMM yyyy HH:mm:ss");

        try {
            Date date = formatter.parse(dueDate);

            long dateDifference = date.getTime() - new Date().getTime();

            return (int) Math.abs(TimeUnit.MILLISECONDS.toDays(dateDifference));
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }
}
