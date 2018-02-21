package it.find.com.call.model.models;

import java.io.IOException;

import it.find.com.call.interfaces.meetings.MeetingImpl;
import it.find.com.call.model.network.interfaces.MeetingsApi;
import it.find.com.call.model.network.request.MeetingsRequests;
import it.find.com.call.presenter.data.Meeting;

/**
 * Created by Bruno on 13-Feb-18.
 */

public class MeetingModel implements MeetingImpl.ModelImpl{

    public MeetingModel() { }


    @Override
    public void getMeetings(MeetingsApi.MeetingsResponse handler) {
        try {
            MeetingsRequests.getMeetings(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMeeting(MeetingsApi.MeetingsResponse handler, Meeting meeting) {
        MeetingsRequests.saveMeetings(handler,meeting);
    }

    @Override
    public void updateMeeting(MeetingsApi.MeetingsResponse handler, Meeting meeting) {

    }

    @Override
    public void deleteMeeting(MeetingsApi.MeetingsResponse handler, int id) {

    }
}
