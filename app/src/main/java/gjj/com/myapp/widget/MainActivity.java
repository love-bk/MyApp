package gjj.com.myapp.widget;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import gjj.com.myapp.R;

public class MainActivity extends Activity {

    private LinearFragment listFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = new LinearFragment();

        getFragmentManager().beginTransaction().replace(
                R.id.fragment_container, listFragment).commit();

    }

}