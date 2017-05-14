package gjj.com.myapp.views;

import java.util.List;

import gjj.com.myapp.baseframework.mvp.BaseView;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Notice;

/**
 * Created by 高娟娟 on 2017/5/14.
 */

public interface AddresseeView extends BaseView{
    void loadSucceed(List<Addressee> addressees);

    void loadFail(String msg);
}
