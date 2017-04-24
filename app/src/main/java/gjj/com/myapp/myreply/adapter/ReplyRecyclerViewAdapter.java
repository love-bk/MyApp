package gjj.com.myapp.myreply.adapter;

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
import gjj.com.myapp.myproject.adapter.SJLWRecyclerViewAdapter;

/**
 * Created by 高娟娟 on 2017/4/4.
 */

public class ReplyRecyclerViewAdapter extends RecyclerView.Adapter<ReplyRecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;


    public ReplyRecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mData = list;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ReplyRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.myreply_item, parent, false);
        return new ReplyRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReplyRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.setData(mData.get(position),position);
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.myreply_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position); // 2
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
        @BindView(R.id.serial_number_tv)
        TextView mSerialNumberTv;
        @BindView(R.id.teamName_tv)
        TextView mTeamNameTv;
        @BindView(R.id.replyLeader_tv)
        TextView mReplyLeaderTv;
        @BindView(R.id.myreply_item)
        LinearLayout myreply_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(String s, int position) {
            mTeamNameTv.setText(mData.get(position));
            mSerialNumberTv.setText(String.valueOf(position + 1));
            mReplyLeaderTv.setText("组长"+String.valueOf(position + 1));

        }
    }

}
