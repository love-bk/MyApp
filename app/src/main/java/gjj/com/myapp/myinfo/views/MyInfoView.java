package gjj.com.myapp.myinfo.views;

import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.model.Tutor;

public interface MyInfoView extends BaseView {
    void showMyInfo(Tutor tutor);
//    void loginSuccess(String tutor);

}