package com.example.mamadou.tutor;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.LinkedList;
import java.util.List;

import utilisateur.DummyData;
import utilisateur.Tuteur;


public class StudentMainPage extends AppCompatActivity {

    private static final int STUDENT = 1;
    private static final int TUTOR = 2;
    Intent intent;
    private int idUser;
    private int userType;

    private List<Tuteur> tuteurs = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student__main__page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().collapseActionView();
        populateTutor();
        populateListView();
        intent = getIntent();
        idUser = intent.getIntExtra("idUser", 0);

        //welcome user
        welcome();
    }

    private void welcome() {
        userType = intent.getIntExtra("userType", 0);
        if (userType == STUDENT) {
            String firstName = DummyData.DUMMY_CREDENTIALS_STUDENT.get(idUser + 1).getPrenom();
            String lastName = DummyData.DUMMY_CREDENTIALS_STUDENT.get(idUser + 1).getNom();
            Toast.makeText(StudentMainPage.this, "Bonjour " + firstName + " " + lastName,
                    Toast.LENGTH_SHORT);
        }
        else if(userType == TUTOR){
            String firstName = DummyData.DUMMY_CREDENTIALS.get(idUser + 1).getPrenom();
            String lastName = DummyData.DUMMY_CREDENTIALS.get(idUser + 1).getNom();
            Toast.makeText(StudentMainPage.this, "Bonjour " + firstName + " " + lastName
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
                Toast.makeText(StudentMainPage.this, "option", Toast.LENGTH_SHORT);
                break;
        }
        return true; //super.onOptionsItemSelected(item);
    }
    private void populateTutor() {
        for (int i = 0; i < 5; i++) {
            tuteurs.add(new Tuteur("Mamadou", "Ly"));
        }
    }
    private void populateListView() {
        ArrayAdapter<Tuteur> adapter = new MyAdapter();
        ListView listView = (ListView) findViewById(R.id.listViewBestTutor);
        listView.setAdapter(adapter);
    }

    private class MyAdapter extends ArrayAdapter<Tuteur> {

        public MyAdapter() {
            super(StudentMainPage.this, R.layout.tutor_item_view,  tuteurs);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tutor_item_view, parent, false);
            }
            // finding the current tutor
            Tuteur currentTutor =  tuteurs.get(position);
            // find the icone
            ImageView profile = (ImageView)convertView.findViewById(R.id.profilePicture);
            //profile.setImageResource(currentTutor.getIdUtilisateur());
            return convertView;
            //return super.getView(position, convertView, parent);
        }
    }
}
