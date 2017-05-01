package gjj.com.myapp.presenter;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.views.HomeView;

/**
 * Created by yyz on 2017/5/1.
 */

public class HomePresenter  extends BasePresenter<HomeView>{

    public HomePresenter(HomeView homeView) {
        attachView(homeView);
    }


    public void loadProjectAndReply(String tutorId){
        mvpView.showLoading();
        addSubscription(mApiStores.loadProjectAndReply(tutorId),
                new ApiCallback<String>() {
                    @Override
                    public void onSuccess(String model) {

                        mvpView.loadSucceed(model);
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

}
