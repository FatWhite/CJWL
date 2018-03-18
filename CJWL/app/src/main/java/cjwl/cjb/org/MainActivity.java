package cjwl.cjb.org;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MapView mMapView = null;
    private AMap aMap;
    private AMapLocationClient mapLocationClient=null;
    private AMapLocationClientOption locationClientOption=null;
    private double lat,lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMapView =findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mapLocationClient=new AMapLocationClient(this);
        mapLocationClient.setLocationListener(locationListener);
        initMap();
    }

    private void initMap(){
        if (aMap==null){
            aMap=mMapView.getMap();
            aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
            aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
            // 自定义系统定位蓝点
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            // 自定义定位蓝点图标
            myLocationStyle.myLocationIcon(
                    BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
            // 自定义精度范围的圆形边框颜色
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
            // 自定义精度范围的圆形边框宽度
            myLocationStyle.strokeWidth(0);
            // 设置圆形的填充颜色
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
            // 将自定义的 myLocationStyle 对象添加到地图上
            aMap.setMyLocationStyle(myLocationStyle);
            //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
//            aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 卫星地图模式
        }
        setUpMap();
    }
    private void setUpMap(){
        locationClientOption=new AMapLocationClientOption();
        locationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationClientOption.setNeedAddress(true);
        locationClientOption.setOnceLocation(true);
//        locationClientOption.setWifiScan(false);
//        locationClientOption.setMockEnable(false);//模拟位置
//        locationClientOption.setInterval(2000);//定位间隔
        mapLocationClient.setLocationOption(locationClientOption);
        mapLocationClient.startLocation();
    }


    public AMapLocationListener locationListener=new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null){
                aMapLocation.getLocationType();//定位来源
                aMapLocation.getLatitude();//纬度
                aMapLocation.getLongitude();//经度
                aMapLocation.getAccuracy();//精度
                SimpleDateFormat ds=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date=new Date(aMapLocation.getTime());
                ds.format(date);//定位时间
                aMapLocation.getAddress();
                aMapLocation.getCountry();
                aMapLocation.getProvider();
                aMapLocation.getCity();
                aMapLocation.getDescription();
                aMapLocation.getStreet();
                aMapLocation.getStreetNum();
                aMapLocation.getCityCode();
                aMapLocation.getAdCode();
                aMapLocation.getAoiName();
                lat=aMapLocation.getLatitude();
                lon=aMapLocation.getLongitude();
                Log.e("locationListener",lat+"---"+lon);
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon),19));
//                MarkerOptions markerOptions=new MarkerOptions();
//                markerOptions.position(new LatLng(lat,lon));
//                markerOptions.title("当前位置");
//                markerOptions.visible(true);
//                BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromBitmap(
//                        BitmapFactory.decodeResource(getResources(),R.drawable.car));
//                markerOptions.icon(bitmapDescriptor);
//                aMap.addMarker(markerOptions);
            }else {
                Log.e("AMap Error",aMapLocation.getErrorCode()+"---"+
                        aMapLocation.getErrorInfo());
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
        mapLocationClient.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapLocationClient.stopLocation();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}