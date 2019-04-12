package com.example.rockscissor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db;
    CollectionReference users;
    EditText editText_username,editText_password;
    Button signinButton,registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        editText_password=findViewById(R.id.editText_password);
        editText_username=findViewById(R.id.editText_username);
        signinButton=findViewById(R.id.signinButton);
        registerButton=findViewById(R.id.registerButton);
        db= FirebaseFirestore.getInstance();
        users=db.collection("users");

    }
    public void registerClick(View view)
    {
        String username=editText_username.getText().toString();
        String password=editText_password.getText().toString();
        if(username.isEmpty()||password.isEmpty())
        {
            editText_username.setError("Fuck off");
        }
        else
        {
            Userinfo userinfo=new Userinfo(username,password);
            users.add(userinfo).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("Main Activity",e.toString());
                    Toast.makeText(MainActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    public void signinClick(View view)
    {
        Intent intent=new Intent(MainActivity.this,SigninActivity.class);
        startActivity(intent);
    }
}
