package gjj.com.myapp.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.dao.GraduateProject_Dao;
import gjj.com.myapp.dao.ReplyGroup_Dao;
import gjj.com.myapp.dao.Student_Dao;
import gjj.com.myapp.dao.Tutor_Dao;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.ReplyGroup;
import gjj.com.myapp.model.Student;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.myproject.ProjectDetailActivity;
import gjj.com.myapp.myproject.ProjectFragment;
import gjj.com.myapp.myreply.views.ReplyDetailActivity;
import gjj.com.myapp.myreply.views.ReplyFragment;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.ProjectView;
import gjj.com.myapp.views.ReplyView;

/**
 * Created by yyz on 2017/5/3.
 */

public class ReplyPresenter extends BasePresenter<ReplyView> {
    private Context context;

    public ReplyPresenter(ReplyView replyView) {
        attachView(replyView);
        if (replyView instanceof ReplyFragment) {
            this.context = ReplyFragment.mActivity;
        } else {
            this.context = ((ReplyDetailActivity) replyView);
        }
    }


    public void loadReplyGroupData(String tutorId) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadReply(tutorId), new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                mvpView.loadSucceed(handleData(model));
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

    private List<ReplyGroup> handleData(String model) {
        //解析数据
        Gson gson = new Gson();
        List<ReplyGroup> groups = gson.fromJson(model, new TypeToken<List<ReplyGroup>>() {
        }.getType());
        //将数据保存到数据库中
        if (groups != null && groups.size() != 0) {
            //将数据保存到数据库中
            for (ReplyGroup group : groups) {
                List<GraduateProject> graduateProjects = group.getGraduateProjects();
                //操作答辩小组里的课题
                if (graduateProjects != null && graduateProjects.size() != 0) {
                    for (GraduateProject project : graduateProjects) {
                        Student student = project.getStudent();
                        if (student != null) {
                            Student_Dao.getInstance(context).insertStudent(student);
                        }
                    }
                    GraduateProject_Dao.getInstance(context).insertProjectList(graduateProjects);
                }
            }
            ReplyGroup_Dao.getInstance(context).insertReplyGroupList(groups);
        }

        return groups;
    }

    public void loadReplyGroupFromDB() {
        //从数据库中获取数据
        List<ReplyGroup> replyGroups = ReplyGroup_Dao.getInstance(context).queryReplyGroupListByTutorId(SPUtil.getTutorIdfromSP(context));

        mvpView.loadSucceed(replyGroups);
    }

    public void loadReplyGroupFromDb(long replyGroupId) {
        List<ReplyGroup> replyGroups = new ArrayList<>();
        ReplyGroup replyGroup = ReplyGroup_Dao.getInstance(context).queryReplyGroupById(replyGroupId);
        List<GraduateProject> projects = GraduateProject_Dao.getInstance(context).queryProjectByReplyGroupId(replyGroupId);
        for (GraduateProject project : projects) {
            project.setStudent(Student_Dao.getInstance(context).queryDatasByProjectId(project.getId()));
        }
        replyGroup.setGraduateProjects(projects);
        replyGroups.add(replyGroup);
        mvpView.loadSucceed(replyGroups);
    }
}
