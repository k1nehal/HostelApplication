package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Main2Activity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseFirestore firebaseFirestore=FirebaseFirestore.getInstance();
    int status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences=getSharedPreferences("MyHostel",MODE_PRIVATE);
        setContentView(R.layout.activity_main2);
        String shared_email=sharedPreferences.getString("Email", "");
        firebaseFirestore.collection("Students")
                .whereEqualTo("Personal Email",shared_email)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())
                        {
                           for( QueryDocumentSnapshot doc : task.getResult()) {
                               status = Integer.parseInt(doc.get("Approved").toString());
                               SharedPreferences.Editor editor = sharedPreferences.edit();
                               editor.putString("Stu_FName", doc.getString("First Name"));
                               editor.putString("Stu_LName", doc.getString("Last Name"));
                               editor.putString("Stu_Mobile", String.valueOf(doc.getLong("Mobile")));
                               editor.putString("M_Mobile",String.valueOf(doc.getLong("Mother's Mobile")));
                               editor.putString("F_Mobile",String.valueOf(doc.getLong("Father's Mobile")));
                               editor.putString("Room",doc.getString("Room No"));
                               editor.putString("ID",doc.getId());
                               editor.commit();
                           }
                        }
                    }
                });
        Log.d("______MAiIN","_________-");
        Log.d("_____",status+"");



        Log.d(shared_email,"My Mail");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View n_view=navigationView.getHeaderView(0);
        TextView textView=n_view.findViewById(R.id.textView_email);
        textView.setText(shared_email);



        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_dash, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
