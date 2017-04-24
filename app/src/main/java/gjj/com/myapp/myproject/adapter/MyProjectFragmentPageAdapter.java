package gjj.com.myapp.myproject.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import gjj.com.myapp.myproject.views.SJLWFragment;

/**
 * Created by 高娟娟 on 2017/3/23.
 */

public class MyProjectFragmentPageAdapter extends FragmentPagerAdapter {
    public MyProjectFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return SJLWFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "论文";
                break;
            case 1:
                title = "设计";
                break;
            default:
                break;
        }
        return title;
    }
}
