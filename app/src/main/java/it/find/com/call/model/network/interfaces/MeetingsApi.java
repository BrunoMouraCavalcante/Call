package it.find.com.call.model.network.interfaces;

import it.find.com.call.model.network.response.models.Response;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Bruno on 13-Feb-18.
 */

public interface MeetingsApi {

    @GET("api/chamada/Meetings")
    Call<Response> loadMeetings();

    @Multipart
    @POST("api/chamada/Meetings")
    Call<Response> saveMeetings(@Part("meeting") RequestBody meeting);

    interface MeetingsResponse {
        void onSuccess(Response response);
        void onError(Response response);
    }
}
