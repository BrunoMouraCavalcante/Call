package it.find.com.call.presenter.presenters;

import android.app.Activity;
import android.content.Context;

import java.util.ArrayList;

import it.find.com.call.interfaces.students_in_meetings.ControlImpl;
import it.find.com.call.model.models.MeetingModel;
import it.find.com.call.model.network.interfaces.MeetingsApi;
import it.find.com.call.model.network.response.models.Response;
import it.find.com.call.presenter.data.Reuniao;

/**
 * Created by Bruno on 26-Feb-18.
 */

public class ControlPresenter implements ControlImpl.presenterImpl {

    private Context context;
    private MeetingModel model;
    private ControlImpl.BaseViewImpl baseView;
    private ControlImpl.ReuniaoViewImpl reuniaoView;
    private ControlImpl.SedeViewImpl sedeView;
    private ControlImpl.ControlViewImpl controlView;
    private ArrayList<Reuniao> reuniaoList;
    private ArrayList<Reuniao> sedeList;
    private Activity activity;

    public ControlPresenter(Context context, Activity activity) {
        this.model = new MeetingModel();
        this.context = context;
        this.activity = activity;
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public void showProgressBar(boolean show) {
        baseView.showProgressBar(show);
    }

    @Override
    public void showToast(String message) {
        baseView.showToast(message);
    }

    @Override
    public void setBaseView(ControlImpl.BaseViewImpl view) {
        this.baseView = view;
    }

    @Override
    public void setReuniaoView(ControlImpl.ReuniaoViewImpl view) {
        this.reuniaoView = view;
    }

    @Override
    public void setSedeView(ControlImpl.SedeViewImpl view) {
        this.sedeView = view;
    }

    @Override
    public void setControlView(ControlImpl.ControlViewImpl view) {
        this.controlView = view;
    }

    @Override
    public void fillListReuniao() {
        baseView.showProgressBar(true);
        model.getMeetingsByType(new MeetingsApi.MeetingsResponse() {
            @Override
            public void onSuccess(Response response) {
                reuniaoList = new ArrayList<>();
                for (int i = 0 ; i < response.getSuccess().getData().getRecords().size() ; i++) {
                    Reuniao reuniao = new Reuniao();
                    reuniao.setMeeting_id(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(0)));
                    reuniao.setType(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(1)));
                    reuniao.setDate(reuniao.getDateFromString(response.getSuccess().getData().getRecords().get(i).get(2)));
                    reuniao.setPresence(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(3)));
                    reuniao.setLate(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(4)));
                    reuniao.setMiss(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(5)));
                    reuniaoList.add(reuniao);
                }
                reuniaoView.showReuniaoList();
            }

            @Override
            public void onError(Response response) {
                reuniaoList = new ArrayList<>();
                reuniaoView.showReuniaoList();
            }
        }, 1);
    }

    @Override
    public void fillListSede() {
        baseView.showProgressBar(true);
        model.getMeetingsByType(new MeetingsApi.MeetingsResponse() {
            @Override
            public void onSuccess(Response response) {
                sedeList = new ArrayList<>();
                for (int i = 0 ; i < response.getSuccess().getData().getRecords().size() ; i++) {
                    Reuniao reuniao = new Reuniao();
                    reuniao.setMeeting_id(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(0)));
                    reuniao.setType(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(1)));
                    reuniao.setDate(reuniao.getDateFromString(response.getSuccess().getData().getRecords().get(i).get(2)));
                    reuniao.setPresence(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(3)));
                    reuniao.setLate(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(4)));
                    reuniao.setMiss(Integer.parseInt(response.getSuccess().getData().getRecords().get(i).get(5)));
                    sedeList.add(reuniao);
                }
                sedeView.showSedeList();
            }

            @Override
            public void onError(Response response) {
                sedeList = new ArrayList<>();
                sedeView.showSedeList();
            }
        }, 2);
    }

    @Override
    public void fillListStudents() {

    }

    @Override
    public ArrayList<Reuniao> getListReuniao() {
        return reuniaoList;
    }

    @Override
    public ArrayList<Reuniao> getListSede() { return sedeList; }

    @Override
    public void getListControl() {

    }
}
