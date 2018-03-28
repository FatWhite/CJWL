package cjwl.cjb.org;

import android.app.Application;

import com.amap.api.navi.model.NaviLatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * application
 * Created by jimbai on 2018/3/28.
 */

public class IApp extends Application{

    private static IApp app=null;

    public static IApp _getApplication(){
        if (app == null){
            synchronized (IApp.class){
                app=new IApp();
            }
        }
        return app;
    }

    //存储算路起点的列表
    protected final List<NaviLatLng> sList = new ArrayList<NaviLatLng>();
    //存储算路终点的列表
    protected final List<NaviLatLng> eList = new ArrayList<NaviLatLng>();
    @Override
    public void onCreate() {
        super.onCreate();
    }

    private void addStartNavi(NaviLatLng latLng){
        sList.add(latLng);
    }
    private void addEndNavi(NaviLatLng latLng){
        eList.add(latLng);
    }
}
