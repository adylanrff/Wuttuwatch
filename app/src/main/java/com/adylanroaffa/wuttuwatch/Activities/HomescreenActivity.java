package com.adylanroaffa.wuttuwatch.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.adylanroaffa.wuttuwatch.Adapters.CategoryFragmentAdapter;
import com.adylanroaffa.wuttuwatch.R;

//ODY JANGAN NGODING MULU PLIS, ISTIRAHAT LAH, JANGAN NGODING TERUS, GUE AJA CAPE LIAT LO NGODING

public class HomescreenActivity extends AppCompatActivity {


    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        //Add Toolbar
       toolbar = (Toolbar) findViewById(R.id.homescreen_toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setOverflowIcon(getDrawable(R.drawable.ic_more));
        setSupportActionBar(toolbar);

        //TODO: add TABS
        //find the view pager to swipe
        ViewPager viewPager = (ViewPager) findViewById(R.id.homescreen_viewpager);

        //Create adapter
        CategoryFragmentAdapter adapter = new CategoryFragmentAdapter(getSupportFragmentManager());

        //set the adapter to view pager
        viewPager.setAdapter(adapter);

        //set the Tab layout
        TabLayout tabLayout = (TabLayout) findViewById(R.id.homescreen_tab);
        tabLayout.setupWithViewPager(viewPager);


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
