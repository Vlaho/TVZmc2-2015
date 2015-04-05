package hr.tvz.natjecanje.karmapp.callbacks;

import hr.tvz.natjecanje.karmapp.wrappers.Doable;

import java.util.List;

/**
 * Created by Tomislav on 2.4.2015..
 */
public interface OnOverdueTasksRecievedListener {
    void onTasksReceived(List<Doable> items);
}
