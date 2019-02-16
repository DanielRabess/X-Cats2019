package com.example.curiosity2019;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;
    FragmentManager myFragmentManager;

    public ScoutTab1 myTab1Fragment;
    public ScoutTab2 myTab2Fragment;

    String frag1Tag;
    String frag2Tag;

    public PagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        myFragmentManager = fm;
        this.numOfTabs = numberOfTabs;

    }


    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0:
                ScoutTab1 tab1 = new ScoutTab1();
                //FragmentTransaction transaction = myFragmentManager.beginTransaction();
                //transaction.replace(R.id.fragtab1, tab1);
                //transaction.add(tab1,"ScoutTab_1");
                //transaction.commit();
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
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment createdFragment = (Fragment) super.instantiateItem(container, position);
        // save the appropriate reference depending on position
        switch (position) {
            case 0:
                myTab1Fragment = (ScoutTab1) createdFragment;
                break;
            case 1:
                myTab2Fragment = (ScoutTab2) createdFragment;
                break;
        }
        return createdFragment;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

}
