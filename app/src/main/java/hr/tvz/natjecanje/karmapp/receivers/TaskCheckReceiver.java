package hr.tvz.natjecanje.karmapp.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import hr.tvz.natjecanje.karmapp.callbacks.OnOverdueTasksRecievedListener;
import hr.tvz.natjecanje.karmapp.tasks.GetOverdueTodoistItemsTask;
import hr.tvz.natjecanje.karmapp.utils.Keys;
import hr.tvz.natjecanje.karmapp.utils.TinyDB;
import hr.tvz.natjecanje.karmapp.wrappers.Doable;

import java.util.List;

/**
 * Created by Tomislav on 30.3.2015..
 */
public class TaskCheckReceiver extends BroadcastReceiver implements OnOverdueTasksRecievedListener {
    private String TAG = getClass().getName();
    public static final int REQUEST_CODE = 1628;

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Starting task check");
        TinyDB db = new TinyDB(context);
        List<String> connectedApps = db.getList(Keys.CONNECTED_APPS);
        Log.i(TAG, "Connected apps: " + connectedApps);

        for (String app : connectedApps) {
            switch (app) {
                case Keys.TODOIST:
                    Log.i(TAG, "Checking Todoist tasks");
                    new Thread(new GetOverdueTodoistItemsTask(context, TaskCheckReceiver.this)).start();
                    break;
            }
        }
    }

    @Override
    public void onTasksReceived(List<Doable> items) {
        Log.i(TAG, "Received " + items.size() + " overdue items");
    }
}
