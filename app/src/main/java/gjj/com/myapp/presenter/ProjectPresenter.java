package gjj.com.myapp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.baseframework.retrofit.ApiStores;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.views.ProjectView;

/**
 * Created by yyz on 2017/5/3.
 */

public class ProjectPresenter extends BasePresenter<ProjectView> {
    public ProjectPresenter(ProjectView projectView) {
        attachView(projectView);
    }


    public void loadProjectData(String tutorId) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadProject(tutorId), new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                //解析数据
                Gson gson = new Gson();
                List<GraduateProject> projects = gson.fromJson(model, new TypeToken<List<GraduateProject>>() {
                }.getType());
                mvpView.loadSucceed(projects);
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

    public void loadProjectFromDB() {
        //从数据库中获取数据

//        mvpView.loadSucceed(null);
    }
}
