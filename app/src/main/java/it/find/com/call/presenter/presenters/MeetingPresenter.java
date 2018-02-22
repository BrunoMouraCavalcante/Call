package it.find.com.call.presenter.presenters;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.interfaces.meetings.MeetingImpl;
import it.find.com.call.interfaces.students_in_meetings.StudentMeetingImp;
import it.find.com.call.model.models.MeetingModel;
import it.find.com.call.model.models.StudentMeetingModel;
import it.find.com.call.model.models.StudentModel;
import it.find.com.call.model.network.interfaces.StudentApi;
import it.find.com.call.model.network.interfaces.StudentMeetingApi;
import it.find.com.call.model.network.response.models.Response;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.Student;
import it.find.com.call.presenter.data.StudentMeeting;

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
    private ArrayList<StudentMeeting> studentsMeeting;
    private String type;

    private StudentMeetingInternalPresenter smiPresenter;

    public MeetingPresenter(Context context) {
        this.context = context;
        this.modelMeeting = new MeetingModel();
        this.modelStudent = new StudentModel();
        this.smiPresenter = new StudentMeetingInternalPresenter();
        this.smiPresenter.model = new StudentMeetingModel();
    }

    public StudentMeetingInternalPresenter getSmiPresenter() {
        return smiPresenter;
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
        if (validadeStudentsMeeting()) {
            smiPresenter.createStudentMeeting(meeting, studentsMeeting);
        }
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
                studentsMeeting = new ArrayList<>();
                view.createEmptyList();
            }
        });
    }

    @Override
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    public void setStudentMeetingView(StudentMeetingImp.ViewImpl view) { this.smiPresenter.setView(view); }

    private void verifyListEmpty() {
        generateStudentsMeeting();
        if (students.isEmpty()) {
            view.createEmptyList();
        } else {
            view.createListStudents();
        }
    }

    private void generateStudentsMeeting() {
        studentsMeeting = new ArrayList<>();
        for(Student s : students) {
            StudentMeeting sm = new StudentMeeting();
            sm.setStudent_id(s.getId());
            studentsMeeting.add(sm);
        }
    }


    private boolean validadeStudentsMeeting() {
        for(StudentMeeting sm : studentsMeeting) {
            if (sm.getStatus() == null || sm.getStatus() < 1 || sm.getStatus() > 3 ) {
                view.showToast("Preencha o status de todos os membros");
                return false;
            }
        }
        return true;
    }

    public class StudentMeetingInternalPresenter implements StudentMeetingImp.PresenterImpl {

        private StudentMeetingModel model;
        private StudentMeetingImp.ViewImpl view;


        @Override
        public void setView(StudentMeetingImp.ViewImpl view) {
            this.view = view;
        }

        @Override
        public void setStudentStatus(int position, int status) {
            if (studentsMeeting != null && !studentsMeeting.isEmpty()) {
                studentsMeeting.get(position).setStatus(status);
            }
        }

        @Override
        public void createStudentMeeting(final Meeting meeting, final ArrayList<StudentMeeting> studentsMeeting) {
            model.createStudentsAndMeeting(new StudentMeetingApi.StudentMeetingResponse() {
                @Override
                public void onSuccess(Response response) {
                    showProgressBar(false);
                    view.showToast(getMeetType()+" criada com sucesso!");
                }

                @Override
                public void onError(Response response) {
                    view.showProgressBar(false);
                    view.showToast("Falha ao criar a "+getMeetType()+ " :( tente novamente mais tarde");
                }
            },meeting, studentsMeeting);
        }

        @Override
        public ArrayList<StudentMeeting> getStudenMeetings() {
            return studentsMeeting;
        }

        public StudentMeetingImp.PresenterImpl getThis(){
            return this;
        }
    }
}
