package com.adylanroaffa.wuttuwatch.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.adylanroaffa.wuttuwatch.R;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

//        Add toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.search_screen_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setTitle(R.string.action_search);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
