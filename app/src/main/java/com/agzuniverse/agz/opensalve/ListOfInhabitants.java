package com.agzuniverse.agz.opensalve;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.agzuniverse.agz.opensalve.Modals.Person;
import com.agzuniverse.agz.opensalve.adapters.ListOfInhabsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListOfInhabitants extends AppCompatActivity {

    private String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_inhabitants);

        //Set EditText field for searching in Action Bar
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.action_bar_edit_text);
        actionBar.setDisplayShowCustomEnabled(true);

        EditText search = actionBar.getCustomView().findViewById(R.id.appbar_search_field);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                query = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        //TODO replace this dummy data with data fetched from API
        List<Person> persons = new ArrayList<>();
        String[] fnames = {"Tony", "Steve", "Peter", "Natasha", "Peter", "Bruce", "Dead", "Steven"};
        String[] snames = {"Stark", "Rogers", "Quill", "Romanoff", "Parker", "Banner", "Pool", "Strange"};
        Integer[] ages = {50, 37, 42, 30, 21, 54, 45, 43};
        for (int i = 0; i < fnames.length && i < snames.length && i < ages.length; i++) {
            Person current = new Person();
            current.firstName = fnames[i];
            current.secondName = snames[i];
            current.age = ages[i];
            persons.add(current);
        }

        RecyclerView inhabs = findViewById(R.id.listOfInhabitants);
        RecyclerView.LayoutManager inhabManager = new LinearLayoutManager(this);
        RecyclerView.Adapter inhabAdapter = new ListOfInhabsAdapter(this, persons);
        inhabs.setLayoutManager(inhabManager);
        inhabs.setAdapter(inhabAdapter);
    }
}
