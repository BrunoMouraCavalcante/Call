package it.find.com.call.model.network.request;

import android.util.Log;

import java.io.IOException;

import it.find.com.call.presenter.data.Student;
import it.find.com.call.model.network.interfaces.StudentApi;
import it.find.com.call.model.network.response.models.Response;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by Bruno on 04-Feb-18.
 */

public class StudentsRequests {

    private static final String TAG = StudentsRequests.class.getSimpleName();

    private static String BASE_URL = "https://apifindit.herokuapp.com/";

    public static void getStudents(final StudentApi.StudentResponse listener) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentApi studentApi = retrofit.create(StudentApi.class);
        Call<Response> call = studentApi.loadStudents();
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

    public static void saveStudents(final StudentApi.StudentResponse listener, Student student) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentApi studentApi = retrofit.create(StudentApi.class);
        String data = "{\"first_name\": \""+student.getName()+"\", \"last_name\": \""+student.getLastName()+"\", \"email\": \""+student.getEmail()+"\"}";
        RequestBody studentPart = RequestBody.create(okhttp3.MultipartBody.FORM, data);
        Call<Response> call = studentApi.saveStudents(studentPart);
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
    }

    public static void deleterStudent(final StudentApi.StudentResponse listener, Integer id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        StudentApi studentApi = retrofit.create(StudentApi.class);
        Call<Response> call = studentApi.deleteStudents(id);
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
    }
}
