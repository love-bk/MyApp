package gjj.com.myapp.views;

import java.util.List;

import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.model.Notice;

/**
 * Created by yyz on 2017/5/1.
 */

public interface NoticeView extends BaseView {

    void loadSucceed(List<Notice> notices);
//    void loginSuccess(String tutor);

    void loadFail(String msg);
}
