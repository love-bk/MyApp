package gjj.com.myapp.myinfo.adapter;

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
import gjj.com.myapp.model.Student;
import gjj.com.myapp.myproject.adapter.ProjectRecyclerViewAdapter;

/**
 * Created by 高娟娟 on 2017/4/3.
 */

public class MyStudentListAdapter extends RecyclerView.Adapter<MyStudentListAdapter.MyViewHolder> {

    private Context mContext;
    private List<Student> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private ProjectRecyclerViewAdapter.OnItemClickListener mOnItemClickListener;


    public MyStudentListAdapter(Context context) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(ProjectRecyclerViewAdapter.OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.mystudent_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.setData(mData.get(position), position);
        //判断是否设置了监听器
        if (mOnItemClickListener != null) {
            //为ItemView设置监听器
            holder.mystudent_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    mOnItemClickListener.onItemClick(holder.itemView, position); // 2
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addList(List<Student> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_tv)
        TextView mNameTv;
        @BindView(R.id.phonenumber_tv)
        TextView mPhonenumberTv;
        @BindView(R.id.serial_number_tv)
        TextView mSerialNumberTv;
        @BindView(R.id.mystudent_item)
        LinearLayout mystudent_item;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(Student student, int position) {
//            mNameTv.setText(s+position);
//            mPhonenumberTv.setText(position+"0000000000");
//            mSerialNumberTv.setText(String.valueOf(position+1));
            mNameTv.setText(student.getName());
            mPhonenumberTv.setText(student.getContact());
            mSerialNumberTv.setText(String.valueOf(position+1));
        }
    }

}

