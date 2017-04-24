package gjj.com.myapp.myproject.views;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import gjj.com.myapp.HomeActivity;
import gjj.com.myapp.R;
import gjj.com.myapp.myproject.adapter.MyProjectFragmentPageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {


//    @BindView(R.id.tabLayout)
//    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    public ProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        ButterKnife.bind(this, view);
        mViewPager.setAdapter(new MyProjectFragmentPageAdapter(getChildFragmentManager()));
        if (getActivity() instanceof HomeActivity){
            ((HomeActivity) getActivity()).getTabLayout().setupWithViewPager(mViewPager);
        }
        return view;
    }


}
