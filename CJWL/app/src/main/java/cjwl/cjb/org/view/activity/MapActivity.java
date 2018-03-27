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
    LatLng p1 = new LatLng(39.993266, 116.473193);//首开广场
    LatLng p2 = new LatLng(39.917337, 116.397056);//故宫博物院
    LatLng p3 = new LatLng(39.904556, 116.427231);//北京站
    LatLng p4 = new LatLng(39.773801, 116.368984);//新三余公园(南5环)
    LatLng p5 = new LatLng(40.041986, 116.414496);//立水桥(北5环)
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()){
            case 1:
                Toast.makeText(MapActivity.this,"菜单一",Toast.LENGTH_SHORT).show();
//                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(),
//                        new AmapNaviParams(new Poi("北京站", p3, ""), null, new Poi("故宫博物院", p2, ""),
//                                AmapNaviType.DRIVER).setTheme(AmapNaviTheme.WHITE), MapActivity.this);
                AmapNaviPage.getInstance().showRouteActivity(MapActivity.this,
                        new AmapNaviParams(new Poi("北京站", p3, ""), null, new Poi("故宫博物院", p2, ""),
                                AmapNaviType.DRIVER), this);

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
