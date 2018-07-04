package com.example.yukariasaoka.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // comment code better

    // edit entries

    private Button addEntryButton;
    private Button settingsButton;
    private TextView totalView;
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

        totalView = findViewById(R.id.totalView);
        addEntryButton = findViewById(R.id.addEntryButton);
        settingsButton = findViewById(R.id.settingsButton);

        addEntryButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

        entries = new ArrayList<>();
        categories = new ArrayList<>();
        addDefaultCategories();

        // for demo (see method for details)
        addExampleEntries();
        totalView.setText("Total: $" + calculateTotal());

        entryListView = (ListView) findViewById(R.id.entriesList);

        eAdapter = new EntryAdapter(this, entries);
        entryListView.setAdapter(eAdapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.addEntryButton: {
                Intent in = new Intent(this, AddEntryActivity.class);
                startActivityForResult(in, 111);
                break;
            }

            case R.id.settingsButton:
                Intent in2 = new Intent(this, SettingsActivity.class);
                startActivity(in2);
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
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public double calculateTotal() {
        double total = 0;
        for (Entry e : entries) {
            total = total + Double.parseDouble(e.getAmount());
        }
        return total;
    }

    // manage categories
    public ArrayList<String> getCategories() {
        return categories;
    }

    public void addCategory(String c) {
        categories.add(c);
    }

    public void removeCategory(String c) {
        categories.remove(c);
    }

    public void sortCategories() {
        Collections.sort(categories, String.CASE_INSENSITIVE_ORDER);
    }



    // sorting methods for entries

    public void sortAlphabetically(){
        Collections.sort(entries);
        entryListView.setAdapter(eAdapter);
    }

    public void sortByMostRecent() {
        Collections.sort(entries, new SortByMostRecent());
        Collections.reverse(entries);
        entryListView.setAdapter(eAdapter);
    }

    public void sortByLeastRecent() {
        Collections.sort(entries, new SortByMostRecent());
        entryListView.setAdapter(eAdapter);
    }


    private void addDefaultCategories() {
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
    private void addExampleEntries() {
        entries.add(new Entry("Music", "9.99", "11/06/18", "Music"));
        entries.add(new Entry("Concert Tickets", "50.00", "07/06/18", "Entertainment"));
        entries.add(new Entry("Starbucks", "6.00", "02/06/18", "Food"));
        entries.add(new Entry("Movie Tickets", "11.00", "31/05/18", "Entertainment"));
        entries.add(new Entry("Sushi", "10.00", "20/05/18", "Food"));
        entries.add(new Entry("New Sandals", "89.00", "13/05/18", "Clothing"));
        entries.add(new Entry("New Shirt", "45.00", "13/05/18", "Clothing"));
    }
}
