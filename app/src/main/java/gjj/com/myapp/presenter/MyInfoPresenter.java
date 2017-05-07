package gjj.com.myapp.presenter;

import android.content.Context;

import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.dao.Student_Dao;
import gjj.com.myapp.dao.Tutor_Dao;
import gjj.com.myapp.model.Student;
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
    private Context context = null;

    public MyInfoPresenter(MyInfoView view) {
        attachView(view);
        if (mvpView instanceof InfoFragment){
            context = ((InfoFragment)mvpView).mActivity;
        }else {
            context = ((MyStudentActivity) mvpView);
        }
    }

    public void loadMyInfo() {
        Tutor tutor = Tutor_Dao.getInstance(context).queryByTutorId(SPUtil.getTutorIdfromSP(context));
        //从数据库中读取数据
        mvpView.showMyInfo(tutor);
    }


    public void loadMyStudent() {
        List<Student> students = Student_Dao.getInstance(context).queryDatasByTutorId(SPUtil.getTutorIdfromSP(context));
        Tutor tutor = new Tutor();
        tutor.setStudent(students);
        mvpView.showMyInfo(tutor);
    }


}
