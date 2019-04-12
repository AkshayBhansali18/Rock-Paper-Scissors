package com.example.rockscissor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SigninActivity extends AppCompatActivity {
    EditText textUsername, textPassword;
    Button buttonSubmit;
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    CollectionReference users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        textPassword=findViewById(R.id.textPassword);
        textUsername=findViewById(R.id.textUsername);
        buttonSubmit=findViewById(R.id.signinButton);
        users=db.collection("users");


    }
    public void signinClick(View view)
    {
        users.whereEqualTo("username",textUsername.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot queryDocumentSnapshot:queryDocumentSnapshots)
                {
                    String docid=queryDocumentSnapshot.getId();
                    Toast.makeText(SigninActivity.this, docid, Toast.LENGTH_LONG).show();
                    Userinfo userinfo=queryDocumentSnapshot.toObject(Userinfo.class);
                    if(userinfo.password.equals(textPassword.getText().toString()))
                    {
                        Toast.makeText(SigninActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(SigninActivity.this,MainPageActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SigninActivity.this, "UnSuccess", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SigninActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
