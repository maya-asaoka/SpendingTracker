package com.example.yukariasaoka.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText categoryName;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        categoryName = findViewById(R.id.addCategoryEt);
        addButton = findViewById(R.id.addCategoryButton);

        addButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case R.id.addCategoryButton: {
                Intent in = getIntent();
                String cat = categoryName.getText().toString();
                in.putExtra("new category", cat);
                setResult(RESULT_OK, in);
                finish();
                break;
            }

            default:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent in2 = getIntent();
        setResult(RESULT_CANCELED, in2);
        finish();
    }
}
