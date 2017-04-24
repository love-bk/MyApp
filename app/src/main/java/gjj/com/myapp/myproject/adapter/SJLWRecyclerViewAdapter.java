package gjj.com.myapp.myproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gjj.com.myapp.R;

/**
 * Created by 高娟娟 on 2017/3/24.
 */

public class SJLWRecyclerViewAdapter extends RecyclerView.Adapter<SJLWRecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<String> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;


    public SJLWRecyclerViewAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mData = list;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.setData(mData.get(position),position);
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.item_layout.setOnClickListener(new View.OnClickListener() {
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
        @BindView(R.id.tv_info)
        TextView mTvInfo;
        @BindView(R.id.mysubtitle_tv)
        TextView mSubTitle;
        @BindView(R.id.item_layout)
        RelativeLayout item_layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(String s, int position) {
            mTvInfo.setText(mData.get(position));
            mSerialNumberTv.setText(String.valueOf(position + 1));
            mSubTitle.setText("副--"+String.valueOf(position + 1));

        }
    }

}
