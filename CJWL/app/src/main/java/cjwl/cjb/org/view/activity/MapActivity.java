package cjwl.cjb.org.view.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviTheme;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;

import cjwl.cjb.org.R;
import cjwl.cjb.org.view.fragment.LocationFragment;
import cjwl.cjb.org.view.fragment.LocationRotationFragment;
import cjwl.cjb.org.view.fragment.NaviFragment;

/**
 * 地图
 * Created by jimbai on 2018/3/18.
 */

public class MapActivity extends AppCompatActivity implements INaviInfoCallback {

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private LocationFragment locationFragment;
    private NaviFragment naviFragment;
    private LocationRotationFragment locationRotationFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();
    }

    private void initView(){
        fm=getFragmentManager();
        transaction=fm.beginTransaction();
        locationFragment=LocationFragment._getInstance();
        naviFragment=NaviFragment._getInstance();
        locationRotationFragment=LocationRotationFragment._getInstance();
        transaction.add(R.id.fl_fragment, locationRotationFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //四个参数的含义。1，group的id,2,item的id,3,是否排序，4，将要显示的内容
        menu.add(0,1,0,"个人信息");
        menu.add(0,2,0,"历史物流");
        menu.add(0,3,0,"菜单三");
        menu.add(0,4,0,"菜单四");

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Intent uin=new Intent();
                uin.setClass(MapActivity.this,UserInfoActivity.class);
                startActivity(uin);
                break;
            case 2:
                Intent intent=new Intent();
                intent.setClass(MapActivity.this,LogisticsActivity.class);
                startActivity(intent);
                break;
            case 3:
                Toast.makeText(MapActivity.this,"菜单三",Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(MapActivity.this,"菜单四",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onInitNaviFailure() {

    }

    @Override
    public void onGetNavigationText(String s) {

    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {

    }

    @Override
    public void onExitPage(int i) {

    }
}
