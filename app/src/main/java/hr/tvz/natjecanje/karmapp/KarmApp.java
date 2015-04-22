package hr.tvz.natjecanje.karmapp;

import android.app.Application;
import com.activeandroid.ActiveAndroid;
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

<<<<<<< Updated upstream
        ActiveAndroid.initialize(this);
        Parse.initialize(this, mySecret, mySecret);
=======
        Parse.initialize(this, "BvBuEHQ6xnPoH4v5t2AuxDBUBRJaOCol4RrDCsQz", "8Tb3KGlQILLLvbu8CFB65klN6sM2LlIzRjDZUBlM");
>>>>>>> Stashed changes

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
