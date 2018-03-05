package it.find.com.call.view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import it.find.com.call.R;

public class MenuActivity extends AppCompatActivity {

    private final int REQUEST_CODE_SUCCESS = 100;

    private CardView mCvMenu1;
    private CardView mCvMenu2;
    private CardView mCvMenu3;
    private CardView mCvMenu4;
    private CollapsingToolbarLayout mCollapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mCvMenu1 = findViewById(R.id.cv_item_menu1);
        mCvMenu2 = findViewById(R.id.cv_item_menu2);
        mCvMenu3 = findViewById(R.id.cv_item_menu3);
        mCvMenu4 = findViewById(R.id.cv_item_menu4);
        mCollapsingToolbar = findViewById(R.id.appbar_collapsing);
        mCollapsingToolbar.setExpandedTitleTextAppearance(R.style.CollapsedAppBar);

        mCvMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { goToPage(StudentActivity.class, null); }
        });

        mCvMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("spin", 0);
                goToPage(PresenceActivity.class, bundle);
            }
        });

        mCvMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("spin", 1);
                goToPage(PresenceActivity.class, bundle);
            }
        });

        mCvMenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPage(ControlActivity.class, null);
            }
        });

        verifyPermissions();
    }


    private void goToPage(final Class destiny, Bundle params) {
        Intent intent = new Intent(this, destiny);
        if (params != null) {
            intent.putExtras(params);
        }
        startActivity(intent);
    }

    public void verifyPermissions() {
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.INTERNET)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.INTERNET},
                        REQUEST_CODE_SUCCESS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_SUCCESS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
