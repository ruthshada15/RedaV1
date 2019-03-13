package com.example.redav1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseActivity extends AppCompatActivity {

    Button signupDriver, signupCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        signupDriver= (Button) findViewById(R.id.btndriver);
        signupCustomer= (Button) findViewById(R.id.btncustomer);

        signupDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SignupCustomerActivity.class));
            }
        });

        signupCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChooseActivity.this, SignupCustomerActivity.class));
            }
        });
    }
}
