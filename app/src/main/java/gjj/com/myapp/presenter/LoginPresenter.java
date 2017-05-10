package gjj.com.myapp.presenter;


import com.google.gson.Gson;

import gjj.com.myapp.MyApplication;
import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.dao.Student_Dao;
import gjj.com.myapp.dao.Tutor_Dao;
import gjj.com.myapp.greendao.gen.StudentDao;
import gjj.com.myapp.greendao.gen.TutorDao;
import gjj.com.myapp.login.LoginActivity;
import gjj.com.myapp.model.Student;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.utils.SPUtil;
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

    public void loadLoginData(final User user) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadLoginData(user),
                new ApiCallback<String>() {
                    @Override
                    public void onSuccess(String tutorStr) {
                        mvpView.loginSuccess(handleData(tutorStr,user));
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

    /**
     * 处理登陆成功返回来的tutor数据
     * @param tutorStr  返回来的数据
     * @param user      登陆接口的参数
     * @return
     */
    private Tutor handleData(String tutorStr,User user) {
        //解析数据
        Gson gson = new Gson();
        Tutor tutor = gson.fromJson(tutorStr, Tutor.class);
        if (tutor.getDepartment() != null) {
            tutor.setDescription(tutor.getDepartment().getDescription());
        }
        //将获取到的数据添加到数据库中
        Tutor_Dao.getInstance((LoginActivity) mvpView).insert(tutor);
        if (tutor.getStudentList() != null && tutor.getStudentList().size() !=0){
            for (Student student : tutor.getStudentList()) {
                student.setTutorId(tutor.getId());
                if (student.getStudentClass()!=null){
                    student.setClassDescription(student.getStudentClass().getDescription());
                }
                if (student.getMajor()!=null){
                    student.setMajorDecription(student.getMajor().getDescription());
                }
            }
            Student_Dao.getInstance((LoginActivity) mvpView).insertStudentList(tutor.getStudentList());
        }
        //将账号保存到SP中
        SPUtil.saveLoginToSP(((LoginActivity) mvpView),user.getUsername(), user.getUsername(),tutor.getId());
        Constants.tutorId = tutor.getId();
        return tutor;
    }


}
