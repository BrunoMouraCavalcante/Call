package it.find.com.call.presenter.data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * Created by Bruno on 13-Feb-18.
 */

public class Meeting {

    private Integer meeting_id;
    private Integer type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Timestamp date;

    public Meeting(Integer meeting_id, Integer type, Timestamp date) {
        this.meeting_id = meeting_id;
        this.type = type;
        this.date = date;
    }

    public Meeting() { }

    public Integer getMeeting_id() { return meeting_id; }

    public void setMeeting_id(Integer meeting_id) { this.meeting_id = meeting_id; }

    public Integer getType() { return type; }

    public void setType(Integer type) { this.type = type; }

    public Timestamp getDate() { return date; }

    public void setDate(Timestamp date) { this.date = date; }
}
