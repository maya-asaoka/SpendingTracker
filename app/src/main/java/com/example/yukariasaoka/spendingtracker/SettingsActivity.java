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
    private TextView editCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alpha = findViewById(R.id.alpha);
        mostRecent = findViewById(R.id.mostRecent);
        leastRecent = findViewById(R.id.leastRecent);
        editCategories = findViewById(R.id.editCategories);

        alpha.setOnClickListener(this);
        mostRecent.setOnClickListener(this);
        leastRecent.setOnClickListener(this);
        editCategories.setOnClickListener(this);
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

            case R.id.editCategories: {
                // go to category list: dialog box for add/delete
                finish();
                break;
            }

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
