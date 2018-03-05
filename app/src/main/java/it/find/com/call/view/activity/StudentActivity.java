package it.find.com.call.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import it.find.com.call.R;
import it.find.com.call.interfaces.student.StudentImpl;
import it.find.com.call.presenter.presenters.StudentPresenter;
import it.find.com.call.view.fragments.page.adapters.StudentPageAdapter;

public class StudentActivity extends AppCompatActivity implements StudentImpl.BaseViewImpl {

    public ViewPager mVpViewPager;
    public StudentPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        presenter = new StudentPresenter(this.getApplicationContext());
        presenter.setView(this);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mVpViewPager = (ViewPager) findViewById(R.id.vp_control);
        mVpViewPager.setAdapter(new StudentPageAdapter(getSupportFragmentManager(),StudentActivity.this, presenter));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mVpViewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                if (tab.getText().equals("Lista")) {
//                    android.support.v4.app.Fragment fragment = ((StudentPageAdapter)mVpViewPager.getAdapter()).getRegisteredFragment(1);
//                    if (fragment != null) {
//                        //((ListStudentFragment) fragment).createListStudents();
//                    }
//                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            mVpViewPager.setVisibility(View.GONE);
        } else {
            mVpViewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showToast(String message) {

    }
}
