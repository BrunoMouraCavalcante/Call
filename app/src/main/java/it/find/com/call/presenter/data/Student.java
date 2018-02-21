package it.find.com.call.presenter.data;

/**
 * Created by Bruno on 07-Feb-18.
 */

public class Student {

    private Integer id;
    private String first_name;
    private String last_name;
    private String email;
    private byte[] file;

    public Student(Integer id, String name, String lastName, String email) {
        this.id = id;
        this.first_name = name;
        this.last_name = lastName;
        this.email = email;
        this.file = null;
    }

    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return first_name;
    }

    public void setName(String name) {
        this.first_name = name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String lastName) {
        this.last_name = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
