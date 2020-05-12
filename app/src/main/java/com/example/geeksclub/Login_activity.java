package com.example.geeksclub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;

public class Login_activity extends AppCompatActivity {
   private Spinner spinner;
private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
spinner=findViewById(R.id.spinnercountries);
spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.
        simple_spinner_dropdown_item,CountryData.countryNames));

editText=findViewById(R.id.editTextphone);
findViewById(R.id.button2
).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String code=CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
        String number=editText.getText().toString().trim();

        if(number.isEmpty()){
            editText.setError("Required Field");

        editText.requestFocus();
        return;
        }
        String phonenumber="+"+code+number;


        Intent intent=new Intent(getApplicationContext(),otp_Login.class);
intent.putExtra("phonenumber",phonenumber);
startActivity(intent);










    }
});




    }


    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){


            Intent intent=new Intent(getApplicationContext(),Attende_or_Host.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }
}
