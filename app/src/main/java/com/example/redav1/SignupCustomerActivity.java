package com.example.redav1;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignupCustomerActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText strfname, strlname, strphone, stremail, strpassword;
    Button getstarted;
    CircleImageView imagedp;
    int Gallery_pick = 1;
    //StorageReference UserProfileImageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_customer);

        mAuth = FirebaseAuth.getInstance();
        getstarted = (Button) findViewById(R.id.btngetstarted);
        strfname = (EditText) findViewById(R.id.txtfname);
        strlname = (EditText) findViewById(R.id.txtlname);
        strphone = (EditText) findViewById(R.id.txtphone);
        stremail = (EditText) findViewById(R.id.txtemail);
        strpassword = (EditText) findViewById(R.id.txtpassword);
        imagedp = (CircleImageView) findViewById(R.id.img_dp);

        imagedp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent UserGallery = new Intent();
                UserGallery.setAction(Intent.ACTION_GET_CONTENT);
                UserGallery.setType("image/*"); //What type of data we want to take from the gallery
                startActivityForResult(UserGallery, Gallery_pick);


            }
        });



        getstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = strfname.getText().toString().trim();
                String lname = strlname.getText().toString().trim();
                String phone = strphone.getText().toString().trim();
                String email = stremail.getText().toString().trim();
                String password = strpassword.getText().toString().trim();

                validation(fname,lname,phone,email,password);
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_pick && resultCode == RESULT_OK && data != null) {
            Uri ImageUri = data.getData();

            // start picker to get image for cropping and then use the image in cropping activity
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1, 1)
                    .start(this);


        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (requestCode == RESULT_OK) {
                Uri resultUri = result.getUri();
            }

        }

    }


    private void validation(String a, String b, String c, String d, String e) {
        if (a.isEmpty()) {
            strfname.setError("Enter your First Name");
            strfname.requestFocus();
            return;
        }
        if (b.isEmpty()) {
            strlname.setError("Enter your Lname");
            strlname.requestFocus();
            return;
        }

        if (c.isEmpty()) {
            strphone.setError("Enter your Phone Number");
            strphone.requestFocus();
            return;
        }
        if (d.isEmpty()) {
            stremail.setError("Enter your Email Adress");
            stremail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher((CharSequence) e).matches()){
            stremail.setError("Enter a Valid* Email Adress");
            stremail.requestFocus();
            return;
        }
        if (e.isEmpty()){
            strpassword.setError("Enter your Password");
            strpassword.requestFocus();
            return;
        }
        if (e.length()<6){
            strpassword.setError("Password is too short");
            strpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(d,e).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}