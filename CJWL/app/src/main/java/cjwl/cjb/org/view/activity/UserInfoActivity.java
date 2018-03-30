package cjwl.cjb.org.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cjwl.cjb.org.R;

/**
 * 个人信息
 * Created by jimbai on 2018/3/30.
 */

public class UserInfoActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
    }
}
