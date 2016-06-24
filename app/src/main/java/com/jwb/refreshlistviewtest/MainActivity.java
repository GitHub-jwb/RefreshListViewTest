package com.jwb.refreshlistviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jwb.refreshlistviewtest.Activitys.DrawerLayoutActivity;
import com.jwb.refreshlistviewtest.Activitys.ListViewActivity;
import com.jwb.refreshlistviewtest.Activitys.SlidingPaneLayoutActivity;
import com.jwb.refreshlistviewtest.Activitys.WebViewActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setOnItemClickListener(this);
        String[] data={"下拉刷新","WebView控件的使用","SlidingPaneLayout的使用",
        "DrawerLayout控件的使用"
        };
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=null;
        switch (i)
        {
            case 0:
                intent=new Intent(this, ListViewActivity.class);
                break;
            case 1:
                intent=new Intent(this, WebViewActivity.class);
                break;
            case 2:
                intent=new Intent(this, SlidingPaneLayoutActivity.class);
                break;
            case 3:
                intent=new Intent(this, DrawerLayoutActivity.class);
                break;
        }
        startActivity(intent);
    }
}
