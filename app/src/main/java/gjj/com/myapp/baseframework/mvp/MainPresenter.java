package gjj.com.myapp.baseframework.mvp;

import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.login.presenter.*;
import gjj.com.myapp.model.MainModel;

/**
 * Created by 高娟娟 on 2017/4/7.
 */

public class MainPresenter extends BasePresenter<MainView> {
    public MainPresenter(MainView view) {
        attachView(view);
    }

//    public void loadDataByRetrofitRxjava(String cityId) {
//        mvpView.showLoading();
//        addSubscription(mApiStores.loadDataByRetrofitRxjava(cityId),
//                new ApiCallback<MainModel>() {
//                    @Override
//                    public void onSuccess(MainModel model) {
//                        mvpView.getDataSuccess(model);
//                    }
//
//                    @Override
//                    public void onFailure(String msg) {
//                        mvpView.getDataFail(msg);
//                    }
//
//
//                    @Override
//                    public void onFinish() {
//                        mvpView.hideLoading();
//                    }
//
//                });
//    }

}
