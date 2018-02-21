package it.find.com.call.view.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import it.find.com.call.view.fragments.pages.AdvertenciaFragment;
import it.find.com.call.view.fragments.pages.ReuniaoFragment;
import it.find.com.call.view.fragments.pages.SedeFragment;

/**
 * Created by Bruno on 20-Jan-18.
 */

public class SampleFragmentPageAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Reunião", "Sede", "Advertência" };
    private Context context;

    public SampleFragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = ReuniaoFragment.newInstance();
                break;
            case 1:
                fragment = SedeFragment.newInstance();
                break;
            case 2:
                fragment = AdvertenciaFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
