package com.adylanroaffa.wuttuwatch.Adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.adylanroaffa.wuttuwatch.Fragments.MoviesFragment;
import com.adylanroaffa.wuttuwatch.Fragments.TVShowsFragment;

/**
 * Created by Adylan Roaffa on 8/10/2017.
 */
/**
 * Created by admin on 26-06-2016.
 */
public class CategoryFragmentAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "Movies", "TV Shows"};

    public CategoryFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        if (position == 0) {
            return new MoviesFragment();
        } else  {
            return new TVShowsFragment();
        }
    }


    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}

