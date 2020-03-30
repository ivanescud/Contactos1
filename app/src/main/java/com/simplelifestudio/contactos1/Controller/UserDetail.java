package com.simplelifestudio.contactos1.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.simplelifestudio.contactos1.Model.User;
import com.simplelifestudio.contactos1.R;

import java.util.Map;

public class UserDetail extends AppCompatActivity {

    private TextView nombreTV;
    private TextView apellidoTV;
    private TextView telfTV;
    private TextView emailTV;
    private String Id;
    private Button callBT;

    private FirebaseFirestore db;
    private String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        init();

    }


    private void init() {

        nombreTV = findViewById(R.id.userDetailNombreTV);
        apellidoTV = findViewById(R.id.userDetailApellidoTV);
        telfTV = findViewById(R.id.userDetailTelfTV);
        emailTV = findViewById(R.id.userDetailEmailTV);
        callBT = findViewById(R.id.userDetailCallBT);

        db = FirebaseFirestore.getInstance();

        Id = getIntent().getStringExtra("ID");

        dbupdate();

    }


    private void dbupdate() {

        db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {


                for (QueryDocumentSnapshot document : task.getResult()) {

                    if (document.getId().toString().equals(Id)) {

                        Map<String, Object> data = document.getData();


                        nombreTV.setText(data.get("nombre").toString());
                        apellidoTV.setText(data.get("apellido").toString());
                        emailTV.setText(data.get("email").toString());
                        telfTV.setText(data.get("numTelf").toString());

                        num = data.get("numTelf").toString();

                    }


                }

                callBT.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent callintent = new Intent(Intent.ACTION_DIAL);
                        callintent.setData(Uri.parse("tel:"+num));


                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.

                            startActivity(callintent);
                            return;
                        }


                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }

}
