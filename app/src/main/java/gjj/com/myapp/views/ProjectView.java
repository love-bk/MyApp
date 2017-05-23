package gjj.com.myapp.views;

import java.util.List;

import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.model.GraduateProject;

/**
 * Created by yyz on 2017/5/3.
 */

public interface ProjectView extends BaseView {

    void loadSucceed(List<GraduateProject> projects);
    void submitSucceed(Boolean status);

    void loadFail(String msg);
}
