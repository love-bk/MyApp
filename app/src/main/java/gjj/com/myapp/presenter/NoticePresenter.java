package gjj.com.myapp.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.dao.Addressee_Dao;
import gjj.com.myapp.dao.Notice_Dao;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.model.ServiceAddressee;
import gjj.com.myapp.mynotice.views.MyNoticeListActivity;
import gjj.com.myapp.mynotice.views.NewNoticeActivity;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.views.NoticeView;

/**
 * Created by yyz on 2017/5/10.
 */

public class NoticePresenter extends BasePresenter<NoticeView> {

    private  Context context;

    public NoticePresenter(NoticeView noticeView) {
        attachView(noticeView);
        if (noticeView instanceof MyNoticeListActivity){
            this.context = (MyNoticeListActivity)noticeView;
        }else {

        }



    }

    /**
     * 加载我收到的通知
     * @param tutorId
     */
    public void loadMyReceivedNotices(String tutorId) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadMyReceivedNotices(tutorId), new ApiCallback<String>() {
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

    /**
     * 加载我发布的通知
     * @param tutorId
     */
    public void loadMyReleasedNotices(String tutorId) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadMyReleasedNotices(tutorId), new ApiCallback<String>() {
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
        Notice_Dao.getInstance(context).insertNotices(notices);
        return notices;
    }

}
