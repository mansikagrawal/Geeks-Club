package com.example.geeksclub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity_Attend extends AppCompatActivity {


    ListView listView;
    MyAdapter adapter;
    Geek geek;
    String url="https://catastrophe3.000webhostapp.com/Retrieve.php";

public  static ArrayList<Geek> geekArrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__attend);
        androidx.appcompat.widget.Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("Geeks Club");
        setSupportActionBar(toolbar);
        listView=findViewById(R.id.mylistview);

        adapter=new MyAdapter(this,geekArrayList);

listView.setAdapter(adapter);
        retrieveData();
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


        AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
        ProgressDialog  progressDialog=new ProgressDialog(view.getContext());
        CharSequence[] dialogitem={"View Data","Update Data"};// There can be multiple options in this like add delete
        builder.setTitle(geekArrayList.get(position).getCollege());
        builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                // like wise can be many cases
                switch (i){


                    case 0:
startActivity(new Intent(getApplicationContext(),DetailActivity.class).
        putExtra("position",position));

                        break;

                    case 1:
                        startActivity(new Intent(getApplicationContext(),Do_Edit.class)
                        .putExtra("position",position));
                        break;


                }

            }
        });

        builder.create().show();

    }
});



    }

    public  void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                geekArrayList.clear();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equalsIgnoreCase("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("ID");
                            String name = object.getString("NAME");
                            String date = object.getString("DATE");

                            String hostname = object.getString("HOSTNAME");
                            String description = object.getString("DESCRIPTION");
                            String college = object.getString("COLLEGE");
                            String website = object.getString("WEBSITE");
                            String goodies = object.getString("GOODIES");
                            String contact = object.getString("CONTACT");
                            String price = object.getString("PRICE");

                            geek = new Geek(id, name, date, hostname, description, college, website, goodies, contact, price);
                            geekArrayList.add(geek);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(HomeActivity_Attend.this, "Done", Toast.LENGTH_SHORT).show();

                        }
                    }

                } catch (JSONException e) {
                    e.getStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity_Attend.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

}
