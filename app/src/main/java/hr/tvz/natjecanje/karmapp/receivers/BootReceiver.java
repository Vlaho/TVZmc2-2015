package hr.tvz.natjecanje.karmapp.receivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Tomislav on 30.3.2015..
 */
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            Intent taskCheckIntent = new Intent(context, TaskCheckReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    context,
                    TaskCheckReceiver.REQUEST_CODE,
                    taskCheckIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT);

            long firstTriggerTime = getTimestampToday(12);

            alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    firstTriggerTime,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
            );

            if (firstTriggerTime < new Date().getTime()) {
                alarmManager.setRepeating(
                        AlarmManager.RTC_WAKEUP,
                        firstTriggerTime,
                        AlarmManager.INTERVAL_DAY,
                        pendingIntent
                );
            }
        }
    }

    private long getTimestampToday(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return calendar.getTimeInMillis();
    }
}
