package gjj.com.myapp.mynotice.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.mynotice.adapter.MyNoticeListAdapter;
import gjj.com.myapp.utils.Constants;

public class MyNoticeListActivity extends BaseActivity implements MyNoticeListAdapter.OnItemClickListener {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.mynotice_recyclerview)
    RecyclerView mMynoticeRecyclerview;
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    private ArrayList<String> mData;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notice_list);
        ButterKnife.bind(this);
        //测试数据
        initData();
        mMynoticeRecyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mMynoticeRecyclerview.setItemAnimator(new DefaultItemAnimator());
        MyNoticeListAdapter adapter = new MyNoticeListAdapter(this, mData, flag);
        adapter.setOnItemClickListener(this);
        mMynoticeRecyclerview.setAdapter(adapter);
        mBackIv.setVisibility(View.VISIBLE);

    }


    private void initData() {

        flag = getIntent().getStringExtra(Constants.NOTICE_FLAG);
        switch (flag) {
            case Constants.FORWARD_MESSAGE:
                mTitleTv.setText("我发送的通知");
                break;
            case Constants.GET_MESSAGE:
                mTitleTv.setText("我收到的通知");
                break;
            default:
                break;
        }
        mData = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            mData.add(" 第" + i + "个item");
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
}
