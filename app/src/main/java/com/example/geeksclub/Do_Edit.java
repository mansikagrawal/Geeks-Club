package com.example.geeksclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Do_Edit extends AppCompatActivity {
    public EditText nameevent,date,hostname,descevent,college,webclg,goody,contact,price;
    public EditText id;
    Button btnsave;
  private   int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do__edit);
        id=findViewById(R.id.roll);
        nameevent=findViewById(R.id.eventname);
        date=findViewById(R.id.event_date);
        hostname=findViewById(R.id.eventhostname);
        descevent=findViewById(R.id.eventdsec);
        college=findViewById(R.id.collegename);
        webclg=findViewById(R.id.websitecollege);
        goody=findViewById(R.id.goodies);
        contact=findViewById(R.id.contact);
        price=findViewById(R.id.price);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Geeks Club");
        btnsave=findViewById(R.id.insert);


        Intent intent=getIntent();
        position=intent.getExtras().getInt("position");
        id.setText(HomeActivity_Attend.geekArrayList.get(position).getId());
        nameevent.setText(HomeActivity_Attend.geekArrayList.get(position).getName());

        date.setText(HomeActivity_Attend.geekArrayList.get(position).getDate());
        hostname.setText(HomeActivity_Attend.geekArrayList.get(position).getHostname());
        descevent.setText(HomeActivity_Attend.geekArrayList.get(position).getDescription());
        college.setText(HomeActivity_Attend.geekArrayList.get(position).getCollege());
        webclg.setText(HomeActivity_Attend.geekArrayList.get(position).getWebsite());
        goody.setText(HomeActivity_Attend.geekArrayList.get(position).getGoodies());
        contact.setText(HomeActivity_Attend.geekArrayList.get(position).getContact());
        price.setText(HomeActivity_Attend.geekArrayList.get(position).getPrice());
        final String enrollid=id.getText().toString();
        final String nameevent1=nameevent.getText().toString();
        final String date1=date.getText().toString();
        final String hostname1=hostname.getText().toString();
        final String descevent1=descevent.getText().toString();
        final String college1=college.getText().toString();
        final String webclg1=webclg.getText().toString();
        final String goody1=goody.getText().toString();
        final String contact1=contact.getText().toString();
        final String price1=price.getText().toString();

Button btn=findViewById(R.id.insert);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {




final ProgressDialog progressDialog=new ProgressDialog(getApplicationContext());
progressDialog.setMessage("Updating...");
progressDialog.show();
        StringRequest request=new StringRequest(Request.Method.POST, "https://catastrophe3.000webhostapp.com/update.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Do_Edit.this, ""+response, Toast.LENGTH_SHORT).show();
progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(Do_Edit.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<String, String>();
                params.put("ID",enrollid);

                params.put("NAME",nameevent1);
                params.put("DATE",date1);
                params.put("HOSTNAME",hostname1);
                params.put("DESCRIPTION",descevent1);
                params.put("COLLEGE",college1);
                params.put("WEBSITE",webclg1);
                params.put("GOODIES",goody1);
                params.put("CONTACT", contact1);
                params.put("PRICE",price1);


                return super.getParams();
            }
        };


        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);



    }
});



    }
}
