package hr.tvz.natjecanje.karmapp.wrappers.todoist;

import android.util.Log;
import hr.tvz.natjecanje.karmapp.wrappers.Doable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tomislav on 1.4.2015..
 */
public class TodoistTask implements Doable {
    private String TAG = getClass().getName();

    private String dueDate;
    private long id;

    public TodoistTask(long id, String dueDate) {
        this.id = id;
        this.dueDate = dueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public long getId() {
        return id;
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
}
