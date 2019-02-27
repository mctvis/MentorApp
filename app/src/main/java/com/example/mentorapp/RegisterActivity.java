package com.example.mentorapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText mDisplayName;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private Button mCreateButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mDisplayName = findViewById(R.id.reg_name);
        mEmail = findViewById(R.id.reg_email);
        mPassword = findViewById(R.id.reg_password);
        mCreateButton = findViewById(R.id.reg_create_btn);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String display_name = mDisplayName.getEditableText().toString().trim();
                String email = mEmail.getEditableText().toString().trim();
                String password = mPassword.getEditableText().toString().trim();
                register_user(display_name, email, password);

            }
        });
    }

    private void register_user(final String display_name, final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    UserMentor userMentor = new UserMentor(display_name,email,password);
                    mDatabase = FirebaseDatabase.getInstance().getReference("UserMentor");
                            mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).
                            setValue(userMentor).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent mainIntent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(mainIntent);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this,task.toString(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(RegisterActivity.this, "You got some Error", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}
