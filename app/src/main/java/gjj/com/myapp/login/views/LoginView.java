package gjj.com.myapp.login.views;

import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.model.MainModel;
import gjj.com.myapp.model.Tutor;

/**
 * Created by 高娟娟 on 2017/3/20.
 void setPresent(T present);
 */

public interface LoginView extends BaseView {
    void loginSuccess(Tutor tutor);
//    void loginSuccess(String tutor);

    void loginFail(String msg);
}
