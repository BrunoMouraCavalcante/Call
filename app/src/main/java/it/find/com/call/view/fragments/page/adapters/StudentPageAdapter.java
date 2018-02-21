package it.find.com.call.view.fragments.page.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import it.find.com.call.presenter.presenters.StudentPresenter;
import it.find.com.call.view.fragments.students.ListStudentFragment;
import it.find.com.call.view.fragments.students.RegisterStudentFragment;

/**
 * Created by Bruno on 03-Feb-18.
 */

public class StudentPageAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[] { "Novo", "Lista"};
    private Context context;
    public StudentPresenter presenter;
    public SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public StudentPageAdapter(FragmentManager fm, Context context, StudentPresenter presenter) {
        super(fm);
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = RegisterStudentFragment.newInstance(presenter);
                registeredFragments.put(0,fragment);
                break;
            case 1:
                fragment = ListStudentFragment.newInstance(presenter);
                registeredFragments.put(1,fragment);
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
