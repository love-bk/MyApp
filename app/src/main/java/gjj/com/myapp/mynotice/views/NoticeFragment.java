package gjj.com.myapp.mynotice.views;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gjj.com.myapp.R;
import gjj.com.myapp.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoticeFragment extends Fragment {


    @BindView(R.id.new_message_rl)
    RelativeLayout mNewMessageRl;
    @BindView(R.id.forward_message_rl)
    RelativeLayout mForwardMessageRl;
    @BindView(R.id.get_message_rl)
    RelativeLayout mGetMessageRl;

    public NoticeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.new_message_rl, R.id.forward_message_rl, R.id.get_message_rl})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.new_message_rl:
                intent = new Intent(getActivity(), NewNoticeActivity.class);
                break;
            case R.id.forward_message_rl:
                intent = new Intent(getActivity(), MyNoticeListActivity.class);
                intent.putExtra(Constants.NOTICE_FLAG,Constants.FORWARD_MESSAGE);
                break;
            case R.id.get_message_rl:
                intent = new Intent(getActivity(), MyNoticeListActivity.class);
                intent.putExtra(Constants.NOTICE_FLAG,Constants.GET_MESSAGE);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
