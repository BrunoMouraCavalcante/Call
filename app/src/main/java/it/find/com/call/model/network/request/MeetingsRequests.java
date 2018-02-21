package it.find.com.call.model.network.request;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import it.find.com.call.model.network.interfaces.MeetingsApi;
import it.find.com.call.model.network.response.models.Response;
import it.find.com.call.presenter.data.Meeting;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Bruno on 13-Feb-18.
 */

public class MeetingsRequests {
    private static final String TAG = MeetingsRequests.class.getSimpleName();

    private static String BASE_URL = "https://apifindit.herokuapp.com/";

    public static void getMeetings(final MeetingsApi.MeetingsResponse listener) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MeetingsApi meetingsApi = retrofit.create(MeetingsApi.class);
        Call<Response> call = meetingsApi.loadMeetings();
        call.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                try {
                    if (response.body()!= null && response.body().getError() != null && response.body().getError().getCode() != null) {
                        listener.onError(response.body());
                    } else {
                        listener.onSuccess(response.body());
                    }
                } catch (Exception e) {
                    Log.d(TAG, this.getClass().getEnclosingMethod().getName()+" get "+e.getMessage());
                    listener.onError(null);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                listener.onError(null);
            }
        });
    }

    public static void saveMeetings(final MeetingsApi.MeetingsResponse listener, Meeting meeting) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ObjectMapper mapper = new ObjectMapper();
            MeetingsApi meetingsApi = retrofit.create(MeetingsApi.class);
            String data = mapper.writeValueAsString(meeting);
            RequestBody studentPart = RequestBody.create(okhttp3.MultipartBody.FORM, data);
            Call<Response> call = meetingsApi.saveMeetings(studentPart);
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    try {
                        if (response.body()!= null && response.body().getError() != null && response.body().getError().getCode() != null) {
                            listener.onError(response.body());
                        } else {
                            listener.onSuccess(response.body());
                        }
                    } catch (Exception e) {
                        Log.d(TAG, this.getClass().getEnclosingMethod().getName()+" save "+e.getMessage());
                        listener.onError(null);
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    listener.onError(null);
                }
            });
        } catch (Exception e) {
            listener.onError(null);
        }
    }
}