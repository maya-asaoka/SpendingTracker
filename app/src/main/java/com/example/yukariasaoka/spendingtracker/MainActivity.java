package com.example.yukariasaoka.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // TODO
    // sort by date (most recent, least recent)
    // category view
    // calendar add (using the date picker)
    // icons for entry item
    // enumerate categories?

    Button addEntryButton;
    ListView entryListView;
    ArrayList<Entry> entries;
    ListAdapter eAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addEntryButton = findViewById(R.id.addEntryButton);
        addEntryButton.setOnClickListener(this);

        entries = new ArrayList<>();

        // for demo (see method for details)
        addExampleEntries();

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

    // MODIFIES: this
    // EFFECTS: adds example list of entries for demo
    private void addExampleEntries(){
        entries.add(new Entry("Starbucks", "6.00", "02/06/18", "Food"));
        entries.add(new Entry("Sushi", "10.00", "20/05/18", "Food"));
        entries.add(new Entry("Concert Tickets", "50.00", "07/06/18", "Entertainment"));
        entries.add(new Entry("Movie Tickets", "11.00", "31/05/18", "Entertainment"));
        entries.add(new Entry("Nail Salon", "35.00", "09/06/18", "Beauty"));
        entries.add(new Entry("New Sandals", "89.00", "13/05/18", "Clothing"));
        entries.add(new Entry("New Shirt", "45.00", "13/05/18", "Clothing"));
    }
}
