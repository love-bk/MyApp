package gjj.com.myapp;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gjj.com.myapp.baseframework.base.BaseActivity;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.myinfo.views.InfoFragment;
import gjj.com.myapp.mynotice.views.NoticeFragment;
import gjj.com.myapp.myproject.adapter.MainFragmentPageAdapter;
import gjj.com.myapp.myproject.views.ProjectFragment;
import gjj.com.myapp.myreply.views.ReplyFragment;
import gjj.com.myapp.presenter.HomePresenter;
import gjj.com.myapp.views.HomeView;

public class HomeActivity extends MvpActivity<HomePresenter> implements HomeView {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.title_tv)
    TextView mTitleTv;
    @BindView(R.id.setting_iv)
    ImageView settingIv;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        ProjectFragment projectFragment = new ProjectFragment();
        ReplyFragment replyFragment = new ReplyFragment();
        NoticeFragment noticeFragment = new NoticeFragment();
        InfoFragment infoFragment = new InfoFragment();
        mFragmentList.add(projectFragment);
        mFragmentList.add(replyFragment);
        mFragmentList.add(noticeFragment);
        mFragmentList.add(infoFragment);
        mViewPager.setAdapter(new MainFragmentPageAdapter(getSupportFragmentManager(), mFragmentList));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.my_subject);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.defense_rating);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.my_notice);
                        break;
                    case 3:
                        mRadioGroup.check(R.id.my_info);
                        break;
                    default:
                        break;
                }
                initTitle(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.my_subject:
                        /**
                         * setCurrentItem第二个参数控制页面切换动画
                         * true:打开/false:关闭
                         */
                        mViewPager.setCurrentItem(0);
                        initTitle(0);
                        break;
                    case R.id.defense_rating:
                        mViewPager.setCurrentItem(1);
                        initTitle(1);
                        break;
                    case R.id.my_notice:
                        mViewPager.setCurrentItem(2);
                        initTitle(2);
                        break;
                    case R.id.my_info:
                        mViewPager.setCurrentItem(3);
                        initTitle(3);
                        break;
                    default:
                        break;
                }
            }
        });
        initTitle(mViewPager.getCurrentItem());

        initData();
    }

    private void initData() {
        mvpPresenter.loadProjectAndReply("1");
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    private void initTitle(int currentItem) {
        if (0 == currentItem) {
            settingIv.setVisibility(View.VISIBLE);
            mTitleTv.setText("我的课题");
        } else {
            settingIv.setVisibility(View.GONE);
            switch (currentItem) {
                case 1:
                    mTitleTv.setText("我所在小组");
                    break;
                case 2:
                    mTitleTv.setText("我的通知");
                    break;
                case 3:
                    mTitleTv.setText("我的信息");
                    break;

            }
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(HomeActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void loadSucceed(String projectAndReply) {
        Toast.makeText(mActivity, "加载成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadFail(String msg) {
        Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();

    }


}
