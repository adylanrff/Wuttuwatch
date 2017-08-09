package com.adylanroaffa.wuttuwatch.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.adylanroaffa.wuttuwatch.R;

public class HomescreenActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        //Add Toolbar
        toolbar = (Toolbar) findViewById(R.id.homescreen_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        //TODO: add fragment manager
        /*Fragment movieFragment = new MoviesFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container, movieFragment).commit();
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homescreen_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_search: {
                    Intent searchIntent = new Intent(HomescreenActivity.this,SearchActivity.class);
                    startActivityForResult(searchIntent,0);
            }

        }

        return true;
    }
}
