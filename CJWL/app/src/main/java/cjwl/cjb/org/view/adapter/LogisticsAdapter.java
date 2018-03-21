package cjwl.cjb.org.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cjwl.cjb.org.R;
import cjwl.cjb.org.entity.LogisticsInfo;

/**
 * 物流信息adapter
 * Created by jimbai on 2018/3/21.
 */

public class LogisticsAdapter extends BaseAdapter{

    private Context mContext;
    private List<LogisticsInfo> logisticsInfos;

    public LogisticsAdapter(Context context, List<LogisticsInfo> list){
        this.mContext=context;
        this.logisticsInfos=list;

    }
    @Override
    public int getCount() {
        return logisticsInfos.size();
    }

    @Override
    public LogisticsInfo getItem(int i) {
        return logisticsInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_logistics,null);
            holder=new ViewHolder();
            holder.tvFromName=view.findViewById(R.id.tv_item_from_name);
            holder.tvFromAdd=view.findViewById(R.id.tv_item_from_address);
            holder.tvToName=view.findViewById(R.id.tv_item_to_name);
            holder.tvToAdd=view.findViewById(R.id.tv_item_to_address);
            view.setTag(holder);
        }
        else {
            holder= (ViewHolder) view.getTag();
        }
        LogisticsInfo info=getItem(i);
        holder.tvFromName.setText(info.getSenderInfo().getName());
        holder.tvFromAdd.setText(info.getSenderInfo().getAddress());
        holder.tvToName.setText(info.getReceiverInfo().getName());
        holder.tvToAdd.setText(info.getReceiverInfo().getAddress());
        return view;
    }

    public class ViewHolder{
          private TextView tvFromName;
          private TextView tvFromAdd;
          private TextView tvToName;
          private TextView tvToAdd;
    }
}
