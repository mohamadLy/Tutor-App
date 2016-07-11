package com.example.mamadou.tutor;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import utilisateur.DummyData;
import utilisateur.Tuteur;

public class TutorMainPage extends AppCompatActivity {

    private static final int STUDENT = 1;
    private static final int TUTOR = 2;
    Intent intent;
    private int idUser;
    private int userType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_main_page2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().collapseActionView();

        intent = getIntent();
        idUser = intent.getIntExtra("userId", 0);

        //welcome user
        welcome();
    }

    private void welcome() {
        userType = intent.getIntExtra("userType", 0);
        if (userType == STUDENT) {
            String firstName = DummyData.DUMMY_CREDENTIALS_STUDENT.get(idUser - 1).getPrenom();
            String lastName = DummyData.DUMMY_CREDENTIALS_STUDENT.get(idUser - 1).getNom();
            Toast.makeText(TutorMainPage.this, "Bonjour " + firstName + " " + lastName,
                    Toast.LENGTH_SHORT);
        }
        else if(userType == TUTOR){
            String firstName = DummyData.DUMMY_CREDENTIALS.get(idUser - 1).getPrenom();
            String lastName = DummyData.DUMMY_CREDENTIALS.get(idUser - 1).getNom();
            Toast.makeText(TutorMainPage.this, "Bonjour " + firstName + " " + lastName
                    , Toast.LENGTH_SHORT);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_student_main_page, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem); // <-- change your code to this
        // Configure the search info and add any event listeners
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_menu:
                Toast.makeText(TutorMainPage.this, "option", Toast.LENGTH_SHORT);
                break;
        }
        return true; //super.onOptionsItemSelected(item);
    }


}
