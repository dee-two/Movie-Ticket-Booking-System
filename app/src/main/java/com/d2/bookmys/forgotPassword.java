package com.d2.bookmys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class forgotPassword extends AppCompatActivity {

    private Button forgotPassword;
    private EditText passwordEd1, passwordEd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/
        passwordEd1 = findViewById(R.id.forgot_password_p1);
        passwordEd2 = findViewById(R.id.forgot_password_p2);
        forgotPassword = findViewById(R.id.forgot_password_button);
        forgotPassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                String pass1 = passwordEd1.getText().toString();
                String pass2 = passwordEd2.getText().toString();
                if(pass1.equals(pass2)) {
                    Log.d(">>>>", "Correct Password !");

                }
                else{
                    Log.d(">>>>", "Incorrect Password !");

                }
            }
        });
    }

}
