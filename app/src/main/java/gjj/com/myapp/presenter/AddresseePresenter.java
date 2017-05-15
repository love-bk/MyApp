package gjj.com.myapp.presenter;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import gjj.com.myapp.baseframework.mvp.BasePresenter;
import gjj.com.myapp.baseframework.retrofit.ApiCallback;
import gjj.com.myapp.dao.Addressee_Dao;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.model.ServiceAddressee;
import gjj.com.myapp.mynotice.views.NewNoticeActivity;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.views.AddresseeView;

/**
 * Created by 高娟娟 on 2017/5/14.
 */

public class AddresseePresenter extends BasePresenter<AddresseeView> {

    private  Context context;

    public AddresseePresenter(AddresseeView addresseeView) {
        attachView(addresseeView);
        if (addresseeView instanceof NewNoticeActivity){
            context = (NewNoticeActivity)addresseeView;
        }
    }


    /**
     * 获取收件人列表
     * @param tutorId
     */
    public void loadAddressees(String tutorId) {
        mvpView.showLoading();
        addSubscription(mApiStores.loadAddressees(tutorId), new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                mvpView.loadSucceed(handleAddressees(model));
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
     * 处理收件人列表数据
     * @param model
     * @return
     */
    private List<Addressee> handleAddressees(String model) {
        Gson gson = new Gson();
        ServiceAddressee serviceAddressee = gson.fromJson(model, ServiceAddressee.class);
        if (serviceAddressee != null){
            List<Addressee> tutors = serviceAddressee.getAllTutorInDepartment();
            List<Addressee> students = serviceAddressee.getMyStudent();
            List<Addressee> addressees = new ArrayList<>();
            if (tutors!=null&&tutors.size()!=0){
                for (Addressee addressee : tutors) {
                    addressee.setAddresseeType(Constants.TUTOR_TYPE);
                    addressees.add(addressee);
                }
            }
            if (students!=null&&students.size()!=0){
                for (Addressee addressee : students) {
                    addressee.setAddresseeType(Constants.STUDENT_TYPE);
                    addressees.add(addressee);
                }
            }
            if (addressees.size() != 0){
                for (Addressee addressee : addressees) {
                    Addressee_Dao.getInstance(context).insert(addressee);
                }
                return addressees;
            }
        }
        return null;
    }
    /*
    获取本地收件人列表
     */
    public void loadAddresseesFromDB(){
        List<Addressee> allAddressees = Addressee_Dao.getInstance(context).getAllAddressees();
        mvpView.loadSucceed(allAddressees);
    }

    /**
     * 发送通知
     * @param notice
     */
    public void sendNotice(Notice notice) {
        mvpView.showLoading();
        addSubscription(mApiStores.sendNotice(notice), new ApiCallback<String>() {
            @Override
            public void onSuccess(String model) {
                mvpView.sendSucceed(model);
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
