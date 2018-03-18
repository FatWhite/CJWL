package cjwl.cjb.org.view.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cjwl.cjb.org.R;
import cjwl.cjb.org.view.fragment.LocationFragment;
import cjwl.cjb.org.view.fragment.NaviFragment;

/**
 * 地图
 * Created by jimbai on 2018/3/18.
 */

public class MapActivity extends Activity{

    private FragmentManager fm;
    private FragmentTransaction transaction;
    private LocationFragment locationFragment;
    private NaviFragment naviFragment;

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
        transaction.add(R.id.fl_fragment, naviFragment);
        transaction.commit();
    }
}
