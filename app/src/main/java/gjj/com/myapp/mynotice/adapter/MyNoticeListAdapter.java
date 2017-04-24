package gjj.com.myapp.mynotice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gjj.com.myapp.R;
import gjj.com.myapp.utils.Constants;

/**
 * Created by 高娟娟 on 2017/4/3.
 */

public class MyNoticeListAdapter extends RecyclerView.Adapter<MyNoticeListAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private String flag;
    private MyNoticeListAdapter.OnItemClickListener mOnItemClickListener;


    public MyNoticeListAdapter(Context context, List<String> list,String flag) {
        this.mContext = context;
        this.mData = list;
        this.flag = flag;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position,String flag);
    }

    public void setOnItemClickListener(MyNoticeListAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.mynotice_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.setData(mData.get(position), position);
        //判断是否设置了监听器
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.mynotice_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position,flag); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addList(List<String> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.mynotice_title_tv)
        TextView mNameTv;
        @BindView(R.id.serial_number_tv)
        TextView mSerialNumberTv;
        @BindView(R.id.mynotice_item)
        LinearLayout mynotice_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(String s, int position) {
            mNameTv.setText(s+position);
            switch (flag){
                case Constants.FORWARD_MESSAGE:
                    mSerialNumberTv.setText("发");
                    mSerialNumberTv.setBackgroundResource(R.mipmap.notice_bg);
                    break;
                case Constants.GET_MESSAGE:
                    mSerialNumberTv.setBackgroundResource(R.mipmap.notice_logo);
                    mSerialNumberTv.setText("收");
                    break;
                default:
                    break;
            }
        }
    }

}

