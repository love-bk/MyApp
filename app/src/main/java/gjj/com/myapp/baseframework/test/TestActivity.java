package gjj.com.myapp.baseframework.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.mvp.MainPresenter;
import gjj.com.myapp.baseframework.mvp.MainView;
import gjj.com.myapp.baseframework.mvp.MvpActivity;
import gjj.com.myapp.model.MainModel;

public class TestActivity extends MvpActivity<MainPresenter> implements MainView {

    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initToolBarAsHome("MVP+Retrofit+Rxjava");
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }


    @Override
    public void getDataSuccess(MainModel model) {
        //接口成功回调
        dataSuccess(model);
    }

    @Override
    public void getDataFail(String msg) {
        toastShow("网络不给力");

    }


//    @OnClick({R.id.button2})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.button2:
//                //请求接口
//                mvpPresenter.loadDataByRetrofitRxjava("101310222");
//                break;
//        }
//    }

    /**
     * 展示数据
     * @param model
     */
    private void dataSuccess(MainModel model) {
        MainModel.WeatherinfoBean weatherinfo = model.getWeatherinfo();
        String showData = getResources().getString(R.string.city) + weatherinfo.getCity()
                + getResources().getString(R.string.wd) + weatherinfo.getWD()
                + getResources().getString(R.string.ws) + weatherinfo.getWS()
                + getResources().getString(R.string.time) + weatherinfo.getTime();
        text.setText(showData);
    }
}
