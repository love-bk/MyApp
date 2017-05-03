package gjj.com.myapp.presenter;

import android.content.Context;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.dao.Tutor_Dao;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.myinfo.InfoFragment;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.MyInfoView;
import gjj.com.myapp.myinfo.MyStudentActivity;

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
        Tutor tutor = Tutor_Dao.getInstance(context).queryByTutorId(SPUtil.getTutorIdfromSP(context));
        //从数据库中读取数据
        mvpView.showMyInfo(tutor);
    }

}
