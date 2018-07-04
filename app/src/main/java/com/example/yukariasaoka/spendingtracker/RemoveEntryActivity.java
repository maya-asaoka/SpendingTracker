package com.example.yukariasaoka.spendingtracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class RemoveEntryActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new EntryAdapter(this, MainActivity.getInstance().getEntries()));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = getIntent();
        Entry entry = (Entry) getListAdapter().getItem(position);
        i.putExtra("Entry", entry);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent in = getIntent();
        setResult(RESULT_CANCELED, in);
        finish();
    }
}
