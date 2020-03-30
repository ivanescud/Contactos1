package com.simplelifestudio.contactos1.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.simplelifestudio.contactos1.Adapters.Adapter;
import com.simplelifestudio.contactos1.Model.User;
import com.simplelifestudio.contactos1.R;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView mainList;
    private Button mainAddButton;
    private FirebaseFirestore db;

    private ArrayList<User> database = new ArrayList<>();
    private Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        updateDb();

        mainAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AddContact.class));
            }
        });
    }


    private void init() {

        mainList = findViewById(R.id.mainListLV);
        mainAddButton = findViewById(R.id.mainAddBT);

        db = FirebaseFirestore.getInstance();

    }



    private void updateDb() {

        db.collection("users")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        Map<String,Object> map = document.getData();

                        User user = new User();
                        user.setNombre(map.get("nombre").toString());
                        user.setApellido(map.get("apellido").toString());
                        user.setNumTelf(map.get("numTelf").toString());
                        user.setEmail(map.get("email").toString());
                        user.setUserID(document.getId());

                        database.add(user);
                    }

                    adapter = new Adapter(MainActivity.this, R.layout.cell1,database);

                    mainList.setAdapter(adapter);

                    mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            String userID = database.get(i).getUserID();
                            startActivity(new Intent(getApplicationContext(),UserDetail.class).putExtra("ID",userID));




                        }
                    });


                } else {
                    Log.w("Database", "Error getting documents.", task.getException());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("DatabaseFail:"," "+e.toString());
            }
        });
    }

}
