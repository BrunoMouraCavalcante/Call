package it.find.com.call.presenter.data;

/**
 * Created by Bruno on 05-Mar-18.
 */

public class StudentMeetingWithStudent {

    private Integer id;
    private Integer meeting_id;
    private Integer status;
    private Integer student_id;
    private String email;
    private String first_name;
    private String last_name;
    private byte[] file;

    public StudentMeetingWithStudent(Integer id, Integer student_id, Integer meeting_id, Integer status, String first_name, String last_name, String email, byte[] file) {
        this.id = id;
        this.student_id = student_id;
        this.meeting_id = meeting_id;
        this.status = status;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.file = file;
    }

    public StudentMeetingWithStudent() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getCompleteName() {
        return this.getFirst_name() + " " + this.getLast_name();
    }
}
