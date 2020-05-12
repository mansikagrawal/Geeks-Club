package com.example.geeksclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_Login extends AppCompatActivity {

private FirebaseAuth mauth;
private EditText editText;
private ProgressBar progressBar;
    private  String verificationid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp__login);
mauth=FirebaseAuth.getInstance();
editText=findViewById(R.id.editTextCode);
progressBar=findViewById(R.id.progressbar);
        String phonenumber=getIntent().getStringExtra("phonenumber");

sendVerificationCode(phonenumber);
findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

String code=editText.getText().toString().trim();
if(code.isEmpty() || code.length()<6)
{
    editText.setError("Enter code");
    editText.requestFocus();
    return;
}

        verifyCode(code);

    }
});

    }



    private  void verifyCode(String code)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationid,code);
        signinwithCredential(credential);
    }

    private void signinwithCredential(PhoneAuthCredential credential) {

    mauth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {

            if(task.isSuccessful())
            {
               //  startActivity( new Intent(getApplicationContext(),HomeActivity.class));
Intent intent=new Intent(getApplicationContext(),Attende_or_Host.class);
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
startActivity(intent);

            }
            else
            {
                Toast.makeText(otp_Login.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }


        }
    });
    }


    private  void sendVerificationCode(String number)
    {
          progressBar.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallBack);


    }

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

       verificationid =s;

        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
String code=phoneAuthCredential.getSmsCode();
if(code!=null)
{editText.setText(code);
    //progressBar.setVisibility(View.VISIBLE);
    verifyCode(code);
}
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(otp_Login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}
