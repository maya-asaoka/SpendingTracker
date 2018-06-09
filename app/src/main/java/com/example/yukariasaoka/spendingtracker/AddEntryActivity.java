package com.example.yukariasaoka.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEntryActivity extends AppCompatActivity implements View.OnClickListener {

    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry);

        confirmButton = findViewById(R.id.confirmEntryButton);
        confirmButton.setOnClickListener(this);
    }

    // EFFECTS: if confirmEntryButton pressed, creates new Entry object from EditTexts
    //          and sends it to MainActivity to display
    @Override
    public void onClick(View v) {

        EditText desc = findViewById(R.id.descriptionField);
        String descString = desc.getText().toString();

        EditText amount = findViewById(R.id.amountField);
        String amountString = amount.getText().toString();

        EditText date = findViewById(R.id.dateField);
        String dateString = date.getText().toString();

        EditText category = findViewById(R.id.categoryField);
        String categoryString = category.getText().toString();

        switch (v.getId()) {

            case R.id.confirmEntryButton:

                Entry entry = new Entry(descString, amountString, dateString, categoryString);
                Intent in = new Intent(getApplicationContext(), MainActivity.class);
                in.putExtra("newEntry", entry);

                setResult(RESULT_OK, in);
                finish();

            default:

                break;

        }

    }

    // EFFECTS: does not add entry to list if back button is pressed
    @Override
    public void onBackPressed() {

        Intent i = new Intent();
        setResult(RESULT_CANCELED, i);
        finish();

    }


}
