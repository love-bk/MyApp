package gjj.com.myapp.baseframework.mvp;

import gjj.com.myapp.baseframework.retrofit.ApiStores;
import gjj.com.myapp.baseframework.retrofit.AppClient;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.Subscriber;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 高娟娟 on 2017/4/6.
 */

public class BasePresenter<V> {
    public V mvpView;
    protected ApiStores mApiStores;
    private CompositeSubscription mCompositeSubscription;

    public void attachView(V mvpView){
        this.mvpView = mvpView;
        mApiStores = AppClient.retrofit().create(ApiStores.class);
    }

    public void detachView(){
        this.mvpView = null;
        onUnsubscribe();
    }

    //Rxjava取消注册，以避免内存泄漏
    public void onUnsubscribe() {
        if (mCompositeSubscription != null&&mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    public void addSubscription(Observable observable, Subscriber subscriber){
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }

        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
}
