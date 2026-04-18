package com.example.projetws;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projetws.beans.Etudiant;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AddEtudiant extends AppCompatActivity implements View.OnClickListener {

    private EditText etLastName, etFirstName;
    private Spinner spCity;
    private RadioButton rbMale, rbFemale;
    private Button btnSubmit;
    private RequestQueue mRequestQueue;

    // Utilisation de 10.0.2.2 pour accéder au localhost de la machine hôte depuis l'émulateur.
    private static final String URL_POST_STUDENT = "http://10.0.2.2:8080/LAB9/ws/createEtudiant.php";
    private static final String TAG = "AddEtudiant";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);

        etLastName = findViewById(R.id.et_last_name);
        etFirstName = findViewById(R.id.et_first_name);
        spCity = findViewById(R.id.sp_city);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        btnSubmit = findViewById(R.id.btn_submit);

        mRequestQueue = Volley.newRequestQueue(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            saveStudentToServer();
        }
    }

    private void saveStudentToServer() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_POST_STUDENT,
                response -> {
                    Log.d(TAG, "Server Response: " + response);
                    Toast.makeText(AddEtudiant.this, "Étudiant ajouté avec succès !", Toast.LENGTH_SHORT).show();
                    
                    try {
                        Gson gson = new Gson();
                        Type listType = new TypeToken<Collection<Etudiant>>(){}.getType();
                        Collection<Etudiant> studentList = gson.fromJson(response, listType);
                        
                        if (studentList != null) {
                            for (Etudiant student : studentList) {
                                Log.i(TAG, "Student Data: " + student.toString());
                            }
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "JSON Parsing error: " + e.getMessage());
                    }
                },
                error -> {
                    Log.e(TAG, "Volley Error: " + error.toString());
                    Toast.makeText(AddEtudiant.this, "Erreur de connexion : " + error.getMessage(), Toast.LENGTH_LONG).show();
                }) {

            @Override
            protected Map<String, String> getParams() {
                String gender = rbMale.isChecked() ? "homme" : "femme";
                Map<String, String> params = new HashMap<>();
                params.put("nom", etLastName.getText().toString());
                params.put("prenom", etFirstName.getText().toString());
                params.put("ville", spCity.getSelectedItem().toString());
                params.put("sexe", gender);
                return params;
            }
        };
        mRequestQueue.add(stringRequest);
    }
}
