package hr.tvz.natjecanje.karmapp.tasks;

import android.content.Context;
import android.util.Log;
import hr.tvz.natjecanje.karmapp.KarmApp;
import hr.tvz.natjecanje.karmapp.callbacks.OnOverdueTasksRecievedListener;
import hr.tvz.natjecanje.karmapp.utils.Keys;
import hr.tvz.natjecanje.karmapp.utils.TinyDB;
import hr.tvz.natjecanje.karmapp.wrappers.Doable;
import hr.tvz.natjecanje.karmapp.wrappers.todoist.TodoistProject;
import hr.tvz.natjecanje.karmapp.wrappers.todoist.TodoistService;
import hr.tvz.natjecanje.karmapp.wrappers.todoist.TodoistTask;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Tomislav on 2.4.2015..
 */
public class GetOverdueTodoistItemsTask implements Runnable {
    private TinyDB db;
    private OnOverdueTasksRecievedListener listener;

    public GetOverdueTodoistItemsTask(Context context, OnOverdueTasksRecievedListener listener) {
        this.listener = listener;
        db = new TinyDB(context);
    }

    @Override
    public void run() {
        TodoistService service = KarmApp.getTodoistService();
        String token = db.getString(Keys.TODOIST_TOKEN);
        List<TodoistProject> projects;
        List<TodoistTask> tasks = new ArrayList<TodoistTask>();

        projects = service.getProjects(token);
        for (TodoistProject project : projects) {
            tasks.addAll(service.getUncompletedItems(token, project.getId()));
        }

        List<Doable> overdueTasks = new ArrayList<Doable>();
        for (Doable item : tasks) {
            if (item.isOverdue())
                overdueTasks.add(item);
        }

        listener.onTasksReceived(overdueTasks);
    }
}
