package cjwl.cjb.org.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cjwl.cjb.org.R;
import cjwl.cjb.org.entity.GoodsInfo;
import cjwl.cjb.org.entity.LogisticsInfo;
import cjwl.cjb.org.entity.PersonInfo;
import cjwl.cjb.org.view.adapter.LogisticsAdapter;

/**
 * 物流
 * Created by jimbai on 2018/3/21.
 */

public class LogisticsActivity extends Activity{

    private ListView listView;
    private LogisticsAdapter adapter;
    private List<LogisticsInfo> logisticsInfos=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logistics);
        initView();
        initData();
    }
    private void initView(){
        adapter=new LogisticsAdapter(LogisticsActivity.this,logisticsInfos);
        listView=findViewById(R.id.lv_logistics_hostory);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    private void initData(){
        LogisticsInfo info1=new LogisticsInfo();
        info1.setStatus(LogisticsInfo.TRANSING);
        GoodsInfo goodsInfo1=new GoodsInfo();
        goodsInfo1.setName("衣服");
        PersonInfo ps1=new PersonInfo();
        ps1.setTel("139999999");
        ps1.setAddress("辽宁省鞍山市");
        ps1.setName("老白");
        ps1.setGender(1);
        ps1.setLatitude(41.1107800000);
        ps1.setLongitude(122.9698400000);
        PersonInfo pr1=new PersonInfo();
        pr1.setTel("1397897392");
        pr1.setAddress("辽宁省沈阳市草仓路小学");
        pr1.setName("老成");
        pr1.setGender(1);
        pr1.setLatitude(41.8107400000);
        pr1.setLongitude(123.4707000000);
        info1.setGoodsInfo(goodsInfo1);
        info1.setSenderInfo(ps1);
        info1.setReceiverInfo(pr1);

        LogisticsInfo info2=new LogisticsInfo();
        info2.setStatus(LogisticsInfo.ENDTRANS);
        GoodsInfo goodsInfo2=new GoodsInfo();
        goodsInfo2.setName("衣服");
        PersonInfo ps2=new PersonInfo();
        ps2.setTel("139999999");
        ps2.setAddress("辽宁省鞍山市");
        ps2.setName("老白");
        ps2.setGender(1);
        ps2.setLatitude(41.1107800000);
        ps2.setLongitude(122.9698400000);
        PersonInfo pr2=new PersonInfo();
        pr2.setTel("1397897392");
        pr2.setAddress("辽宁省本溪市");
        pr2.setName("徐大帅");
        pr2.setGender(1);
        pr2.setLatitude(41.2951800000);
        pr2.setLongitude(123.7588000000);
        info2.setGoodsInfo(goodsInfo2);
        info2.setSenderInfo(ps2);
        info2.setReceiverInfo(pr2);
        logisticsInfos.add(info1);
        logisticsInfos.add(info2);
        adapter.notifyDataSetChanged();
    }
}
