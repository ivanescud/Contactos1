package com.simplelifestudio.contactos1.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.simplelifestudio.contactos1.Model.User;
import com.simplelifestudio.contactos1.R;

public class AddContact extends AppCompatActivity {

    private EditText nombreET;
    private EditText apellidoET;
    private EditText telfET;
    private EditText emailET;
    private Button addContactBT;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        init();


        addContactBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (nombreET.getText().toString().isEmpty()|| apellidoET.getText().toString().isEmpty()||telfET.getText().toString().isEmpty()|| emailET.getText().toString().isEmpty()){

                    Toast.makeText(AddContact.this, "Favor Ingresar todos los campos", Toast.LENGTH_SHORT).show();

                }else {

                    String nombre = nombreET.getText().toString().trim();
                    String apellido = apellidoET.getText().toString().trim();
                    String telf = telfET.getText().toString().trim();
                    String email = emailET.getText().toString().trim();


                    User user = new User();
                    user.setNombre(nombre);
                    user.setApellido(apellido);
                    user.setNumTelf(telf);
                    user.setEmail(email);


                    db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            finish();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(AddContact.this, "Error en creacion de contacto", Toast.LENGTH_SHORT).show();


                        }
                    });
                }
            }
        });


    }


    private void init(){

        nombreET = findViewById(R.id.addcontacNameET);
        apellidoET = findViewById(R.id.addContactLastET);
        telfET = findViewById(R.id.addcontacPhoneET);
        emailET = findViewById(R.id.addContactEmailET);
        addContactBT = findViewById(R.id.addcontactAddButtBT);
        db = FirebaseFirestore.getInstance();

    }

}
