package it.find.com.call.interfaces.students_in_meetings;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.presenter.data.Reuniao;

/**
 * Created by Bruno on 26-Feb-18.
 */

public interface ControlImpl {

    interface presenterImpl {
        Context getContext();
        void showProgressBar(boolean show);
        void showToast(String message);
        void setBaseView(BaseViewImpl view);
        void setReuniaoView(ReuniaoViewImpl view);
        void setSedeView(SedeViewImpl view);
        void setControlView(ControlViewImpl view);
        void fillListReuniao();
        void fillListSede();
        void fillListStudents();
        ArrayList<Reuniao> getListReuniao();
        void getListSede();
        void getListControl();
    }

    interface BaseViewImpl {
        void showProgressBar(boolean show);
        void showToast(String message);
    }

    interface ReuniaoViewImpl extends BaseViewImpl {
        void showReuniaoList();
    }

    interface SedeViewImpl extends BaseViewImpl { }

    interface ControlViewImpl extends BaseViewImpl { }
}
