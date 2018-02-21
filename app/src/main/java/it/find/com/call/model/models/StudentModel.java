package it.find.com.call.model.models;

import java.io.IOException;

import it.find.com.call.interfaces.student.StudentImpl;
import it.find.com.call.model.network.interfaces.StudentApi;
import it.find.com.call.model.network.request.StudentsRequests;
import it.find.com.call.presenter.data.Student;

/**
 * Created by Bruno on 12-Feb-18.
 */

public class StudentModel implements StudentImpl.ModelImpl {

    public StudentModel() { }

    @Override
    public void getStudents(StudentApi.StudentResponse handler) {
        try {
            StudentsRequests.getStudents(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createStudent(StudentApi.StudentResponse handler, Student student) {
        StudentsRequests.saveStudents(handler, student);
    }

    @Override
    public void deleteStudent(StudentApi.StudentResponse handler, int id) {
        StudentsRequests.deleterStudent(handler, id);
    }

    @Override
    public void updateStudent(StudentApi.StudentResponse handler, Student student) {

    }
}
