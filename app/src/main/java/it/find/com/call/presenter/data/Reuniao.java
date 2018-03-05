package it.find.com.call.presenter.data;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Bruno on 26-Feb-18.
 */

public class Reuniao {

    private Integer meeting_id;
    private Integer type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", locale = "pt-BR", timezone = "America/Sao_Paulo")
    private Timestamp date;
    private int presence;
    private int late;
    private int miss;

    public Reuniao() { }

    public Integer getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(Integer meeting_id) {
        this.meeting_id = meeting_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getPresence() {
        return presence;
    }

    public void setPresence(int presence) {
        this.presence = presence;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getMiss() {
        return miss;
    }

    public void setMiss(int miss) {
        this.miss = miss;
    }

    public Timestamp getDateFromString(String value) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            formatter.setLenient(false);
            String[] date = value.split(" ");
            Date dateFormated = formatter.parse(date[0].replaceAll("/","-"));
            Timestamp timestamp = new Timestamp(dateFormated.getTime());
            return timestamp;
        } catch (Exception e) {
            Log.d("Reuniao", "Fail in convert String " + value + " to Timestamp :: "+e.getMessage());
            return null;
        }
    }
}
