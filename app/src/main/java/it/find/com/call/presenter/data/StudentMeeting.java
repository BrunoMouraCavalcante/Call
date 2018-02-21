package it.find.com.call.presenter.data;

/**
 * Created by Bruno on 13-Feb-18.
 */

public class StudentMeeting {
    private Integer id;
    private Integer student_id;
    private Integer meeting_id;
    private Integer status;

    public StudentMeeting() { }

    public StudentMeeting(Integer id, Integer student_id, Integer meeting_id, Integer status) {
        this.id = id;
        this.student_id = student_id;
        this.meeting_id = meeting_id;
        this.status = status;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public Integer getStudent_id() { return student_id; }

    public void setStudent_id(Integer student_id) { this.student_id = student_id; }

    public Integer getMeeting_id() { return meeting_id; }

    public void setMeeting_id(Integer meeting_id) { this.meeting_id = meeting_id; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }
}
