package hr.tvz.natjecanje.karmapp.wrappers.todoist;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Tomislav on 29.3.2015..
 */
public interface TodoistService {
    @GET("/API/login")
    void authorize(@Query("email") String email, @Query("password") String password, Callback<TodoistToken> callback);
}
