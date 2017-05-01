package gjj.com.myapp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import gjj.com.myapp.HomeActivity;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.baseframework.retrofit.ApiStores;
import gjj.com.myapp.presenter.LoginPresenter;
import gjj.com.myapp.model.Tutor;
import gjj.com.myapp.model.User;
import gjj.com.myapp.utils.KeyBoardHelper;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.LoginView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LoginActivity extends MvpActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.username_et)
    EditText mUsernameEt;
    @BindView(R.id.password_et)
    EditText mPasswordEt;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.layout_content)
    LinearLayout mLayoutContent;
    @BindView(R.id.layout_bottom)
    LinearLayout mLayoutBottom;


    private int bottomHeight;
    private KeyBoardHelper boardHelper;
    private Retrofit retrofit;
    private String name;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        if (SPUtil.getLoginInfoFromSP(this)) {
            jumpToHomePage();
        }
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    private void initView() {
        softKeyboardEvent();
    }

    @OnClick(R.id.login_btn)
    public void onViewClicked(View view) {
        //点击登陆按钮，隐藏软键盘
        hideSoftKeyboard(view);
        name = mUsernameEt.getText().toString().trim();
        pwd = mPasswordEt.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
            Toast.makeText(mActivity, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            mvpPresenter.loadLoginData(new User(name, pwd));
        }

//        testApi();


    }

    /**
     * 用于测试服务器接口的
     */
    private void testApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiStores.API_SERVER_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        User user = new User("020081", "020081");
        //生成对象的Service
        ApiStores loginService = retrofit.create(ApiStores.class);
        //调用方法得到Call
//        Call<String> loginCall = loginService.loadLoginData(user);
//
//        //异步执行
//        loginCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//
//                Log.d("vivi",response.message()+" "+response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//                Log.d("vivie",t.getMessage());
//                Log.d("vivie",t.toString());
//            }
//        });

    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    private void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boardHelper.onDestory();
    }

    @Override
    public void loginSuccess(Tutor tutor) {
        //将账号保存到SP中
        SPUtil.saveLoginToSP(this,name, pwd);
        jumpToHomePage();
    }

    /**
     * 跳转到主页
     */
    private void jumpToHomePage() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }

    @Override
    public void loginFail(String msg) {
        Log.d("vivie", msg+"");
    }


    /**
     * 软键盘出现时不遮挡登陆按钮
     */
    private void softKeyboardEvent() {
        boardHelper = new KeyBoardHelper(this);
        boardHelper.onCreate();
        boardHelper.setOnKeyBoardStatusChangeListener(onKeyBoardStatusChangeListener);
        mLayoutBottom.post(new Runnable() {
            @Override
            public void run() {
                bottomHeight = mLayoutBottom.getHeight();
            }
        });
    }

    //软件盘状态改变的监听
    private KeyBoardHelper.OnKeyBoardStatusChangeListener onKeyBoardStatusChangeListener =
            new KeyBoardHelper.OnKeyBoardStatusChangeListener() {

                @Override
                public void OnKeyBoardPop(int keyBoardheight) {

                    final int height = keyBoardheight;
                    if (bottomHeight > height) {
                        mLayoutBottom.setVisibility(View.GONE);
                    } else {
                        int offset = bottomHeight - height;
                        final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mLayoutContent
                                .getLayoutParams();
                        lp.topMargin = offset;
                        mLayoutContent.setLayoutParams(lp);
                    }

                }

                @Override
                public void OnKeyBoardClose(int oldKeyBoardheight) {
                    if (View.VISIBLE != mLayoutBottom.getVisibility()) {
                        mLayoutBottom.setVisibility(View.VISIBLE);
                    }
                    final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) mLayoutContent
                            .getLayoutParams();
                    if (lp.topMargin != 0) {
                        lp.topMargin = 0;
                        mLayoutContent.setLayoutParams(lp);
                    }

                }
            };

}



