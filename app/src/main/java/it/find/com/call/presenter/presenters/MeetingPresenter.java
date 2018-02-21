package it.find.com.call.presenter.presenters;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.interfaces.meetings.MeetingImpl;
import it.find.com.call.model.models.MeetingModel;
import it.find.com.call.model.models.StudentModel;
import it.find.com.call.model.network.interfaces.MeetingsApi;
import it.find.com.call.model.network.interfaces.StudentApi;
import it.find.com.call.model.network.response.models.Response;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.Student;

/**
 * Created by Bruno on 13-Feb-18.
 */

public class MeetingPresenter implements MeetingImpl.PresenterImpl {

    private static final String TAG = MeetingPresenter.class.getSimpleName();
    private MeetingModel modelMeeting;
    private StudentModel modelStudent;
    private Context context;
    private MeetingImpl.ViewImpl view;
    private ArrayList<Meeting> meetings;
    private ArrayList<Student> students;
    private String type;

    public MeetingPresenter(Context context) {
        this.context = context;
        this.modelMeeting = new MeetingModel();
        this.modelStudent = new StudentModel();
    }

    @Override
    public Context getContext() {
        return null;
    }

    @Override
    public void showProgressBar(boolean show) {
        view.showProgressBar(show);
    }

    @Override
    public void showToast(String message) {
        view.showToast(message);
    }

    @Override
    public void setView(MeetingImpl.ViewImpl view) {
        this.view = view;
    }

    @Override
    public ArrayList<Meeting> getMeetings() {
        return this.meetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        modelMeeting.saveMeeting(new MeetingsApi.MeetingsResponse() {
            @Override
            public void onSuccess(Response response) {
                view.cleanFields();
                showProgressBar(false);
                view.showToast(getMeetType()+" criada com sucesso!");
            }

            @Override
            public void onError(Response response) {
                showProgressBar(false);
                view.showToast("Falha ao criar a "+getMeetType()+ " :( tente novamente mais tarde");
            }
        }, meeting);
    }

    @Override
    public void setMeetingType(String type) {
        this.type = type;
    }

    @Override
    public String getMeetType() {
        return type;
    }

    @Override
    public void fillListStudents() {
        showProgressBar(true);
        modelStudent.getStudents(new StudentApi.StudentResponse() {
            @Override
            public void onSuccess(Response response) {
                students = new ArrayList<>();
                for (int i = 0 ; i < response.getSuccess().getData().getRecords().size() ; i++) {
                    Student student = new Student();
                    student.setId(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(0)));
                    student.setName(response.getSuccess().getData().getRecords().get(i).get(1));
                    student.setEmail(response.getSuccess().getData().getRecords().get(i).get(2));
                    students.add(student);
                }
                verifyListEmpty();
            }

            @Override
            public void onError(Response response) {
                students = new ArrayList<>();
                view.createEmptyList();
            }
        });
    }

    @Override
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    private void verifyListEmpty() {
        if (students.isEmpty()) {
            view.createEmptyList();
        } else {
            view.createListStudents();
        }
    }
}
