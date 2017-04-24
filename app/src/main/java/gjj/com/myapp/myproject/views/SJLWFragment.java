package gjj.com.myapp.myproject.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gjj.com.myapp.R;
import gjj.com.myapp.myproject.adapter.SJLWRecyclerViewAdapter;
import gjj.com.myapp.myproject.ProjectDetailActivity;
import gjj.com.myapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class SJLWFragment extends Fragment {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> mData = new ArrayList<>();
    public SJLWFragment() {
        // Required empty public constructor
    }

    public static SJLWFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt(Constants.SUBJECT_CATEGORY, position);
        SJLWFragment fragment = new SJLWFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sjlw, container, false);
        ButterKnife.bind(this, view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SJLWRecyclerViewAdapter mAdapter = new SJLWRecyclerViewAdapter(getActivity(), mData);
        mAdapter.setOnItemClickListener(new SJLWRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getActivity(), ProjectDetailActivity.class));
                Toast.makeText(getActivity(), "点击了第"+position+"条", Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        int categoryFlag = getArguments().getInt(Constants.SUBJECT_CATEGORY, -1);
        if (categoryFlag != -1){
            switch (categoryFlag){
                case 0://设计
                    initData(1);
                    mAdapter.addList(mData);
                    break;
                case 1://论文
                    initData(2);
                    mAdapter.addList(mData);
                    break;
                default:
                    break;
            }
        }
        return view;
    }

    private void initData(int pager) {
        mData = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            mData.add("pager" + pager + " 第" + i + "个item");
        }
    }
}
