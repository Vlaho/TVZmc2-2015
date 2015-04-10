package hr.tvz.natjecanje.karmapp;

import android.app.Application;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.parse.Parse;
import hr.tvz.natjecanje.karmapp.wrappers.todoist.TodoistService;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import java.util.Date;

/**
 * Created by Tomislav on 29.3.2015..
 */
public class KarmApp extends Application {
    private static TodoistService todoistService;

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, mySecret, mySecret);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://todoist.com")
                .setConverter(new GsonConverter(gson))
                .build();

        todoistService = restAdapter.create(TodoistService.class);
    }

    public static TodoistService getTodoistService() {
        return todoistService;
    }
}
