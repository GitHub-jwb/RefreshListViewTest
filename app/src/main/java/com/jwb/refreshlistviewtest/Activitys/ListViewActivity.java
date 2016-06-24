package com.jwb.refreshlistviewtest.Activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.jwb.refreshlistviewtest.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class ListViewActivity extends AppCompatActivity {

    private ImageView imageView;
    private PtrClassicFrameLayout ptrLayout;
    String[] urlStrings={"http://img5.duitang.com/uploads/item/201410/04/20141004141720_vr23M.jpeg",
    "http://img1.imgtn.bdimg.com/it/u=3724834980,466973059&fm=11&gp=0.jpg",
    "http://i2.hexunimg.cn/2015-04-07/174742774.jpg",
    "http://img1.imgtn.bdimg.com/it/u=2525689499,526305536&fm=21&gp=0.jpg"
    };
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        imageView = (ImageView) findViewById(R.id.image_view);
        ptrLayout = (PtrClassicFrameLayout) findViewById(R.id.ptr_layout);
        setHeader();
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                startLoadBitmap();
            }
        });
    }

    private void setHeader() {
        // 创建一个MaterialHeader 的视图(该框架自带)
        final MaterialHeader header = new MaterialHeader(this);

        //可以设置一组 颜色数组，改变进度显示的颜色变化
        header.setColorSchemeColors(new int[]{Color.RED, Color.BLUE});
        //设置布局参数，-1指匹配父窗体，-2指包裹内容
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        //设置内边距...PtrLocalDisplay框架自带
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, PtrLocalDisplay.dp2px(10));
        //告诉创建一个MaterialHeader 布局绑定在那个下拉刷新控件上
        header.setPtrFrameLayout(ptrLayout);

        //给下拉刷新设置下拉头部 MaterialHeader布局
        ptrLayout.setHeaderView(header);
        //添加一个UI时间处理回调函数。为MaterialHeader的内部实现回调。
        ptrLayout.addPtrUIHandler(header);
    }

    int curIndex=0;
    private void startLoadBitmap() {
        curIndex%=urlStrings.length;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(urlStrings[curIndex]);
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    if(con.getResponseCode()==200){
                        bitmap = BitmapFactory.decodeStream(con.getInputStream());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                ptrLayout.refreshComplete();
                            }
                        });
                    }
                    curIndex++;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
