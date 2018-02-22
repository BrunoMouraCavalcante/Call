package it.find.com.call.model.network.request;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import it.find.com.call.model.network.interfaces.StudentMeetingApi;
import it.find.com.call.model.network.response.models.Response;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.data.StudentMeeting;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Bruno on 13-Feb-18.
 */

public class StudentMeetingsRequests {
    private static final String TAG = MeetingsRequests.class.getSimpleName();

    private static String BASE_URL = "https://apifindit.herokuapp.com/";

    public static void getStudentsMeeting(final StudentMeetingApi.StudentMeetingResponse listener) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentMeetingApi studentMeetingApi = retrofit.create(StudentMeetingApi.class);
        Call<Response> call = studentMeetingApi.loadStudentMeetings();
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

    public static void saveStudentsMeeting(final StudentMeetingApi.StudentMeetingResponse listener, ArrayList<StudentMeeting> listStudents) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ObjectMapper mapper = new ObjectMapper();
            StudentMeetingApi studentMeetingApi = retrofit.create(StudentMeetingApi.class);
            String data = mapper.writeValueAsString(listStudents);
            RequestBody studentPart = RequestBody.create(okhttp3.MultipartBody.FORM, data);
            Call<Response> call = studentMeetingApi.saveStudentMeetings(studentPart);
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

    public static void saveStudentsAndMeeting(final StudentMeetingApi.StudentMeetingResponse listener, Meeting meeting, ArrayList<StudentMeeting> listStudents) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ObjectMapper mapper = new ObjectMapper();
            StudentMeetingApi studentMeetingApi = retrofit.create(StudentMeetingApi.class);
            String dataMeeting = mapper.writeValueAsString(meeting);
            String dataStudents = mapper.writeValueAsString(listStudents);
            RequestBody studentsPart = RequestBody.create(okhttp3.MultipartBody.FORM, dataStudents);
            RequestBody meetingPart = RequestBody.create(okhttp3.MultipartBody.FORM, dataMeeting);
            Call<Response> call = studentMeetingApi.saveStudentsAndMeeting(meetingPart, studentsPart);
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
