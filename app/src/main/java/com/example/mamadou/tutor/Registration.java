package com.example.mamadou.tutor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import utilisateur.DummyData;
import utilisateur.Etudiant;
import utilisateur.Tuteur;
import utilisateur.Utilisateur;

public class Registration extends AppCompatActivity {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private EditText editFullname;
    private EditText editEmail;
    private int userType;
    private final String utilisateurType = "UserType";
    public static List<Utilisateur> utilisateurs;
    private static int numberOfUser;
    private final int STUDENT = 1;
    private final int TUTOR = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextUserName = (EditText) findViewById(R.id.userName);
        editTextPassword = (EditText) findViewById(R.id.password);
        editFullname = (EditText) findViewById(R.id.fullName);
        editEmail = (EditText) findViewById(R.id.email);
        Intent intent = getIntent();
        userType = intent.getIntExtra(utilisateurType, 0);
        numberOfUser = 0;
        utilisateurs = new LinkedList<Utilisateur>();
    }

    public void signUpWithEmail(View view) {
        String userName = editTextUserName.getText().toString();
        String password = editTextPassword.getText().toString();
        //String confirmPassword = editTextPassword.getText();
        String fullName = editFullname.getText().toString();
        String email = editEmail.getText().toString();
        //
        String[] temp = splitFullName(fullName);
        String prenom = temp[0];
        String nom = temp[1];
        if (userType == STUDENT) {
            createStudentUser(prenom, nom, password, fullName, email);
        }
        else if (userType == TUTOR) {
            createTutorUser(prenom, nom, password, fullName, email);
        }
    }
    public void studentRegistration() {
        Intent intent = new Intent(this, StudentMainPage.class);
        startActivity(intent);
    }
    public void tutorRegistration() {
        Intent intent = new Intent(this, TutorMainPage.class);
        startActivity(intent);
    }
    private String[] splitFullName( String fullName) {
        String[] temp;
        String delimeter = " ";
        temp = fullName.split(delimeter);
        return temp;
    }
    private void createStudentUser(String prenom, String nom, String userName,
                                   String password, String email) {
        Utilisateur utilisateur = new Etudiant(prenom, nom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(password);
        utilisateur.setUserName(userName);
        utilisateur.setIdUtilisateur(DummyData.DUMMY_CREDENTIALS_STUDENT.size());
        // adding user to the database
        DummyData.DUMMY_CREDENTIALS_STUDENT.add((Etudiant)utilisateur);
        studentRegistration();
    }
    private void createTutorUser(String prenom, String nom, String userName,
                                 String password, String email) {
        Utilisateur utilisateur = new Tuteur(prenom, nom);
        utilisateur.setEmail(email);
        utilisateur.setPassword(password);
        utilisateur.setUserName(userName);
        utilisateur.setIdUtilisateur(DummyData.DUMMY_CREDENTIALS.size());
        // adding user to the database
        DummyData.DUMMY_CREDENTIALS.add((Tuteur)utilisateur);
        tutorRegistration();
    }
}
