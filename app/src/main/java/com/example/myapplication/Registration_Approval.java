package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;

public class Registration_Approval extends Activity {

    SharedPreferences sharedPreferences;
    int status;
    FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__approval);
        sharedPreferences=getSharedPreferences("MyHostel",MODE_PRIVATE);
        String email=sharedPreferences.getString("Email","");
//        Log.d("_______","__________");
//        Log.d("Email: ",email);
        DocumentReference document=firebaseFirestore.collection("Students").document(email);
        document.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    status= Integer.parseInt(documentSnapshot.get("approved").toString());
                    Log.d("_______","_______");
                    Log.d("Status: ",status+"");
                }
            }
        });

        if(status==1)
        {
            Intent intent=new Intent(Registration_Approval.this,Main2Activity.class);
            startActivity(intent);
            finish();
        }

    }
}

/*    SharedPreferences sharedPreferences;
    TextView t;
    int status = 1;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration__approval);
        sharedPreferences = getSharedPreferences("MyHostel", MODE_PRIVATE);
        setContentView(R.layout.activity_main2);
        String shared_email = sharedPreferences.getString("Email", "");
        ReadData(shared_email);
        if (status == 1) {
            Intent intent = new Intent(Registration_Approval.this, Main2Activity.class);
            startActivity(intent);
            finish();
        }

    }

    private void ReadData(String em) {
        DocumentReference user = db.collection("Students").document(em);
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    status = Integer.parseInt(doc.get("approved").toString());
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}*/
