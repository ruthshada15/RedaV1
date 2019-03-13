package com.example.redav1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    EditText email, password;
    Button login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        email = (EditText)findViewById(R.id.txtloginemail);
        password = (EditText) findViewById(R.id.txtloginpassword);
        login = (Button) findViewById(R.id.btnlogin);
        signup=(Button) findViewById(R.id.btnsignup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "Hello world", Toast.LENGTH_SHORT).show();
                //String email = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                //validation(email,pass);

            }

        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), ChooseActivity.class));
            }
        });

    }
    private void validation (String a, String b){
        if (a.isEmpty()){
            email.setError("Enter your Email Addres");
            email.requestFocus();
            return;
        }
        if (b.isEmpty()){
            password.setError("Enter your password");
            password.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(a).matches()){
            email.setError("Enter a Valid* Email Adress");
            email.requestFocus();
            return;
        }
        if (b.length()<6) {
            password.setError("Password is too short");
            password.requestFocus();
            return;
        }

    }
}
