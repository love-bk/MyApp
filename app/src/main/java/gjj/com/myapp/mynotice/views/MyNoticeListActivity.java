package gjj.com.myapp.mynotice.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.mynotice.adapter.MyNoticeListAdapter;
import gjj.com.myapp.presenter.NoticePresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.NoticeView;

public class MyNoticeListActivity extends MvpActivity<NoticePresenter> implements MyNoticeListAdapter.OnItemClickListener,NoticeView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.mynotice_recyclerview)
    RecyclerView mMynoticeRecyclerview;
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    private String flag;
    private MyNoticeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notice_list);
        ButterKnife.bind(this);
        //测试数据
        initData();
        mMynoticeRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMynoticeRecyclerview.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MyNoticeListAdapter(this,flag);
        mAdapter.setOnItemClickListener(this);
        mMynoticeRecyclerview.setAdapter(mAdapter);
        mBackIv.setVisibility(View.VISIBLE);

    }

    @Override
    protected NoticePresenter createPresenter() {
        return new NoticePresenter(this);
    }


    private void initData() {

        flag = getIntent().getStringExtra(Constants.NOTICE_FLAG);
        switch (flag) {
            case Constants.FORWARD_MESSAGE:
                mTitleTv.setText("我发送的通知");
                mvpPresenter.loadMyReleasedNotices(String.valueOf(SPUtil.getTutorIdfromSP(this)));
                break;
            case Constants.GET_MESSAGE:
                mTitleTv.setText("我收到的通知");
                mvpPresenter.loadMyReceivedNotices(String.valueOf(SPUtil.getTutorIdfromSP(this)));
                break;
            default:
                break;
        }
    }

    /**
     * 条目的点击事件
     *
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(View view, int position, String flag) {
        Intent intent = new Intent(MyNoticeListActivity.this, MyNoticeDetailActivity.class);
        intent.putExtra(Constants.NOTICE_FLAG, flag);
        startActivity(intent);
    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void loadSucceed(List<Notice> notices) {
        mAdapter.addList(notices);
    }

    @Override
    public void loadFail(String msg) {
        Toast.makeText(mActivity, "请求失败："+msg, Toast.LENGTH_SHORT).show();
    }
}
