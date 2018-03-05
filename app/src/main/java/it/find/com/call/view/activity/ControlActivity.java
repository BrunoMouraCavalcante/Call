package it.find.com.call.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import it.find.com.call.R;
import it.find.com.call.interfaces.students_in_meetings.ControlImpl;
import it.find.com.call.presenter.presenters.ControlPresenter;
import it.find.com.call.view.fragments.page.adapters.ControlPageAdapter;

public class ControlActivity extends AppCompatActivity implements ControlImpl.BaseViewImpl{

    private ControlImpl.presenterImpl presenter;
    private ViewPager mViewPager;
    private ProgressBar mPbProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        setTitle("Controle de Presença");

        presenter = new ControlPresenter(this);
        presenter.setBaseView(this);
        // Get the ViewPager and set it's PagerAdapter so that it can display items
        mPbProgressBar = findViewById(R.id.pb_control);
        mViewPager = (ViewPager) findViewById(R.id.vp_control);
        mViewPager.setAdapter(new ControlPageAdapter(getSupportFragmentManager(),
                ControlActivity.this,presenter));

        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            mPbProgressBar.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.GONE);
        } else {
            mPbProgressBar.setVisibility(View.GONE);
            mViewPager.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
