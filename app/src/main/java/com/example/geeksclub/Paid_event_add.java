package com.example.geeksclub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import java.util.UUID;

public class Paid_event_add extends AppCompatActivity {

String uniqueID;
     public EditText nameevent,date,hostname,descevent,college,webclg,goody,contact,price;
    EditText id;
Button btnsave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_event_add);
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
        btnsave=findViewById(R.id.insert);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Geeks Club");
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* uniqueID = UUID.randomUUID().toString();
                id.setText(uniqueID); */
                insertData();

            }


        });

    }


    private void insertData() {
        final String enrollid=id.getText().toString();
       final String  namevent = nameevent.getText().toString();
      final String Date=date.getText().toString();
      final String host=hostname.getText().toString();
      final String desc=descevent.getText().toString();
      final String col=college.getText().toString();
      final String wecl=webclg.getText().toString();
      final String good=goody.getText().toString();
      final String cont=contact.getText().toString();
      final String priz=price.getText().toString();
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");


      if(Date.isEmpty())
          date.setError("Required Field");
      progressDialog.show();

      StringRequest request=new StringRequest(Request.Method.POST,
              "https://catastrophe3.000webhostapp.com/1.php", new Response.Listener<String>() {
          @Override
          public void onResponse(String response) {

              if(response.equalsIgnoreCase("Records inserted successfully.")){
                  Toast.makeText(Paid_event_add.this, " Event Created", Toast.LENGTH_SHORT).show();
              progressDialog.dismiss();}

          else{
                  Toast.makeText(Paid_event_add.this, ""+response, Toast.LENGTH_SHORT).show();
          progressDialog.dismiss();}

          }

      }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(Paid_event_add.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
              progressDialog.dismiss();
          }
      }){
          @Override
          protected Map<String, String> getParams() throws AuthFailureError {

              Map<String,String> params=new HashMap<String, String>();
             params.put("ID",enrollid);

              params.put("NAME",namevent);
              params.put("DATE",Date);
              params.put("HOSTNAME",host);
              params.put("DESCRIPTION",desc);
              params.put("COLLEGE",col);
              params.put("WEBSITE",wecl);
              params.put("GOODIES",good);
              params.put("CONTACT", cont);
              params.put("PRICE",priz);



              return params;
          }
      };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);



    }




}
