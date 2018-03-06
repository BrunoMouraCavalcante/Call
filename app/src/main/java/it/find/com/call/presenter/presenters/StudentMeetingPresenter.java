package it.find.com.call.presenter.presenters;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.interfaces.students_in_meetings.StudentMeetingImp;
import it.find.com.call.model.models.StudentMeetingModel;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.StudentMeeting;
import it.find.com.call.presenter.data.StudentMeetingWithStudent;

/**
 * Created by Bruno on 22-Feb-18.
 */

public class StudentMeetingPresenter implements StudentMeetingImp.PresenterImpl {

    private StudentMeetingModel model;
    private StudentMeetingImp.ViewImpl view;

    @Override
    public void setView(StudentMeetingImp.ViewImpl view, Context context) {

    }

    @Override
    public void setStudentStatus(int position, int status) {

    }

    @Override
    public void createStudentMeeting(Meeting meeting, ArrayList<StudentMeeting> studentsMeeting) {

    }

    @Override
    public void updateStudentMeeting() {

    }

    @Override
    public ArrayList<StudentMeetingWithStudent> getStudenMeetingsList() {
        return null;
    }

    @Override
    public void fillListStudents() {

    }

    @Override
    public ArrayList<StudentMeeting> getFinalStudentMeetingList() {
        return null;
    }

    @Override
    public Context getContext() {
        return null;
    }

}
