package com.example.yukariasaoka.spendingtracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class AddEntryActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private Button confirmButton;
    private TextView date;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private Spinner categorySpinner;
    private ArrayAdapter<String> adapter;
    private TextView spinnerText;

    // TODO: add spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry);

        confirmButton = findViewById(R.id.confirmEntryButton);
        confirmButton.setOnClickListener(this);

        categorySpinner = findViewById(R.id.categorySpinner);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, MainActivity.getInstance().getCategories());
        categorySpinner.setAdapter(adapter);
        categorySpinner.setOnItemSelectedListener(this);

        // setting onClickListener for choose date textview
        date = findViewById(R.id.chooseDate);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // defaults to current date
                Calendar cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                // setting the datepickerdialog
                DatePickerDialog dialog = new DatePickerDialog(
                        AddEntryActivity.this,
                        android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth,
                        onDateSetListener,
                        year, month, day);
                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            // sets choose date text to chosen date in dd/mm/yy format
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String twoYear = Integer.toString(year).substring(2);
                String chosenDate = dayOfMonth + "/" + month + "/" + twoYear;
                date.setText(chosenDate);
            }
        };

    }

    // EFFECTS: if confirmEntryButton pressed, creates new Entry object from EditTexts
    //          and sends it to MainActivity to display
    @Override
    public void onClick(View v) {

        EditText desc = findViewById(R.id.descriptionField);
        String descString = desc.getText().toString();

        EditText amount = findViewById(R.id.amountField);
        String amountString = amount.getText().toString();

        String categoryString = spinnerText.getText().toString();
        if (categoryString == " Choose Category"){
            categoryString = "Other";
        }

        switch (v.getId()) {

            case R.id.confirmEntryButton:

                Entry entry = new Entry(descString, amountString, date.getText().toString(), categoryString);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerText = (TextView) view;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        spinnerText = null;
    }
}
