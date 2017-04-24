package gjj.com.myapp.login.presenter;


import android.content.Context;

import com.google.gson.Gson;

import gjj.com.myapp.MyApplication;
import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.greendao.gen.TutorDao;
import gjj.com.myapp.login.views.LoginActivity;
import gjj.com.myapp.login.views.LoginView;
import gjj.com.myapp.model.MainModel;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.model.User;
import gjj.com.myapp.utils.ACache;
import gjj.com.myapp.utils.Constants;

/**
 * Created by 高娟娟 on 2017/3/20.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        attachView(view);
    }

    public void loadLoginData(User user) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadLoginData(user),
                new ApiCallback<String>() {
                    @Override
                    public void onSuccess(String tutor) {
//                        tutor.setDescription(tutor.getDepartment().getDescription());
//                        TutorDao tutorDao = MyApplication.getInstances().getDaoSession().getTutorDao();
//                        tutorDao.insert(tutor);
                        //将获取到的数据添加到缓存
                        ACache.get((LoginActivity)mvpView).put(Constants.TUTOR,tutor);
                        Gson gson = new Gson();
                        mvpView.loginSuccess(gson.fromJson(tutor, Tutor.class));
                    }

                    @Override
                    public void onFailure(String msg) {
                        mvpView.loginFail(msg);
                    }
                    @Override
                    public void onFinish() {
                        mvpView.hideLoading();
                    }

                });
    }



}
