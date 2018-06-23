package com.example.yukariasaoka.spendingtracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoryActivity extends ListActivity {

    private ArrayList<String> cat = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cat = MainActivity.getInstance().getCategories();
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, cat));
    }

    // EFFECTS: sends chosen category back to previous activity
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = getIntent();
        String category = (String) getListAdapter().getItem(position);
        i.putExtra("Category", category);
        setResult(RESULT_OK, i);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        setResult(RESULT_CANCELED, i);
        finish();
    }
}
