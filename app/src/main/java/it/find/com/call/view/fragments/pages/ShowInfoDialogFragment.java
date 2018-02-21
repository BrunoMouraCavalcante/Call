package it.find.com.call.view.fragments.pages;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Bruno on 20-Jan-18.
 */

public class ShowInfoDialogFragment extends Fragment {

    public ShowInfoDialogFragment() {

    }

    public static ShowInfoDialogFragment newInstance() {
        ShowInfoDialogFragment fragment = new ShowInfoDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}
