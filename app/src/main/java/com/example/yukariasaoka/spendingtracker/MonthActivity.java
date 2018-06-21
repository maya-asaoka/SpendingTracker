package com.example.yukariasaoka.spendingtracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class MonthActivity extends ListActivity {

    private ArrayList<String> monthsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        addMonths();
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, monthsList));
    }

    private void addMonths() {
        String[] months = new DateFormatSymbols().getMonths();
        for (int i = 0; i < months.length; i++) {
            String month = months[i];
            System.out.println("month = " + month);
            monthsList.add(months[i]);
        }
    }

    // EFFECTS: sends chosen month back to settings activity
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = getIntent();
        String month = (String) getListAdapter().getItem(position);
        i.putExtra("Month", month);
        setResult(RESULT_OK, i);
        finish();
    }
}
