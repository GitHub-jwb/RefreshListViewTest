package com.jwb.refreshlistviewtest.Activitys;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.jwb.refreshlistviewtest.R;
import com.jwb.refreshlistviewtest.View.MyFragment;

import java.util.ArrayList;
import java.util.List;

public class DrawerLayoutActivity extends AppCompatActivity {
    private NavigationView navi;
    private List<MyFragment> fragments;
    String[] url={"定位","看电视","听音乐","联系"};
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout2);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        initToolbar();
        initFragment();
        initNavigationView();

    }
    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //class ActionBarDrawerToggle implements DrawerLayout.DrawerListener
        //1.创建一个抽屉的监听器，这个监听器可以把toolbar和DrawerLayout关联起来
        ActionBarDrawerToggle listener = new ActionBarDrawerToggle(this,drawerLayout,
                toolbar,R.string.open_drawer,R.string.close_drawer);

        //2.同步toolbar上面的navitgation图标和drawer的状态
        listener.syncState();

        drawerLayout.addDrawerListener(listener);
    }

    private void initFragment() {
        fragments=new ArrayList<>();
        for (int i = 0; i < url.length; i++) {
            MyFragment fragment=new MyFragment();
            Bundle bundle=new Bundle();
            bundle.putString("url",url[i]);
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
    }

    private void initNavigationView() {
        navi = (NavigationView) findViewById(R.id.navigation_view);
        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()){
                    case R.id.loc_item://定位
                        transaction.replace(R.id.fragment_container,fragments.get(0));
                        break;
                    case R.id.tv_item://看电视
                        transaction.replace(R.id.fragment_container,fragments.get(1));
                        break;
                    case R.id.music_item://听音乐
                        transaction.replace(R.id.fragment_container,fragments.get(2));
                        break;
                    case R.id.contact_item://联系
                        transaction.replace(R.id.fragment_container,fragments.get(3));
                        break;
                }
                transaction.commit();
                drawerLayout.closeDrawer(GravityCompat.START,true);
                return true;
            }
        });
    }

}
