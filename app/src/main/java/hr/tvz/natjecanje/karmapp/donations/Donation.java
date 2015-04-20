package hr.tvz.natjecanje.karmapp.donations;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;

/**
 * Created by tomislav on 18.04.15..
 */
@Table(name = "Donations")
public class Donation extends Model {
    @Column(name = "Amount")
    private String amount;
    @Column(name = "Currency")
    private String currency;
    @Column(name = "Content")
    private String content;
    @Column(name = "Date")
    private Date date;
    @Column(name = "DaysOverdue")
    private int daysOverdue;

    public Donation() {
        super();
    }

    public Donation(String amount, String currency, String content, Date date, int daysOverdue) {
        super();
        this.amount = amount;
        this.currency = currency;
        this.content = content;
        this.date = date;
        this.daysOverdue = daysOverdue;
    }

    public String getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getContent() {
        return content;
    }

    public Date getDate() {
        return date;
    }

    public int getDaysOverdue() {
        return daysOverdue;
    }

    @Override
    public String toString() {
        return amount + " " + currency + " donated for '" + content + "' (overdue " + daysOverdue + " days)";
    }
}
