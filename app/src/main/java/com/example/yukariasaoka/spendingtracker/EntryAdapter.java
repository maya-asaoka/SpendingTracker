package com.example.yukariasaoka.spendingtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EntryAdapter extends ArrayAdapter<Entry> {

    public EntryAdapter(Context context, ArrayList<Entry> entries) {

        super(context, R.layout.entry_item, entries);

    }

    // constructs a single entry item (showing it how to display it)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // this basically prepares for rendering so we can get the references later
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View entryView = inflater.inflate(R.layout.entry_item, parent, false);

        // get the entry in the current position in list
        Entry oneEntry = getItem(position);

        // get a reference to every text field in the layout
        TextView description = entryView.findViewById(R.id.showDescription);
        TextView amount = entryView.findViewById(R.id.showAmount);
        TextView date = entryView.findViewById(R.id.showDate);
        TextView category = entryView.findViewById(R.id.showCategory);

        // set the text of each textview to the fields from entry item
        description.setText(oneEntry.getDescription());
        amount.setText(oneEntry.getAmount());
        date.setText(oneEntry.getDate());
        category.setText(oneEntry.getCategory());

        return entryView;

    }
}
