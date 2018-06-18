package com.example.yukariasaoka.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // choose category spinner in addEntry

    // icons for entry item

    // preferences page---
    // sort by date (most recent, least recent)
    // choose category spinner
    // category view (for each category, different list, same layout)
    // edit categories page (add/remove) later
    // month by month view (later)

    // only dollar values for amount?

    private Button addEntryButton;
    private Button settingsButton;
    private ListView entryListView;
    private ArrayList<Entry> entries;
    private ListAdapter eAdapter;
    private ArrayList<String> categories;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        addEntryButton = findViewById(R.id.addEntryButton);
        addEntryButton.setOnClickListener(this);

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(this);

        entries = new ArrayList<>();
        categories = new ArrayList<>();

        // for demo (see method for details)
        addExampleEntries();

        addDefaultCategories();

        entryListView = (ListView) findViewById(R.id.entriesList);

        eAdapter = new EntryAdapter(this, entries);
        entryListView.setAdapter(eAdapter);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.addEntryButton:

                Intent in = new Intent(getApplicationContext(), AddEntryActivity.class);
                startActivityForResult(in, 111);

                break;

            case R.id.settingsButton:

                // TODO

                break;

            default:

                break;

        }
    }

    // MODIFIES: this
    // EFFECTS: ActivityResult from AddEntryActivity, if confirm entry button is pressed, adds entry to list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 111 && resultCode == RESULT_OK) {

            Entry entry = data.getParcelableExtra("newEntry");
            entries.add(entry);

            entryListView.setAdapter(eAdapter);

        }

        if (requestCode == 222 && resultCode == RESULT_OK) {

            String sort = data.getStringExtra("sort by");

            if (sort == "Date (most recent)") {
                sortByMostRecent();
            }
            if (sort == "Date (least recent)") {
                sortByLeastRecent();
            }
        }
    }

    private void addDefaultCategories(){
        categories.add(" Choose Category");
        categories.add("Food");
        categories.add("Entertainment");
        categories.add("Clothing");
        categories.add("Music");
        categories.add("Books");
        categories.add("Travel");
        categories.add("Transport");
        categories.add("Gifts");
        categories.add("Pets");
        categories.add("Other");
        sortCategories();
    }

    // MODIFIES: this
    // EFFECTS: adds example list of entries for demo
    private void addExampleEntries(){
        entries.add(new Entry("Starbucks", "6.00", "02/06/18", "Food"));
        entries.add(new Entry("Sushi", "10.00", "20/05/18", "Food"));
        entries.add(new Entry("Concert Tickets", "50.00", "07/06/18", "Entertainment"));
        entries.add(new Entry("Movie Tickets", "11.00", "31/05/18", "Entertainment"));
        entries.add(new Entry("New Sandals", "89.00", "13/05/18", "Clothing"));
        entries.add(new Entry("New Shirt", "45.00", "13/05/18", "Clothing"));
        entries.add(new Entry("iTunes Purchase", "9.99", "11/06/18", "Music"));
    }

    private void sortByMostRecent(){
        // TODO
    }

    private void sortByLeastRecent(){
        // TODO
    }

    public static MainActivity getInstance(){
        return instance;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void addCategory(String c){
        categories.add(c);
    }

    public void removeCategory(String c){
        categories.remove(c);
    }

    public void sortCategories(){
        Collections.sort(categories, String.CASE_INSENSITIVE_ORDER);
    }
}
