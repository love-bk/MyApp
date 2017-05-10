package gjj.com.myapp.myproject;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import gjj.com.myapp.HomeActivity;
import gjj.com.myapp.R;
import gjj.com.myapp.baseframework.mvp.MvpFragment;
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.myproject.adapter.ProjectRecyclerViewAdapter;
import gjj.com.myapp.presenter.ProjectPresenter;
import gjj.com.myapp.utils.Constants;
import gjj.com.myapp.utils.SPUtil;
import gjj.com.myapp.views.ProjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends MvpFragment<ProjectPresenter> implements SwipeRefreshLayout.OnRefreshListener,ProjectView, HomeActivity.MenuClickCallBack {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;
    private ProjectRecyclerViewAdapter mAdapter;
    private String category = Constants.ALL;

    public ProjectFragment() {
    }

    public static ProjectFragment newInstance(int position) {

        ProjectFragment fragment = new ProjectFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);
        initWidget();
        return view;
    }

    /**
     * 初始化控件
     */
    private void initWidget() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ProjectRecyclerViewAdapter(getActivity());
        mAdapter.setOnItemClickListener(new ProjectRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position,GraduateProject project) {

                Intent intent = new Intent(getActivity(), ProjectDetailActivity.class);
                intent.putExtra(Constants.PROJECT_ID,project.getId());
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefresh.setOnRefreshListener(this);
        //为SwipeRefreshLayout设置刷新时的颜色变化，最多可以设置4种
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomeActivity){
            ((HomeActivity) context).setMenuClickCallBack(this);
        }
    }


    @Override
    protected void initData() {

    }

    @Override
    protected ProjectPresenter createPresenter() {
        return new ProjectPresenter(this);
    }


    /**
     * 下拉之后加载数据
     */
    @Override
    public void onRefresh() {
        mvpPresenter.loadProjectData(String.valueOf(SPUtil.getTutorIdfromSP(mActivity)));
    }

    /**
     * 数据加载成功
     * @param projects
     */
    @Override
    public void loadSucceed(List<GraduateProject> projects) {
        if (projects != null) {
            mAdapter.addList(projects);
        }
    }

    /**
     * 数据加载失败
     * @param msg
     */
    @Override
    public void loadFail(String msg) {
        Toast.makeText(mActivity, "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
//        mSwipeRefresh.setRefreshing(true);
    }

    /**
     * 隐藏下拉刷新的动画
     */
    @Override
    public void hideLoading() {
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void changerCategory(String category) {
        this.category = category;
        switch (category){
            case Constants.ALL:
                mvpPresenter.loadProjectFromDB(category);
                Toast.makeText(mActivity, "点击了全部", Toast.LENGTH_SHORT).show();
                break;
            case Constants.DESIGN:
                mvpPresenter.loadProjectFromDB(category);
                Toast.makeText(mActivity, "点击了设计", Toast.LENGTH_SHORT).show();
                break;
            case Constants.PAGE:
                mvpPresenter.loadProjectFromDB(category);
                Toast.makeText(mActivity, "点击了论文", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
