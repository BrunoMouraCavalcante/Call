package it.find.com.call.view.fragments.page.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import it.find.com.call.interfaces.students_in_meetings.ControlImpl;
import it.find.com.call.view.fragments.pages.AdvertenciaFragment;
import it.find.com.call.view.fragments.pages.ReuniaoFragment;
import it.find.com.call.view.fragments.pages.SedeFragment;

/**
 * Created by Bruno on 20-Jan-18.
 */

public class ControlPageAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Reunião", "Sede", "Advertência" };
    private Context context;
    private ControlImpl.presenterImpl presenter;

    public ControlPageAdapter(FragmentManager fm, Context context, ControlImpl.presenterImpl presenter) {
        super(fm);
        this.context = context;
        this.presenter = presenter;
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
                fragment = ReuniaoFragment.newInstance(presenter);
                break;
            case 1:
                fragment = SedeFragment.newInstance(presenter);
                break;
            case 2:
                fragment = AdvertenciaFragment.newInstance(presenter);
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
