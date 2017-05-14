package gjj.com.myapp.baseframework.retrofit;

import gjj.com.myapp.model.User;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by 高娟娟 on 2017/4/7.
 */

public interface ApiStores {
    //baseUrl
    String API_SERVER_URL = "http://115.159.195.172:8080/bysj3/";

//    @POST("login.json")
//    Observable<Tutor> loadLoginData(@Body User user);
//    @POST("login.json")
//    Call<String> loadLoginData(@Body User user);
    @POST("login.json")
    Observable<String> loadLoginData(@Body User user);

    @POST("getProjectAndReplyByTutor.json")
    Observable<String> loadProjectAndReply(@Body String tutorId);

    @POST("getGraduateProjectByTutorId.json")
    Observable<String> loadProject(@Body String tutorId);
    @POST("getReplyGroupByTutor.json")
    Observable<String> loadReply(@Body String tutorId);
    @POST("getMyReceivedNotices.json")
    Observable<String> loadMyReceivedNotices(@Body String tutorId);
    @POST("getMyReleasedNotices.json")
    Observable<String> loadMyReleasedNotices(@Body String tutorId);

    @POST("getAllSchoolAndMyStudent.json")
    Observable<String> loadAddressees(@Body String tutorId);

//      /login.json"
//      "/sendMail.json";
//      "/getGraduateProjectByTutorId.json";
//      /getReplyGroupByTutor.json";
//      /mail.json";
//      /myStudent.json";
//            /setScoreWithProjectByGroupMembers.json";


}
