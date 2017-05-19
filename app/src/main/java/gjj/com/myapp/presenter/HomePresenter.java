package gjj.com.myapp.presenter;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import gjj.com.myapp.HomeActivity;
import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.dao.Addressee_Dao;
import gjj.com.myapp.dao.GraduateProject_Dao;
import gjj.com.myapp.dao.Notice_Dao;
import gjj.com.myapp.dao.ReplyGroup_Dao;
import gjj.com.myapp.dao.Student_Dao;
import gjj.com.myapp.dao.Tutor_Dao;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.ProjectAndReply;
import gjj.com.myapp.model.ReplyGroup;
import gjj.com.myapp.model.Student;
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
            //处理课题
            handleProjectAndStudent(projects);
            //处理答辩小组
            if (groups != null && groups.size() != 0){
                //将数据保存到数据库中
                for (ReplyGroup group : groups) {
                    List<GraduateProject> graduateProjects = group.getGraduateProjects();
                    //处理答辩小组里的课题
                    handleProjectAndStudent(graduateProjects);
                }
                ReplyGroup_Dao.getInstance(context).insertReplyGroupList(groups);
            }
        }
    }

    /**
     * 处理课题和学生
     * @param projects
     */
    private void handleProjectAndStudent(List<GraduateProject> projects) {
        if (projects!=null&&projects.size()!=0){
            //将数据保存到数据库中
            for (GraduateProject project : projects) {
                Student student = project.getStudent();
                if (student != null){
                    Student_Dao.getInstance(context).insertStudent(student);
                }
                GraduateProject_Dao.getInstance(context).insertProject(project);
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
        SPUtil.removeConfig(context,Constants.USERNAME,Constants.PASSWORD,Constants.TUTORID,Constants.ACOUNTNAME);
        Addressee_Dao.getInstance(context).deleteAllData();
        GraduateProject_Dao.getInstance(context).deleteAllData();
        Notice_Dao.getInstance(context).deleteAllData();
        ReplyGroup_Dao.getInstance(context).deleteAllData();
        Student_Dao.getInstance(context).deleteAllData();
        Tutor_Dao.getInstance(context).deleteAllData();
    }
}
