package com.d2.bookmys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class signup_page extends AppCompatActivity {

    private Button signup_submit_button;
    private EditText signup_username_et;
    private EditText signup_email_et;
    private EditText signup_password_et;
    private EditText signup_password_verify_et;
    private EditText signup_mobile_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_signup_page);
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        signup_username_et = findViewById((R.id.signup_username));
        signup_email_et = findViewById((R.id.signup_email));
        signup_password_et = findViewById((R.id.signup_password));
        signup_password_verify_et = findViewById((R.id.signup_password_verify));
        signup_mobile_et = findViewById((R.id.signup_mobile));
        signup_submit_button = findViewById(R.id.signup_submit);
        signup_submit_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String signup_password = signup_password_et.getText().toString();
                String signup_password_verify = signup_password_verify_et.getText().toString();
                String signup_username = signup_username_et.getText().toString();
                String signup_email = signup_email_et.getText().toString();
                String signup_mobile = signup_mobile_et.getText().toString();
                Log.d(">>>>>", ""+signup_password+" : "+signup_password_verify);
                if(!signup_password.equals(signup_password_verify)) {
                    Toast.makeText(getApplicationContext(), "Please enter correct password.", Toast.LENGTH_LONG).show();
                }
                else if(signup_mobile.length()!=10){
                    Toast.makeText(getApplicationContext(), "Please enter valid mobile number.", Toast.LENGTH_LONG).show();
                }
                else{
                    new Helper().execute(signup_username, signup_password, signup_email, signup_mobile);
                    Toast.makeText(getApplicationContext(), "Registration Successful. Please Login.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
