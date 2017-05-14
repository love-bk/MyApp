package gjj.com.myapp.mynotice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gjj.com.myapp.R;
import gjj.com.myapp.model.Addressee;
import gjj.com.myapp.model.Notice;

/**
 * Created by 高娟娟 on 2017/5/14.
 */

public class AddresseeAdapter extends RecyclerView.Adapter<AddresseeAdapter.MyAddresseeViewHolder> {

    private  LayoutInflater mInflater;
    private  Context context;
    private List<Addressee> mData = new ArrayList<>();
    private List<Addressee> mCache = new ArrayList<>();
    public AddresseeAdapter(Context context,List<Addressee> mData) {
        this.context = context;
        this.mData = mData;
        mInflater = LayoutInflater.from(context);
    }

    public List<Addressee> getCache() {
        return mCache;
    }

    @Override
    public MyAddresseeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.addressee_item, parent, false);
        return new MyAddresseeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAddresseeViewHolder holder, int position) {
        holder.setData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addList(List<Addressee> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }
    public class MyAddresseeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.addressee_name)
        TextView addresseeName;
        @BindView(R.id.checkbox)
        CheckBox checkbox;
        public MyAddresseeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setData(final Addressee addressee) {
           addresseeName.setText(addressee.getName());
           checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
               @Override
               public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                   if (b){
                       if (!mCache.contains(addressee))
                           mCache.add(addressee);
                   }else {
                       if (mCache.contains(addressee))
                           mCache.remove(addressee);
                   }
               }
           });
        }
    }
}
