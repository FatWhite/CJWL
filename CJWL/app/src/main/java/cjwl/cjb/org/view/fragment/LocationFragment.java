package cjwl.cjb.org.view.fragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeAddress;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

import java.text.SimpleDateFormat;
import java.util.Date;

import cjwl.cjb.org.R;

/**
 * 定位
 * Created by jimbai on 2018/3/18.
 */

public class LocationFragment extends Fragment {
    public static LocationFragment _getInstance(){
        LocationFragment fragment=new LocationFragment();
        return fragment;
    }
    MapView mMapView = null;
    private AMap aMap;
    private AMapLocationClient mapLocationClient=null;
    private AMapLocationClientOption locationClientOption=null;
    private double lat,lon;
    private boolean isLocation=false;

    private GeocodeSearch geocodeSearch;

    private Button btnAddMarker;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_location,container,false);
        initView(v,savedInstanceState);
        return v;
    }
    private void initView(View v,Bundle bundle){
        mMapView =v.findViewById(R.id.mv_location);
        mMapView.onCreate(bundle);
        mapLocationClient=new AMapLocationClient(getActivity());
        mapLocationClient.setLocationListener(locationListener);
        geocodeSearch=new GeocodeSearch(getActivity());
        geocodeSearch.setOnGeocodeSearchListener(geocodeSearchListener);
        btnAddMarker=v.findViewById(R.id.btn_addmarker);
        btnAddMarker.setOnClickListener(clickListener);
        initMap();
    }
    private void initMap(){
        if (aMap==null){
            aMap=mMapView.getMap();
            aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
            aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
            // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
            aMap.setMyLocationType(AMap.LOCATION_TYPE_MAP_FOLLOW);
            MyLocationStyle myLocationStyle = new MyLocationStyle();
            myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
            myLocationStyle.myLocationIcon(
                    BitmapDescriptorFactory.fromResource(R.drawable.gps_point));
            myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
            myLocationStyle.strokeWidth(0);
            myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
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
        locationClientOption.setOnceLocation(false);
//        locationClientOption.setWifiScan(false);
//        locationClientOption.setMockEnable(false);//模拟位置
        locationClientOption.setInterval(2000);//定位间隔
        mapLocationClient.setLocationOption(locationClientOption);
        mapLocationClient.startLocation();
    }

    //查询地址位置
    private void getAddressByLatlng(LatLng latLng) {
        //逆地理编码查询条件：逆地理编码查询的地理坐标点、查询范围、坐标类型。
        LatLonPoint latLonPoint = new LatLonPoint(latLng.latitude, latLng.longitude);
        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 500f, GeocodeSearch.AMAP);
        //异步查询
        geocodeSearch.getFromLocationAsyn(query);
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
//                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon),19));
//                if (!isLocation){
//                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat,lon),19));
//                    isLocation=true;
//                }

            }else {
                Log.e("AMap Error",aMapLocation.getErrorCode()+"---"+
                        aMapLocation.getErrorInfo());
            }
        }
    };

    GeocodeSearch.OnGeocodeSearchListener geocodeSearchListener=new GeocodeSearch.OnGeocodeSearchListener() {
        @Override
        public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
            RegeocodeAddress address= regeocodeResult.getRegeocodeAddress();
            String formatAddress = address.getFormatAddress();
            Log.e("onRegeocodeSearched",formatAddress);
        }

        @Override
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

        }
    };

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_addmarker:
                    MarkerOptions markerOptions=new MarkerOptions();
                    markerOptions.position(new LatLng(lat+0.2,lon+0.3));
                    markerOptions.title("当前位置");
                    markerOptions.visible(true);
                    BitmapDescriptor bitmapDescriptor= BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory.decodeResource(getResources(),R.drawable.car));
                    markerOptions.icon(bitmapDescriptor);
                    aMap.addMarker(markerOptions);
                    getAddressByLatlng(new LatLng(lat+0.2,lon+0.3));
                    aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat+0.2,lon+0.3),19));
                    break;
            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }
}
