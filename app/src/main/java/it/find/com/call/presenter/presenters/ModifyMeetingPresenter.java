package it.find.com.call.presenter.presenters;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.R;
import it.find.com.call.interfaces.students_in_meetings.StudentMeetingImp;
import it.find.com.call.model.models.StudentMeetingModel;
import it.find.com.call.model.network.interfaces.StudentMeetingApi;
import it.find.com.call.model.network.response.models.Response;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.StudentMeeting;
import it.find.com.call.presenter.data.StudentMeetingWithStudent;

/**
 * Created by Bruno on 05-Mar-18.
 */

public class ModifyMeetingPresenter implements StudentMeetingImp.PresenterImpl{

    private ArrayList<StudentMeetingWithStudent> listStudents;
    private ArrayList<StudentMeeting> listUpdate;
    private StudentMeetingImp.ViewImpl view;
    private StudentMeetingModel model;
    private int meeting_id;
    private int meeting_type;
    private String type;
    private Context context;

    public ModifyMeetingPresenter(int meeting_id, int meeting_type) {
        this.model = new StudentMeetingModel();
        this.meeting_id = meeting_id;
        this.meeting_type = meeting_type;
    }

    @Override
    public void setView(StudentMeetingImp.ViewImpl view, Context context) {
        this.view = view;
        this.context = context;
        this.type = (this.meeting_type == 1) ? context.getString(R.string.reuniao) : context.getString(R.string.sede);
    }

    @Override
    public void setStudentStatus(int position, int status) {
        listStudents.get(position).setStatus(status);
    }

    @Override
    public void createStudentMeeting(Meeting meeting, ArrayList<StudentMeeting> studentsMeeting) { }

    @Override
    public void updateStudentMeeting() {
        model.updateStudentMeeting(new StudentMeetingApi.StudentMeetingResponse() {
            @Override
            public void onSuccess(Response response) {
                view.showProgressBar(false);
                view.showToast(getType()+" atualizada com sucesso!");
            }

            @Override
            public void onError(Response response) {
                view.showProgressBar(false);
                view.showToast("Não foi possível atualizar a "+getType()+" no momento, tente mais tarde!");
            }
        },getFinalStudentMeetingList());
    }

    @Override
    public ArrayList<StudentMeetingWithStudent> getStudenMeetingsList() {
        return listStudents;
    }

    @Override
    public void fillListStudents() {
        view.showProgressBar(true);
        model.getStudentMeetingByMeeting(new StudentMeetingApi.StudentMeetingResponse() {
            @Override
            public void onSuccess(Response response) {
                listStudents = new ArrayList<>();
                for (int i = 0 ; i < response.getSuccess().getData().getRecords().size() ; i++) {
                    StudentMeetingWithStudent student = new StudentMeetingWithStudent();
                    student.setId(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(0)));
                    student.setMeeting_id(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(1)));
                    student.setStatus(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(2)));
                    student.setStudent_id(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(3)));
                    student.setEmail(response.getSuccess().getData().getRecords().get(i).get(4));
                    student.setFirst_name(response.getSuccess().getData().getRecords().get(i).get(5));
                    student.setLast_name(response.getSuccess().getData().getRecords().get(i).get(6));
                    listStudents.add(student);
                }
                view.showStudentMeetingList();
            }

            @Override
            public void onError(Response response) {
                listStudents = new ArrayList<>();
                view.showStudentMeetingList();
            }
        }, meeting_id);
    }

    @Override
    public ArrayList<StudentMeeting> getFinalStudentMeetingList() {
        listUpdate = new ArrayList<>();
        for(StudentMeetingWithStudent s : getStudenMeetingsList()) {
            StudentMeeting sm = new StudentMeeting();
            sm.setId(s.getId());
            sm.setMeeting_id(s.getMeeting_id());
            sm.setStudent_id(s.getStudent_id());
            sm.setStatus(s.getStatus());
            listUpdate.add(sm);
        }
        return listUpdate;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public String getType() {
        return type;
    }
}
