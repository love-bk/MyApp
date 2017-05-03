package gjj.com.myapp.myinfo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.mvp.MvpFragment;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.presenter.MyInfoPresenter;
import gjj.com.myapp.views.MyInfoView;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends MvpFragment<MyInfoPresenter> implements MyInfoView {


    @BindView(R.id.nameTv)
    TextView mNameTv;
    @BindView(R.id.sexTv)
    TextView mSexTv;
    @BindView(R.id.worker_numTv)
    TextView mWorkerNumTv;
    @BindView(R.id.departmentTv)
    TextView mDepartmentTv;
    @BindView(R.id.mystudent_btn)
    Button mMystudentBtn;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void initData() {
        mvpPresenter.loadMyInfo();
    }

    @Override
    protected MyInfoPresenter createPresenter() {
        return new MyInfoPresenter(this);
    }



    @OnClick(R.id.mystudent_btn)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MyStudentActivity.class));
    }


    @Override
    public void showMyInfo(Tutor tutor) {
        if (tutor != null){
            mNameTv.setText(tutor.getName());
            mSexTv.setText(tutor.getSex());
            mWorkerNumTv.setText(tutor.getNo());
            mDepartmentTv.setText(tutor.getDescription());
        }
    }
}
