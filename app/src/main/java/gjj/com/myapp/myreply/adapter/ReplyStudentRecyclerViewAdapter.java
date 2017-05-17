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
import gjj.com.myapp.model.GraduateProject;
import gjj.com.myapp.model.ReplyGroup;
import gjj.com.myapp.model.Student;

/**
 * Created by 高娟娟 on 2017/4/4.
 */

public class ReplyStudentRecyclerViewAdapter extends RecyclerView.Adapter<ReplyStudentRecyclerViewAdapter.MyViewHolder> {
    private Context mContext;
    private List<GraduateProject> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;


    public List<GraduateProject> getData() {
        return mData;
    }

    public void setData(List<GraduateProject> data) {
        mData = data;
    }

    public ReplyStudentRecyclerViewAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position,Long projectId);
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public ReplyStudentRecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.replystudent_item, parent, false);
        return new ReplyStudentRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ReplyStudentRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.setData(mData.get(position),position);
        //判断是否设置了监听器
        if(mOnItemClickListener != null){
            //为ItemView设置监听器
            holder.replystudent_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView,position,mData.get(position).getId()); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addList(List<GraduateProject> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.serial_number_tv)
        TextView mSerialNumberTv;
        @BindView(R.id.replyState_tv)
        TextView mReamStateTv;
        @BindView(R.id.replyProject_tv)
        TextView mReplyProjectTv;
        @BindView(R.id.replyStudent_tv)
        TextView mReplyStudentTv;
         @BindView(R.id.replystudent_item)
         LinearLayout replystudent_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(GraduateProject graduateProject, int position) {
            mReplyProjectTv.setText(graduateProject.getTitle());
            mSerialNumberTv.setText(String.valueOf(position + 1));
            mReplyStudentTv.setText(graduateProject.getStudent_name().getName());
            mReamStateTv.setText("已打分");
        }
    }

}
