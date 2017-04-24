package gjj.com.myapp.myinfo.views;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.Student;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.myinfo.adapter.MyStudentListAdapter;
import gjj.com.myapp.myinfo.presenter.MyInfoPresenter;

public class MyStudentActivity extends MvpActivity<MyInfoPresenter> implements MyInfoView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.mystudent_recyclerview)
    RecyclerView mMystudentRecyclerview;
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    private List<Student> mData = new ArrayList<>();
    private MyStudentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_student);
        ButterKnife.bind(this);

        initView();
        //测试数据
        initData();
        mMystudentRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMystudentRecyclerview.setItemAnimator(new DefaultItemAnimator());
        adapter = new MyStudentListAdapter(this, mData);
        mMystudentRecyclerview.setAdapter(adapter);
        mBackIv.setVisibility(View.VISIBLE);

    }

    private void initData() {
        mvpPresenter.loadMyStudent();
    }

    @Override
    protected MyInfoPresenter createPresenter() {
        return new MyInfoPresenter(this);
    }

    private void initView() {
        mTitleTv.setText("我的学生");
    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void showMyInfo(Tutor tutor) {
        if (tutor != null) {
            List<Student> students = tutor.getStudent();
            if (students != null){
                adapter.addList(students);
            }
        }
    }
}
