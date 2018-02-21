package it.find.com.call.interfaces.student;

import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.model.network.interfaces.StudentApi;
import it.find.com.call.presenter.data.Student;

/**
 * Created by Bruno on 12-Feb-18.
 */

public interface StudentImpl {

    interface ModelImpl {
        void getStudents(StudentApi.StudentResponse handler);
        void createStudent(StudentApi.StudentResponse handler, Student student);
        void deleteStudent(StudentApi.StudentResponse handler, int id);
        void updateStudent(StudentApi.StudentResponse handler, Student student);
    }

    interface BaseViewImpl {
        void showProgressBar(boolean show);
        void showToast(String message);
    }

    interface ViewSaveImpl extends BaseViewImpl {
        void cleanView();
    }

    interface ViewListImpl extends BaseViewImpl {
        void createListStudents();
        void createEmptyList();
    }

    interface BasePresenterImpl {
        Context getContext();
        void showProgressBar(boolean show);
        void showToast(String message);
        void addMember(Student student);
        void removeMember(int id,int position);
        void setView(BaseViewImpl view);
    }

    interface PresentSaveImpl {
        void setView(ViewSaveImpl view);
        void createStudent(Student student);
    }

    interface PresentListImpl {
        void setView(ViewListImpl view);
        void fillListStudents();
        ArrayList<Student> getStudents();
        void setStudents(ArrayList<Student> students);
    }
}
