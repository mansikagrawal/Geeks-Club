package com.example.geeksclub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity implements PaymentResultListener {

    private static final String TAG = "Payment Error";
    TextView nameevent,date,hostname,descevent,college,webclg,goody,contact,price;
    Button btn;
int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameevent=findViewById(R.id.eventname);
        date=findViewById(R.id.event_date);
        hostname=findViewById(R.id.eventhostname);
        descevent=findViewById(R.id.eventdsec);
        college=findViewById(R.id.collegename);
        webclg=findViewById(R.id.websitecollege);
        goody=findViewById(R.id.goodies);
        contact=findViewById(R.id.contact);
        btn=findViewById(R.id.pay);
        price=findViewById(R.id.price);
        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");
        nameevent.setText("NAME OF THE EVENT\n"+HomeActivity_Attend.geekArrayList.get(position).getName());
        date.setText("DATE OF THE EVENT\n"+HomeActivity_Attend.geekArrayList.get(position).getDate());
        hostname.setText("EVENT HOST\n"+HomeActivity_Attend.geekArrayList.get(position).getHostname());
        descevent.setText("DESCRIPTION \n"+HomeActivity_Attend.geekArrayList.get(position).getDescription());
        college.setText("COLLEGE\n"+HomeActivity_Attend.geekArrayList.get(position).getCollege());
        webclg.setText("URL OF COLLEGE \n"+HomeActivity_Attend.geekArrayList.get(position).getWebsite());
        goody.setText("PRIZE\n"+HomeActivity_Attend.geekArrayList.get(position).getGoodies());
        contact.setText("CONTACT HOST \n"+HomeActivity_Attend.geekArrayList.get(position).getContact());
        price.setText("PRICE OF EVENT (PAID OR UNPAID)\n"+HomeActivity_Attend.geekArrayList.get(position).getPrice());
        Checkout.preload(getApplicationContext());
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

startPayment();


        }
    });




    }
    public void startPayment() {


        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_3CVSPxMvKrUkKr");

        /**
         * Set your logo here
         */
     //   checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "geeks club");

            /**
             * Description can be anything
             * eg: Reference No. #123123 - This order number is passed by you for your internal reference. This is not the `razorpay_order_id`.
             *     Invoice Payment
             *     etc.
             */
            options.put("description", "Enroll");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("order_id", "order_9A33XWu170gUtm");
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", "5000");

            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();

    }
}
