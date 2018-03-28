package cjwl.cjb.org.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.AMapNaviViewOptions;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapModelCross;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.tbt.TrafficFacilityInfo;

import java.util.List;

import cjwl.cjb.org.IApp;
import cjwl.cjb.org.R;

/**
 * 导航
 * Created by jimbai on 2018/3/28.
 */

public class NaviActivity extends Activity {

    private AMapNaviView aMapNaviView;
    private AMapNavi aMapNavi;
    protected List<NaviLatLng> mWayPointList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);
        initView(savedInstanceState);
    }

    private void initView(Bundle bundle){
        aMapNaviView=findViewById(R.id.mv_navi);
        aMapNaviView.onCreate(bundle);
        aMapNavi= AMapNavi.getInstance(getApplicationContext());
        //添加监听
        aMapNavi.addAMapNaviListener(aMapNaviListener);
        aMapNaviView.setAMapNaviViewListener(aMapNaviViewListener);

        //设置模拟导航的行车速度
        aMapNavi.setEmulatorNaviSpeed(75);
        AMapNaviViewOptions options = new AMapNaviViewOptions();
        options.setTilt(0);
        aMapNaviView.setViewOptions(options);
    }

    AMapNaviViewListener aMapNaviViewListener=new AMapNaviViewListener() {
        @Override
        public void onNaviSetting() {

        }

        @Override
        public void onNaviCancel() {

        }

        @Override
        public boolean onNaviBackClick() {
            return false;
        }

        @Override
        public void onNaviMapMode(int i) {

        }

        @Override
        public void onNaviTurnClick() {

        }

        @Override
        public void onNextRoadClick() {

        }

        @Override
        public void onScanViewButtonClick() {

        }

        @Override
        public void onLockMap(boolean b) {

        }

        @Override
        public void onNaviViewLoaded() {

        }
    };

    AMapNaviListener aMapNaviListener=new AMapNaviListener() {
        @Override
        public void onInitNaviFailure() {

        }

        @Override
        public void onInitNaviSuccess() {
/**
 * 方法: int strategy=mAMapNavi.strategyConvert(congestion, avoidhightspeed, cost, hightspeed, multipleroute); 参数:
 *
 * @congestion 躲避拥堵
 * @avoidhightspeed 不走高速
 * @cost 避免收费
 * @hightspeed 高速优先
 * @multipleroute 多路径
 *
 *  说明: 以上参数都是boolean类型，其中multipleroute参数表示是否多条路线，如果为true则此策略会算出多条路线。
 *  注意: 不走高速与高速优先不能同时为true 高速优先与避免收费不能同时为true
 */
            int strategy = 0;
            try {
                //再次强调，最后一个参数为true时代表多路径，否则代表单路径
                strategy = aMapNavi.strategyConvert(true, false, false, false, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 驾车算路
            aMapNavi.calculateDriveRoute(IApp._getApplication().getsList(), IApp._getApplication().geteList(), mWayPointList, strategy);
        }

        @Override
        public void onStartNavi(int i) {

        }

        @Override
        public void onTrafficStatusUpdate() {

        }

        @Override
        public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

        }

        @Override
        public void onGetNavigationText(int i, String s) {

        }

        @Override
        public void onGetNavigationText(String s) {

        }

        @Override
        public void onEndEmulatorNavi() {

        }

        @Override
        public void onArriveDestination() {

        }

        @Override
        public void onCalculateRouteFailure(int i) {

        }

        @Override
        public void onReCalculateRouteForYaw() {

        }

        @Override
        public void onReCalculateRouteForTrafficJam() {

        }

        @Override
        public void onArrivedWayPoint(int i) {

        }

        @Override
        public void onGpsOpenStatus(boolean b) {

        }

        @Override
        public void onNaviInfoUpdate(NaviInfo naviInfo) {

        }

        @Override
        public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

        }

        @Override
        public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

        }

        @Override
        public void updateIntervalCameraInfo(AMapNaviCameraInfo aMapNaviCameraInfo, AMapNaviCameraInfo aMapNaviCameraInfo1, int i) {

        }

        @Override
        public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

        }

        @Override
        public void showCross(AMapNaviCross aMapNaviCross) {

        }

        @Override
        public void hideCross() {

        }

        @Override
        public void showModeCross(AMapModelCross aMapModelCross) {

        }

        @Override
        public void hideModeCross() {

        }

        @Override
        public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

        }

        @Override
        public void showLaneInfo(AMapLaneInfo aMapLaneInfo) {

        }

        @Override
        public void hideLaneInfo() {

        }

        @Override
        public void onCalculateRouteSuccess(int[] ints) {
            aMapNavi.startNavi(NaviType.EMULATOR);
        }

        @Override
        public void notifyParallelRoad(int i) {

        }

        @Override
        public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

        }

        @Override
        public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

        }

        @Override
        public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

        }

        @Override
        public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

        }

        @Override
        public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

        }

        @Override
        public void onPlayRing(int i) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        aMapNaviView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        aMapNaviView.onDestroy();
    }

}

