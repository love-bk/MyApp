package gjj.com.myapp.presenter;

import android.content.Context;
import android.location.Address;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.dao.Addressee_Dao;
import gjj.com.myapp.dao.Notice_Dao;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.mynotice.views.MyNoticeDetailActivity;
import gjj.com.myapp.mynotice.views.MyNoticeListActivity;
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
        }else if (noticeView instanceof MyNoticeDetailActivity){
            this.context = (MyNoticeDetailActivity)noticeView;
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
                mvpView.loadSucceed(handleData(model,Constants.GET_MESSAGE));
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
                mvpView.loadSucceed(handleData(model,Constants.FORWARD_MESSAGE));
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
     * 将报题数据保存到数据库中
     * @param model
     * @return
     */
    private List<Notice> handleData(String model,String flag) {
        Gson gson = new Gson();
        List<Notice> notices = gson.fromJson(model, new TypeToken<List<Notice>>() {
        }.getType());
        //保存到数据库中
        Notice_Dao.getInstance(context).insertNoticesByFlag(notices,flag);
        for (Notice notice : notices) {
            List<Long> addresseeIdList = notice.getAddresseeIdList();
            List<String> addresseeNameList = notice.getAddresseeNameList();
            if (addresseeIdList !=null&& addresseeIdList.size()!=0){
                for (int i = 0; i < addresseeIdList.size(); i++) {
                    Addressee addressee = new Addressee(addresseeIdList.get(i), addresseeNameList.get(i), notice.getId());
                    //将收件人发送到数据库中
                    Addressee_Dao.getInstance(context).insert(addressee);
                }
            }
        }
        return notices;
    }

    public void loadNoticeFromDB(long noticeId){
        List<Notice> notices = new ArrayList<>();
        Notice notice = Notice_Dao.getInstance(context).queryNoticeById(noticeId);
        notices.add(notice);
        mvpView.loadSucceed(notices);
    }

    //从本地加载我收到的通知
    public void loadReceiveNoticeFormDB(String flag) {
        List<Notice> notices = Notice_Dao.getInstance(context).queryNoticesByFlag(flag);
        for (Notice notice : notices) {
            List<Long> addresseeIdList = new ArrayList<>();
            List<String> addresseeNameList= new ArrayList<>();
            List<Addressee> addressees = Addressee_Dao.getInstance(context).queryAddresseeByNoticeId(notice.getId());
            if (addressees != null && addressees.size()>0){
                for (Addressee addressee : addressees) {
                    addresseeIdList.add(addressee.getId());
                    addresseeNameList.add(addressee.getName());
                }
                notice.setAddresseeNameList(addresseeNameList);
                notice.setAddresseeIdList(addresseeIdList);
            }
        }
        mvpView.loadSucceed(notices);

    }
    //从本地加载我发送的通知

}
