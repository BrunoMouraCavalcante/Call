package it.find.com.call.model.network.interfaces;

import it.find.com.call.model.network.response.models.Response;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Bruno on 13-Feb-18.
 */

public interface StudentMeetingApi {

    @GET("api/chamada/StudentMeeting")
    Call<Response> loadStudentMeetings();

    @GET("api/chamada/StudentMeeting/meeting/{id}")
    Call<Response> loadStudentMeetingsByMeeting(@Path("id") int id);

    @GET("api/chamada/StudentMeeting/student/{id}")
    Call<Response> loadStudentMeetingsByStudent(@Path("id") int id);

    @POST("api/chamada/StudentMeeting")
    Call<Response> saveStudentMeetings(@Part("student") RequestBody student);

    interface StudentMeetingResponse {
        void onSuccess(Response response);
        void onError(Response response);
    }
}
