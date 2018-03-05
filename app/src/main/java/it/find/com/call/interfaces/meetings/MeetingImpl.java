package it.find.com.call.interfaces.meetings;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.model.network.interfaces.MeetingsApi;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.Student;

/**
 * Created by Bruno on 13-Feb-18.
 */

public interface MeetingImpl {

    interface ModelImpl {
        void getMeetings(MeetingsApi.MeetingsResponse handler);
        void saveMeeting(MeetingsApi.MeetingsResponse handler, Meeting meeting);
        void updateMeeting(MeetingsApi.MeetingsResponse handler, Meeting meeting);
        void deleteMeeting(MeetingsApi.MeetingsResponse handler, int id);
        void getMeetingsByType(MeetingsApi.MeetingsResponse handler, int type);
    }

    interface ViewImpl {
        void showProgressBar(boolean show);
        void showToast(String message);
        boolean validateFields();
        void cleanFields();
        void createListStudents();
        void createEmptyList();
    }

    interface PresenterImpl {
        Context getContext();
        void showProgressBar(boolean show);
        void showToast(String message);
        void setView(ViewImpl view);
        ArrayList<Meeting> getMeetings();
        void createMeeting(Meeting meeting);
        void setMeetingType(String type);
        String getMeetType();
        void fillListStudents();
        ArrayList<Student> getStudents();
    }
}
