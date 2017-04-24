package gjj.com.myapp.baseframework.mvp;

import gjj.com.myapp.model.MainModel;

/**
 * Created by 高娟娟 on 2017/4/7.
 */

public interface MainView extends BaseView{
    void getDataSuccess(MainModel model);

    void getDataFail(String msg);

}
