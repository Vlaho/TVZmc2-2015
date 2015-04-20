package hr.tvz.natjecanje.karmapp.tasks;

import android.os.Looper;
import android.util.Log;
import com.parse.FunctionCallback;
import com.parse.ParseCloud;
import com.parse.ParseException;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.AuthenticationException;
import hr.tvz.natjecanje.karmapp.donations.Donation;
import hr.tvz.natjecanje.karmapp.wrappers.Doable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Tomislav on 6.4.2015..
 */
public class MakeDonationsTask implements Runnable {
    private String TAG = getClass().getName();

    List<Doable> items;

    public MakeDonationsTask(List<Doable> items) {
        this.items = items;
    }

    @Override
    public void run() {
        Card card = new Card("4242424242424242", 12, 2016, "123");

        final HashMap donationData = new HashMap();
        final String amount = "100";
        final String currency = "USD";

        donationData.put("amount", amount);
        donationData.put("currency", currency);
        donationData.put("description", "KarmApp donation");

        Looper.prepare();
        for (final Doable item : items) {
            try {
                Stripe stripe = new Stripe(myPrecious);

                stripe.createToken(
                        card,
                        new TokenCallback() {
                            public void onSuccess(Token token) {
                                HashMap request = new HashMap<>(donationData);
                                request.put("token", token.getId());
                                // Send token to your server
                                ParseCloud.callFunctionInBackground("donate", request, new FunctionCallback<String>() {
                                    public void done(String result, ParseException e) {
                                        if (e == null) {
                                            Log.i(TAG, result);
                                            // Persist the donation in the database
                                            Donation d = new Donation(amount,
                                                    currency,
                                                    item.getContent(),
                                                    new Date(),
                                                    item.daysOverdue());
                                            d.save();
                                        } else {
                                            Log.e(TAG, e.getMessage());
                                        }
                                    }
                                });
                            }
                            public void onError(Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
                        }
                );
            } catch (AuthenticationException e) {
                e.printStackTrace();
            }
        }
        Looper.loop();
    }
}
