package it.find.com.call.interfaces.students_in_meetings;

import java.util.ArrayList;

import it.find.com.call.model.network.interfaces.StudentMeetingApi;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.StudentMeeting;

/**
 * Created by Bruno on 22-Feb-18.
 */

public interface StudentMeetingImp {

    interface ModelImpl {
        void getStudentMeeting(StudentMeetingApi.StudentMeetingResponse handler);
        void createStudentMeeting(StudentMeetingApi.StudentMeetingResponse handler, ArrayList<StudentMeeting> studentsMeeting);
        void createStudentsAndMeeting(StudentMeetingApi.StudentMeetingResponse handler, Meeting meeting, ArrayList<StudentMeeting> studentsMeeting);
        void updateStudentMeeting(StudentMeetingApi.StudentMeetingResponse handler, ArrayList<StudentMeeting> studentsMeeting);
    }

    interface PresenterImpl {
        void setView (ViewImpl view);
        void setStudentStatus(int position, int status);
        void createStudentMeeting(Meeting meeting, ArrayList<StudentMeeting> studentsMeeting);
        ArrayList<StudentMeeting> getStudenMeetings();
    }

    interface ViewImpl {
        void showProgressBar(boolean show);
        void showToast(String message);
    }
}
