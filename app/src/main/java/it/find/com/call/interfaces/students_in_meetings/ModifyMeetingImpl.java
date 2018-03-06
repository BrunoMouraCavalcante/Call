package it.find.com.call.interfaces.students_in_meetings;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.presenter.data.StudentMeeting;

/**
 * Created by Bruno on 05-Mar-18.
 */

public interface ModifyMeetingImpl {

    interface PresenterImpl {
        Context getContext();
        void showProgressBar(boolean show);
        void showToast(String message);
        void setBaseView(ViewImpl view);
        void fillListStudents();
        ArrayList<StudentMeeting> getListStudenMeeting();
        void setStudentStatus(int position, int status);
    }
    interface ViewImpl {
        void showProgressBar(boolean show);
        void showToast(String message);
        void showStudentMeetingList();
    }
}
