package com.jwb.refreshlistviewtest.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jwb.refreshlistviewtest.R;

public class BaiduFragment extends Fragment {

    private WebView webView;
    private WebSettings webSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_baidu,container,false);
        webView = (WebView) view.findViewById(R.id.baidu_web_view);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // 为了让网页加载速度快一点，在加载网页的时候，先阻止网络图片加載
        webSettings.setBlockNetworkImage(true);//是否阻止图片加载

        //点击超链接后不弹出浏览器窗口，而在WebView控件中加载URL
        webView.setWebViewClient(new WebViewClient() {

            //重写页面加载完成的回调，页面加载完成可以开始加载图片。可以取消加载的提示！
            @Override
            public void onPageFinished(WebView view, String url) {
                webSettings.setBlockNetworkImage(false);
            }
        });
        webView.loadUrl("http://www.baidu.com");

        return view;
    }
}
