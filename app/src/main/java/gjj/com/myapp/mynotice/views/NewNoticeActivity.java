package gjj.com.myapp.mynotice.views;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.mynotice.adapter.AddresseeAdapter;
import gjj.com.myapp.presenter.AddresseePresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.utils.TimeUtils;
import gjj.com.myapp.views.AddresseeView;

public class NewNoticeActivity extends MvpActivity<AddresseePresenter> implements AddresseeView {

    @BindView(R.id.back_iv)
    ImageView mBackIv;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.noticeTitle_et)
    EditText mNoticeTitleEt;
    @BindView(R.id.ddresseeaTv)
    TextView mDdresseeaTv;
    @BindView(R.id.addressorTv)
    TextView mAddressorTv;
    @BindView(R.id.timeTv)
    TextView mTimeTv;
    @BindView(R.id.content_et)
    EditText mContentEt;
    @BindView(R.id.send)
    Button mSend;
    private List<Addressee> studentList = new ArrayList<>();
    private List<Addressee> tutorList = new ArrayList<>();
    private RecyclerView mStudentRecycler;
    private RecyclerView mTutorRecycler;
    private AddresseeAdapter mStudentAdapter;
    private AddresseeAdapter mTutorAdapter;
    private AlertDialog dlg;
    private List<Addressee> mAddressees = new ArrayList<>();
    private long noticeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_notice);
        ButterKnife.bind(this);
        initWidget();
        initData();
    }

    private void initWidget() {
        mBackIv.setVisibility(View.VISIBLE);
        mTitleTv.setText("发送通知");
        mAddressorTv.setText(SPUtil.getValueBykey(this,Constants.ACOUNTNAME));
        noticeTime = System.currentTimeMillis();
        mTimeTv.setText(TimeUtils.formatTimeInMillis(noticeTime));
    }

    @Override
    protected AddresseePresenter createPresenter() {
        return new AddresseePresenter(this);
    }

    private void initData() {
        mvpPresenter.loadAddresseesFromDB();
    }


    @OnClick({R.id.ddresseeaTv, R.id.send, R.id.back_iv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ddresseeaTv:
                //请选则收件人
                //弹出自定义对话框
                initCustomDialog();
                break;
            case R.id.send:
                hideSoftKeyboard(view);
                initNotice();
                break;
            case R.id.back_iv:
                onBackPressed();
                break;

        }
    }

    private void initNotice() {
        Notice notice = new Notice();
        String noticeTitle = mNoticeTitleEt.getText().toString();
        String content = mContentEt.getText().toString();
        String addressees = mDdresseeaTv.getText().toString();
        String addressor = mAddressorTv.getText().toString();
//        String time = mTimeTv.getText().toString();
        notice.setAddressor_name(addressor);
        notice.setAddressor_id(SPUtil.getTutorIdfromSP(this));
        notice.setAddressTime(noticeTime);
        if (TextUtils.isEmpty(noticeTitle)){
            Toast.makeText(mActivity, "通知标题不能为空", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(addressees)){
            Toast.makeText(mActivity, "请选择收件人", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(content)){
            Toast.makeText(mActivity, "通知内容不能为空", Toast.LENGTH_SHORT).show();
        }else {
            notice.setTitle(noticeTitle);
//            notice.setAddressees(mAddressees);
            notice.setContent(content);
            mvpPresenter.sendNotice(notice);
        }
    }

    private void initCustomDialog() {
        if (dlg == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View layout = inflater.inflate(R.layout.custom_dialog_view, null);//获取自定义布局
            builder.setView(layout);
            builder.setTitle("请选择收件人");//设置标题内容
            mStudentAdapter = new AddresseeAdapter(this,studentList);
            mStudentRecycler = (RecyclerView) layout.findViewById(R.id.student_recyclerview);
            mStudentRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mStudentRecycler.setAdapter(mStudentAdapter);
            mTutorAdapter = new AddresseeAdapter(this,tutorList);
            mTutorRecycler = (RecyclerView) layout.findViewById(R.id.tutor_recyclerview);
            mTutorRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mTutorRecycler.setAdapter(mTutorAdapter);
            //确认按钮
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    List<Addressee> studentCache = mStudentAdapter.getCache();
                    List<Addressee> tutorCache = mTutorAdapter.getCache();
                    String addressees = "";
                    if (studentCache != null && studentCache.size()!=0){
                        for (Addressee addressee : studentCache) {
                            addressees = addressees+addressee.getName()+"、";
                            mAddressees.add(addressee);
                        }
                    }
                    if (tutorCache != null && tutorCache.size() != 0){
                        for (Addressee addressee : tutorCache) {
                            addressees = addressees+addressee.getName()+"、";
                            mAddressees.add(addressee);
                        }
                    }
                    if (addressees.contains("、")){
                        addressees = addressees.substring(0,addressees.lastIndexOf("、"));
                    }
                    mDdresseeaTv.setText(addressees);

//                    Toast.makeText(mActivity, "好了："+studentCache.get(0).getName()+" 、 "+tutorCache.get(0).getName()+" 、 "+tutorCache.get(1).getName()+" 、 "+tutorCache.get(2).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            //取消
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                }
            });
            dlg = builder.create();
        }
        dlg.show();
    }

    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void loadSucceed(List<Addressee> addressees) {
        if (addressees != null && addressees.size() !=0){
            for (Addressee addressee : addressees) {
                if (Constants.STUDENT_TYPE.equals(addressee.getAddresseeType())){
                    studentList.add(addressee);
                }else {
                    tutorList.add(addressee);
                }
            }
        }else {
            mvpPresenter.loadAddressees(String.valueOf(SPUtil.getTutorIdfromSP(this)));
        }
    }

    @Override
    public void sendSucceed(String model) {
        Toast.makeText(mActivity, "通知发送成功:"+model, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFail(String msg) {
        Toast.makeText(mActivity, "加载数据失败"+msg, Toast.LENGTH_SHORT).show();
    }

}
