package hr.tvz.natjecanje.karmapp.wrappers;

/**
 * Created by Tomislav on 1.4.2015..
 */
public interface Doable {
    String getContent();
    boolean isOverdue();
    int daysOverdue();
}
