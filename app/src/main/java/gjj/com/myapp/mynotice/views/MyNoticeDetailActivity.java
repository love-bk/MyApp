package gjj.com.myapp.mynotice.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Notice;
import gjj.com.myapp.presenter.NoticePresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.TimeUtils;
import gjj.com.myapp.views.NoticeView;

public class MyNoticeDetailActivity extends MvpActivity<NoticePresenter> implements NoticeView {

    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.noticeTitle_Tv)
    TextView mNoticeTitleTv;
    @BindView(R.id.ddresseeaTv)
    TextView mDdresseeaTv;
    @BindView(R.id.addressorTv)
    TextView mAddressorTv;
    @BindView(R.id.timeTv)
    TextView mTimeTv;
    @BindView(R.id.content_tv)
    TextView mContentTv;
    @BindView(R.id.back_iv)
    ImageView mBackIv;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notice_detail);
        ButterKnife.bind(this);
        initData();
        mBackIv.setVisibility(View.VISIBLE);
    }

    @Override
    protected NoticePresenter createPresenter() {
        return new NoticePresenter(this);
    }

    private void initData() {

        Intent intent = getIntent();
        flag = intent.getStringExtra(Constants.NOTICE_FLAG);
        long id = intent.getLongExtra(Constants.NOTICE_ID, 0);
        mTitleTv.setText("通知详情");
        mvpPresenter.loadNoticeFromDB(id);
//        switch (flag) {
//            case Constants.FORWARD_MESSAGE:
//                mTitleTv.setText("通知详情");
//                break;
//            case Constants.GET_MESSAGE:
//                mTitleTv.setText("通知详情");
//                break;
//            default:
//                break;
//        }

    }

    @OnClick(R.id.back_iv)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void loadSucceed(List<Notice> notices) {
        Notice notice = notices.get(0);
        mNoticeTitleTv.setText(notice.getTitle());
        List<Addressee> addressees = notice.getAddressees();
        String addresseeStr = "";
        if (addressees !=null && addressees.size()!=0){
            for (Addressee addressee : addressees) {
                addresseeStr = addresseeStr + addressee.getName()+"、";
            }
            if (addresseeStr.contains("、")){
                addresseeStr = addresseeStr.substring(0,addresseeStr.lastIndexOf("、"));
            }
        }
        mDdresseeaTv.setText(addresseeStr);
        mAddressorTv.setText(notice.getAddressor_name());
        mTimeTv.setText(TimeUtils.formatTimeInMillis(notice.getAddressTime()));
        mContentTv.setText(notice.getContent());
    }

    @Override
    public void loadFail(String msg) {

    }
}
