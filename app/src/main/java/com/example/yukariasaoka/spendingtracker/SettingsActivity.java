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
    private TextView addCategory;
    private TextView removeCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alpha = findViewById(R.id.alpha);
        mostRecent = findViewById(R.id.mostRecent);
        leastRecent = findViewById(R.id.leastRecent);
        addCategory = findViewById(R.id.addCategory);
        removeCategory = findViewById(R.id.removeCategory);

        alpha.setOnClickListener(this);
        mostRecent.setOnClickListener(this);
        leastRecent.setOnClickListener(this);
        addCategory.setOnClickListener(this);
        removeCategory.setOnClickListener(this);
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

            case R.id.addCategory: {
                Intent i0 = new Intent(this, AddCategoryActivity.class);
                startActivityForResult(i0, 0);
                break;
            }

            case R.id.removeCategory: {
                Intent i1 = new Intent(this, CategoryActivity.class);
                startActivityForResult(i1, 1);
                break;
            }

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == 0) {
            MainActivity.getInstance().addCategory(data.getStringExtra("new category"));
            finish();
        }

        if (resultCode == RESULT_OK && requestCode == 1) {
            MainActivity.getInstance().removeCategory(data.getStringExtra("Category"));
            finish();
        }
    }
}
