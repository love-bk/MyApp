package gjj.com.myapp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.views.NoticeView;

/**
 * Created by yyz on 2017/5/10.
 */

public class NoticePresenter extends BasePresenter<NoticeView> {

    public NoticePresenter(NoticeView noticeView) {
        attachView(noticeView);

    }

    public void loadProjectData(String tutorId) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadNotice(tutorId), new ApiCallback<String>() {
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

    private List<Notice> handleData(String model) {
        Gson gson = new Gson();
        List<Notice> notices = gson.fromJson(model, new TypeToken<List<Notice>>() {
        }.getType());
        //保存到数据库中
        return notices;
    }

}
