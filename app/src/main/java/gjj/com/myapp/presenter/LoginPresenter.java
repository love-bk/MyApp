package gjj.com.myapp.presenter;


import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.login.LoginActivity;
import gjj.com.myapp.views.LoginView;
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
//                        Gson gson = new Gson();
//                        gson.fromJson(tutor, Tutor.class)
//                        mvpView.loginSuccess();
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
