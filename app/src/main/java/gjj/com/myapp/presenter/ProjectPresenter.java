package gjj.com.myapp.presenter;

import android.content.Context;
import android.widget.Switch;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.baseframework.retrofit.ApiStores;
import gjj.com.myapp.dao.GraduateProject_Dao;
import gjj.com.myapp.dao.Scores_Dao;
import gjj.com.myapp.dao.Student_Dao;
import gjj.com.myapp.greendao.gen.ScoresDao;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.Scores;
import gjj.com.myapp.model.Student;
import gjj.com.myapp.myproject.ProjectDetailActivity;
import gjj.com.myapp.myproject.ProjectFragment;
import gjj.com.myapp.myreply.views.ReplyScoreDetailActivity;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.ProjectView;

/**
 * Created by yyz on 2017/5/3.
 */

public class ProjectPresenter extends BasePresenter<ProjectView> {
    private Context context;
    public ProjectPresenter(ProjectView projectView) {
        attachView(projectView);
        if (projectView instanceof ProjectFragment){
            this.context = ProjectFragment.mActivity;
        }else if (projectView instanceof  ProjectDetailActivity){
            this.context = ((ProjectDetailActivity) projectView);
        }else if (projectView instanceof ReplyScoreDetailActivity){
            this.context = (ReplyScoreDetailActivity)projectView;
        }else {

        }
    }


    /**
     * 从服务器获取数据
     * @param tutorId
     */
    public void loadProjectData(String tutorId) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadProject(tutorId), new ApiCallback<String>() {
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

    /**
     * 处理返回来的数据
     * @param model
     * @return
     */
    private List<GraduateProject> handleData(String model) {
        //解析数据
        Gson gson = new Gson();
        List<GraduateProject> projects = gson.fromJson(model, new TypeToken<List<GraduateProject>>() {
        }.getType());
        //将数据保存到数据库中
        if (projects!=null&&projects.size()!=0){
            //将数据保存到数据库中
            for (GraduateProject project : projects) {
                Student student = project.getStudent();
                if (student != null){
                    Student_Dao.getInstance(context).insertStudent(student);
                }
                if (project.getReplyGroupId()!=null){
                    //处理分数
                    project.setScores(handleScore(project));
                }
                GraduateProject_Dao.getInstance(context).insertProject(project);
            }
        }
        return projects;
    }

    /**
     * 处理分数
     * @param project
     */
    private Scores handleScore(GraduateProject project) {
        Scores scores = Scores_Dao.getInstance(context).queryDatasByProjectId(project.getId());
        if (scores == null) {
            Integer score0 = project.getCompletenessScoreByGroup();
            Integer score1 = project.getCorrectnessScoreByGroup();
            Integer score2 = project.getQualityScoreBtGroup();
            Integer score3 = project.getReplyScoreByGroup();
            if (score0 == null) {
                score0 = 0;
            }
            if (score1 == null) {
                score1 = 0;
            }
            if (score2 == null) {
                score2 = 0;
            }
            if (score3 == null) {
                score3 = 0;
            }
            int sum = score0 + score1 + score2 + score3;
            int scoresState;
            if (sum == 0) {
                scoresState = 0;
            } else {
                scoresState = 2;
            }
            scores = new Scores(project.getId(), score0, score1, score2, score3, scoresState);
            Scores_Dao.getInstance(context).insert(scores);
        }
        return scores;
    }

    /**
     * 根据课题的分类获取课题列表
     * @param category
     */
    public void loadProjectFromDB(String category) {
        //从数据库中获取数据
        List<GraduateProject> projects = new ArrayList<>();
        long tutorId = SPUtil.getTutorIdfromSP(context);
        switch (category){
            case Constants.ALL:
                 projects = GraduateProject_Dao.getInstance(context).queryProjectListByTutorId(tutorId);
                break;
            case Constants.DESIGN:
                projects = GraduateProject_Dao.getInstance(context).queryProjectListByCategory("设计题目", tutorId);
                break;
            case Constants.PAGE:
                projects = GraduateProject_Dao.getInstance(context).queryProjectListByCategory("论文题目", tutorId);
                break;
        }
        for (GraduateProject project : projects) {
            Student student = Student_Dao.getInstance(context).queryDatasByProjectId(project.getId());
            project.setStudent(student);
        }
        mvpView.loadSucceed(projects);
    }

    /**
     * 根据课题id获取课题
     * @param projectId
     */
    public void loadProjectFromDb(long projectId){
        List<GraduateProject> projects = new ArrayList<>();
        GraduateProject project = GraduateProject_Dao.getInstance(context).queryProjectById(projectId);
        project.setStudent(Student_Dao.getInstance(context).queryDatasByProjectId(projectId));
        project.setScores(Scores_Dao.getInstance(context).queryDatasByProjectId(projectId));
        projects.add(project);
        mvpView.loadSucceed(projects);
    }



    /**
     * 从服务器获取数据
     * @param project
     */
    public void submitScores(GraduateProject project) {
        mvpView.showLoading();
        addSubscription(mApiStores.submitScores(project), new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                mvpView.submitSucceed(true);
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
     * 保存数据到数据库中
     * @param scores
     */
    public void saveScoresToDB(Scores scores) {
        Scores_Dao.getInstance(context).insert(scores);
    }
}
