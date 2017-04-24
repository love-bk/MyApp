package gjj.com.myapp.myinfo.presenter;

import android.content.Context;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.InputStream;

import gjj.com.myapp.MyApplication;
import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.login.views.LoginActivity;
import gjj.com.myapp.login.views.LoginView;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.model.User;
import gjj.com.myapp.myinfo.views.InfoFragment;
import gjj.com.myapp.myinfo.views.MyInfoView;
import gjj.com.myapp.myinfo.views.MyStudentActivity;
import gjj.com.myapp.utils.ACache;
import gjj.com.myapp.utils.Constants;

/**
 * Created by 高娟娟 on 2017/4/24.
 */

public class MyInfoPresenter extends BasePresenter<MyInfoView> {

    public MyInfoPresenter(MyInfoView view) {
        attachView(view);
    }

    public void loadMyInfo() {
       loadData();
    }


    public void loadMyStudent() {
        //从缓存中获取数据
        loadData();
    }


    //加载数据
    private void loadData(){
        Context context = null;
        if (mvpView instanceof InfoFragment){
            context = ((InfoFragment)mvpView).mActivity;
        }else {
            context = ((MyStudentActivity) mvpView);
        }
        String tutorJson = ACache.get(context).getAsString(Constants.TUTOR);
        Gson gson = new Gson();
        Tutor tutor = gson.fromJson(tutorJson, Tutor.class);
        tutor.setDescription(tutor.getDepartment().getDescription());
        mvpView.showMyInfo(tutor);
    }

}
