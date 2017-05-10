package gjj.com.myapp.views;

import java.util.List;

import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.ReplyGroup;

/**
 * Created by yyz on 2017/5/10.
 */

public interface ReplyView extends BaseView {
    void loadSucceed(List<ReplyGroup> replyGroups);

    void loadFail(String msg);
}
