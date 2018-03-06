package it.find.com.call.model.models;

import java.io.IOException;
import java.util.ArrayList;

import it.find.com.call.interfaces.students_in_meetings.StudentMeetingImp;
import it.find.com.call.model.network.interfaces.StudentMeetingApi;
import it.find.com.call.model.network.request.StudentMeetingsRequests;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.StudentMeeting;

/**
 * Created by Bruno on 22-Feb-18.
 */

public class StudentMeetingModel implements StudentMeetingImp.ModelImpl {

    @Override
    public void getStudentMeeting(StudentMeetingApi.StudentMeetingResponse handler) {
        try {
            StudentMeetingsRequests.getStudentsMeeting(handler);
        } catch (IOException e) {
            handler.onError(null);
        }
    }

    @Override
    public void getStudentMeetingByMeeting(StudentMeetingApi.StudentMeetingResponse handler, int meeting_id) {
        try {
            StudentMeetingsRequests.getStudentsMeetingByMeeting(handler, meeting_id);
        } catch (IOException e) {
            handler.onError(null);
        }
    }

    @Override
    public void createStudentMeeting(StudentMeetingApi.StudentMeetingResponse handler, ArrayList<StudentMeeting> studentsMeeting) {
        StudentMeetingsRequests.saveStudentsMeeting(handler, studentsMeeting);
    }

    @Override
    public void createStudentsAndMeeting(StudentMeetingApi.StudentMeetingResponse handler, Meeting meeting, ArrayList<StudentMeeting> studentsMeeting) {
        StudentMeetingsRequests.saveStudentsAndMeeting(handler,meeting, studentsMeeting);
    }

    @Override
    public void updateStudentMeeting(StudentMeetingApi.StudentMeetingResponse handler, ArrayList<StudentMeeting> studentsMeeting) {
        StudentMeetingsRequests.updateStudentMeeting(handler, studentsMeeting);
    }
}
