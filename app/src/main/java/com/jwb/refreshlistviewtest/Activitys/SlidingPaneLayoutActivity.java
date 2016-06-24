package com.jwb.refreshlistviewtest.Activitys;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.jwb.refreshlistviewtest.R;
import com.jwb.refreshlistviewtest.View.BaiduFragment;
import com.jwb.refreshlistviewtest.View.TenFragment;
import com.jwb.refreshlistviewtest.View.WYFragment;

public class SlidingPaneLayoutActivity extends AppCompatActivity {

    private SlidingPaneLayout slidingPaneLayout;
    private ListView slideListView;
    private FrameLayout contentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_pane_layout);
        initView();
    }

    private void initView() {
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.sliding_pane_layout);
        slideListView = (ListView) findViewById(R.id.slide_list_view);
        String[] data={"百度","网易","腾讯"};
        ArrayAdapter<String> adpt=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        slideListView.setAdapter(adpt);
        final FragmentManager manager=getSupportFragmentManager();

        slideListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction ft=null;
                switch (i)
                {
                    case 0:
                        ft=manager.beginTransaction();
                        ft.replace(R.id.fragment_container,new BaiduFragment(),"1");
                        break;
                    case 1:
                        ft=manager.beginTransaction();
                        ft.replace(R.id.fragment_container,new WYFragment(),"2");
                        break;
                    case 2:
                        ft=manager.beginTransaction();
                        ft.replace(R.id.fragment_container,new TenFragment(),"3");
                        break;

                }
                ft.commit();
            }
        });
    }
}
