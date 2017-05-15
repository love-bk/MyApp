package gjj.com.myapp.presenter;

import android.content.ComponentName;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.List;

import gjj.com.myapp.HomeActivity;
import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.dao.GraduateProject_Dao;
import gjj.com.myapp.dao.ReplyGroup_Dao;
import gjj.com.myapp.dao.Student_Dao;
import gjj.com.myapp.dao.Tutor_Dao;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.ProjectAndReply;
import gjj.com.myapp.model.ReplyGroup;
import gjj.com.myapp.model.Student;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.HomeView;

/**
 * Created by yyz on 2017/5/1.
 */

public class HomePresenter  extends BasePresenter<HomeView>{
    private Context context;
    public HomePresenter(HomeView homeView) {
        attachView(homeView);
        this.context = (HomeActivity)homeView;
    }


    public void loadProjectAndReply(String tutorId){
        mvpView.showLoading();
        addSubscription(mApiStores.loadProjectAndReply(tutorId),
                new ApiCallback<String>() {
                    @Override
                    public void onSuccess(String model) {
                        Gson gson = new Gson();
                        ProjectAndReply projectAndReply = gson.fromJson(model, ProjectAndReply.class);
                        handleData(projectAndReply);
                        mvpView.loadSucceed(model);
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.loadFail(msg);
                    }

                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }
                });
    }

    /**
     * 处理返回来的数据
     * @param projectAndReply
     */
    private void handleData(ProjectAndReply projectAndReply) {
        if (projectAndReply != null){
            List<GraduateProject> projects = projectAndReply.getGraduateProjectList();
            List<ReplyGroup> groups = projectAndReply.getReplyGroups();
            long tutorId = SPUtil.getTutorIdfromSP(context);
            if (projects!=null&&projects.size()!=0){
                //将数据保存到数据库中
                for (GraduateProject project : projects) {
                    Student student = project.getStudent_name();
                    if (student != null){
                        if (student.getStudentClass()!=null){
                            student.setClassDescription(student.getStudentClass().getDescription());
                        }
                        if (student.getMajor()!=null){
                            student.setMajorDecription(student.getMajor().getDescription());
                        }
                        student.setReplyGraduateProject_id(project.getId());
                        Student_Dao.getInstance(context).insertStudent(student);
                    }
                }
                GraduateProject_Dao.getInstance(context).insertProjectList(projects);
            }else {
                //从数据库中读取数据

            }
            if (groups != null && groups.size() != 0){
                //将数据保存到数据库中
                for (ReplyGroup group : groups) {
                    if (group.getReplyTime()!=null){
                        group.setBeginTime(group.getReplyTime().getBeginTime());
                        group.setEndTime(group.getReplyTime().getEndTime());
                    }
                    group.setTutor_Id(tutorId);
                    List<GraduateProject> graduateProjects = group.getGraduateProjects();
                    List<Tutor> tutors = group.getTutorId();
                    //操作答辩小组里的答辩成员
                    if (tutors != null && tutors.size() != 0){
                        for (Tutor tutor : tutors) {
                            tutor.setReplyId(group.getId());
                            Tutor_Dao.getInstance(context).insert(tutor);
                        }
                    }
                    //操作答辩小组里的课题
                    if (graduateProjects !=null&& graduateProjects.size()!=0){
                        for (GraduateProject project : graduateProjects) {
                            project.setReplyGroup_id(group.getId());
                            Student student = project.getStudent_name();
                            if (student != null){
                                if (student.getStudentClass()!=null){
                                    student.setClassDescription(student.getStudentClass().getDescription());
                                }
                                if (student.getMajor()!=null){
                                    student.setMajorDecription(student.getMajor().getDescription());
                                }
                                student.setReplyGraduateProject_id(project.getId());
                                student.setReplyGroup_id(group.getId());
                                Student_Dao.getInstance(context).insertStudent(student);
                            }
                            GraduateProject_Dao.getInstance(context).insertProject(project);
                        }
                    }
                }
                ReplyGroup_Dao.getInstance(context).insertReplyGroupList(groups);
            }else{
                //从数据库中读取数据

            }
        }
    }

    /**
     * 切换账号时删除指定数据
     * @param context
     */
    public void deleteData(Context context){
        //删除数据库中的数据
        //删除SP中的数据
        SPUtil.removeConfig(context,Constants.USERNAME,Constants.PASSWORD,Constants.TUTORID);
    }
}
