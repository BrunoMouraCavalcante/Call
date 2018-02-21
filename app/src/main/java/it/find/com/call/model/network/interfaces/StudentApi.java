package it.find.com.call.model.network.interfaces;

import it.find.com.call.model.network.response.models.Response;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Bruno on 04-Feb-18.
 */

public interface StudentApi {
    @GET("api/chamada/Students")
    Call<Response> loadStudents();

    @Multipart
    @POST("api/chamada/Students")
    Call<Response> saveStudents(@Part("student") RequestBody student/*,
                                       /*@Part("file") RequestBody file*/);

    @DELETE("api/chamada/Students/{id}")
    Call<Response> deleteStudents(@Path("id") Integer id);

    interface StudentResponse {
        void onSuccess(Response response);
        void onError(Response response);
    }
}
