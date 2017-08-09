package com.adylanroaffa.wuttuwatch.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

//      add search edit text functionality
        EditText search = (EditText) findViewById(R.id.search_edit_text);
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){

                    //TODO : DO SEARCHING (try the search endpoint)
                    Toast.makeText(SearchActivity.this,"DO SEARCHING",Toast.LENGTH_SHORT)
                    .show();

                    //TODO: HIDE SOFT KEYBOARD FROM THE SCREEN
                    InputMethodManager imm = (InputMethodManager) SearchActivity.this.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                }
                return true;
            }
        });


    }
}
