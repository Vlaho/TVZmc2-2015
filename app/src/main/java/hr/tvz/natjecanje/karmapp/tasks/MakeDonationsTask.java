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
import hr.tvz.natjecanje.karmapp.wrappers.Doable;

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
        donationData.put("amount", "100");
        donationData.put("currency", "USD");
        donationData.put("description", "KarmApp donation");

        Looper.prepare();
        for (Doable item : items) {
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
                                        } else {
                                            Log.i(TAG, e.getMessage());
                                        }
                                    }
                                });
                            }
                            public void onError(Exception e) {
                                Log.i(TAG, e.getMessage());
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
