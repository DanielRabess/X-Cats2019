package com.example.curiosity2019;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numOfTabs = numberOfTabs;

    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                ScoutTab1 tab1 = new ScoutTab1();
                return tab1;
            case 1:
                ScoutTab2 tab2 = new ScoutTab2();
                return tab2;
            case 2:
                ScoutTab3 tab3 = new ScoutTab3();
                return tab3;
            case 3:
                ScoutTab4 tab4 = new ScoutTab4();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
