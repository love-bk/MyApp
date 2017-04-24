package gjj.com.myapp.utils;


/**
 * Created by 13287 on 2016/3/12.
 */
public class UrlManager {

    public static String Login_url= Constants.BASE_URL+"/login.json";
    public static String SendNotice_url=Constants.BASE_URL+"/sendMail.json";
    public static String GraduateProject_url = Constants.BASE_URL+"/getGraduateProjectByTutorId.json";
    public static String ReplyGroup_url = Constants.BASE_URL+"/getReplyGroupByTutor.json";
    public static String Notice_url = Constants.BASE_URL+"/mail.json";
    public static String Student_url = Constants.BASE_URL+"/myStudent.json";
    public static String submitScore_url = Constants.BASE_URL+"/setScoreWithProjectByGroupMembers.json";


    public static String getAddressee_url = Constants.BASE_URL+"/getAllSchoolAndMyStudent.json";
}
