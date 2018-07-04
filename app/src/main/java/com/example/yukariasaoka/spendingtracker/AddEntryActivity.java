package com.example.yukariasaoka.spendingtracker;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.Calendar;

public class AddEntryActivity extends AppCompatActivity implements View.OnClickListener {

    private Button confirmButton;
    private TextView date;
    private TextView category;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private String categoryString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_entry);

        confirmButton = findViewById(R.id.confirmEntryButton);
        confirmButton.setOnClickListener(this);
        category = findViewById(R.id.chooseCategory);
        category.setOnClickListener(this);

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
    // if category is null, default to "Other"
    @Override
    public void onClick(View v) {

        EditText desc = findViewById(R.id.descriptionField);
        String descString = desc.getText().toString();

        EditText amount = findViewById(R.id.amountField);
        String amountString = amount.getText().toString();

        if (categoryString == null) { categoryString = "Other"; }

        switch (v.getId()) {

            case R.id.chooseCategory:

                Intent catAct = new Intent(this, CategoryActivity.class);
                startActivityForResult(catAct, 222);
                break;

            case R.id.confirmEntryButton:

                Entry entry = new Entry(descString, amountString, date.getText().toString(), categoryString);

                Intent main = new Intent(this, MainActivity.class);
                main.putExtra("newEntry", entry);
                setResult(RESULT_OK, main);
                finish();
                break;

            default:

                break;

        }

    }

    // adds entry to list in main when confirmEntryButton is pressed, changes textview to chosen category
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 222 && resultCode == RESULT_OK) {
            String cat = data.getStringExtra("Category");
            category.setText(cat);
            categoryString = cat;
        }
    }

    // EFFECTS: does not add entry to list if back button is pressed
    @Override
    public void onBackPressed() {
        Intent returnToMain = new Intent();
        setResult(RESULT_CANCELED, returnToMain);
        finish();
    }

}
