package com.example.yukariasaoka.spendingtracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView alpha;
    private TextView mostRecent;
    private TextView leastRecent;
    private TextView byCategory;
    private TextView editCategories;
    private TextView byMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alpha = findViewById(R.id.alpha);
        mostRecent = findViewById(R.id.mostRecent);
        leastRecent = findViewById(R.id.leastRecent);
        byCategory = findViewById(R.id.byCategory);
        editCategories = findViewById(R.id.editCategories);
        byMonth = findViewById(R.id.byMonth);

        alpha.setOnClickListener(this);
        mostRecent.setOnClickListener(this);
        leastRecent.setOnClickListener(this);
        byCategory.setOnClickListener(this);
        editCategories.setOnClickListener(this);
        byMonth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.alpha: {
                MainActivity.getInstance().sortAlphabetically();
                finish();
                break;
            }

            case R.id.mostRecent: {
                MainActivity.getInstance().sortByMostRecent();
                finish();
                break;
            }

            case R.id.leastRecent: {
                MainActivity.getInstance().sortByLeastRecent();
                finish();
                break;
            }

            case R.id.byCategory: {
                Intent ic = new Intent(this, CategoryActivity.class);
                startActivityForResult(ic, 123);
                break;
            }

            case R.id.editCategories: {
                // go to category list: dialog box for add/delete
                finish();
                break;
            }

            case R.id.byMonth: {
                Intent im = new Intent(this, MonthActivity.class);
                startActivityForResult(im, 456);
                break;
            }

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 123) {
            String cat = data.getStringExtra("Category");
            MainActivity.getInstance().viewByCategory(cat);
            finish();
        }
        if (resultCode == RESULT_OK && requestCode == 456) {
            String month = data.getStringExtra("Month");
            MainActivity.getInstance().sortByMonth(month);
            finish();
        }
    }
}
