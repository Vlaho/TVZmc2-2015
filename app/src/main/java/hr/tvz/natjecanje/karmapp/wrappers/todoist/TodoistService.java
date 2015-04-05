package hr.tvz.natjecanje.karmapp.wrappers.todoist;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

import java.util.List;

/**
 * Created by Tomislav on 29.3.2015..
 */
public interface TodoistService {
    @GET("/API/login")
    void authorize(@Query("email") String email, @Query("password") String password, Callback<TodoistToken> callback);

    @GET("/API/getProjects")
    List<TodoistProject> getProjects(@Query("token") String token);

    @GET("/API/getUncompletedItems")
    List<TodoistTask> getUncompletedItems(@Query("token") String token, @Query("project_id") long projectId);
}
