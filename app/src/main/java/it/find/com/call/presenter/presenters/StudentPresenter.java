package it.find.com.call.presenter.presenters;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.interfaces.student.StudentImpl;
import it.find.com.call.model.models.StudentModel;
import it.find.com.call.model.network.interfaces.StudentApi;
import it.find.com.call.model.network.response.models.Response;
import it.find.com.call.presenter.data.Student;

/**
 * Created by Bruno on 12-Feb-18.
 */

public class StudentPresenter implements StudentImpl.BasePresenterImpl, StudentImpl.PresentListImpl, StudentImpl.PresentSaveImpl {

    private static final String TAG = StudentPresenter.class.getSimpleName();
    private StudentModel model;
    private StudentImpl.ViewListImpl viewList;
    private StudentImpl.ViewSaveImpl viewSave;
    private StudentImpl.BaseViewImpl viewBase;
    private ArrayList<Student> students;
    private Context context;

    public StudentPresenter(Context context) {
        this.model = new StudentModel() ;
        this.context = context;
    }

    @Override
    public void setView(StudentImpl.ViewListImpl view) {
        this.viewList = view;
    }

    @Override
    public void fillListStudents() {
        showProgressBar(true);
        model.getStudents(new StudentApi.StudentResponse() {
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
                viewList.createEmptyList();
            }
        });
    }

    @Override
    public ArrayList<Student> getStudents() {
        return this.students;
    }

    @Override
    public void setStudents(ArrayList<Student> students) {

    }

    @Override
    public void setView(StudentImpl.ViewSaveImpl view) {
        this.viewSave = view;
    }

    @Override
    public void createStudent(final Student student) {
        showProgressBar(true);
        model.createStudent(new StudentApi.StudentResponse() {
            @Override
            public void onSuccess(Response response) {
                viewSave.cleanView();
                //addMember(student);
                fillListStudents();
                showProgressBar(false);
                viewSave.showToast("Usuário adicionado com sucesso!");
            }

            @Override
            public void onError(Response response) {
                showProgressBar(false);
                viewSave.showToast("Problema ao adicionar usuário :( tente mais tarde!");
            }
        }, student);
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void showProgressBar(boolean show) {
        viewBase.showProgressBar(show);
    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void addMember(Student student) {
        students.add(student);
        verifyListEmpty();
    }

    @Override
    public void removeMember(final int id, final int position) {
        showProgressBar(true);
        model.deleteStudent(new StudentApi.StudentResponse() {
            @Override
            public void onSuccess(Response response) {
                students.remove(position);
                verifyListEmpty();
                showProgressBar(false);
                viewList.showToast("Membro removido com sucesso!");
            }

            @Override
            public void onError(Response response) {
                showProgressBar(false);
                viewList.showToast("Ocorreu um problema ao remover o membro :( tente novamente mais tarde");
            }
        }, id);
    }

    @Override
    public void setView(StudentImpl.BaseViewImpl view) {
        this.viewBase = view;
    }

    private void verifyListEmpty() {
        if (students.isEmpty()) {
            viewList.createEmptyList();
        } else {
            viewList.createListStudents();
        }
    }
}
