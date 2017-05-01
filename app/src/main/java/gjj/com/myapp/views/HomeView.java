package gjj.com.myapp.views;

import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.model.Tutor;

/**
 * Created by yyz on 2017/5/1.
 */

public interface HomeView extends BaseView {

    void loadSucceed(String projectAndReply);
//    void loginSuccess(String tutor);

    void loadFail(String msg);
}
