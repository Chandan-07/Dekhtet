package com.wordpress.keepup395.dekhte;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signin extends AppCompatActivity {
    RelativeLayout activity_signin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        final EditText etEmail = (EditText) findViewById(R.id.etEmailSignin);
        final EditText etPassword = (EditText) findViewById(R.id.etPasswordSignIn);
        Button btnSignin = (Button) findViewById(R.id.btnSignin);
        final ProgressDialog progressDialog = new ProgressDialog(this);

        final FirebaseUser Auth = FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseAuth mAuth = FirebaseAuth.getInstance();
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                    progressDialog.setMessage("Signing in...");
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(Signin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent i = new Intent(Signin.this, ControlAdmin.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Signin.this, "This Email is not registered", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                    progressDialog.dismiss();
                } else {
                    Toast.makeText(Signin.this, "Some fields are empty", Toast.LENGTH_LONG).show();
                }
            }
        });



    }


}

