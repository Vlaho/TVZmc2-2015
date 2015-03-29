package hr.tvz.natjecanje.karmapp.wrappers.todoist;

/**
 * Created by Tomislav on 29.3.2015..
 */
public class TodoistToken {
    private String token;
    private String email;

    public TodoistToken(String token, String email) {

        this.token = token;
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }
}
