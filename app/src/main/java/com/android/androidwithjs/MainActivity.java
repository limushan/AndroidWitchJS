package com.android.androidwithjs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends Activity {

    private WebView mWebView;

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.wv);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.addJavascriptInterface(new JSBridge(), "android");
        mWebView.loadUrl("file:///android_asset/web.html");

        findViewById(R.id.btn_one).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String params = "标题,蜀道大使的,多岁的;已已已i的的,但是;ds";
                int type = 2;
                mWebView.loadUrl("javascript:androidCallJs2(" + type + ",'" + params + "')");
            }
        });
        WebViewClient webviewcilnt = new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String phon = "标题,蜀道大使的,多岁的;已已已i的的,但是;ds";
                mWebView.loadUrl("javascript:androidCallJs('" + phon + "')");
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        };
        mWebView.setWebViewClient(webviewcilnt);
    }

    private Handler handler = new Handler();

    public class JSBridge {

        @JavascriptInterface
        public void toastMessage(String message, int ss) {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("dsds", "2dsdxx电话");
                jsonObject.put("ds", "xxxx");
                jsonObject.put("xx", "2ds电dxxx话");
                jsonObject.put("ddd", "2dds电xxxd话");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final int type = 2;

            handler.post(new Runnable() {
                @Override
                public void run() {
                    //mWebView.loadUrl("javascript:sum(3,114)");
                    //mWebView.loadUrl("javascript:sum(" + type + "," + i + ")");
                    mWebView.loadUrl("javascript:ok2('" + jsonObject.toString() + "')");
                }
            });
            Toast.makeText(getApplicationContext(), "通过Natvie传递的Toast:" + message + ss, Toast.LENGTH_LONG).show();

        }

        @JavascriptInterface
        public String result(String msg) {
            return msg + "sssssssssssss";
        }

        @JavascriptInterface
        public Object result2(String msg){
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("dsds", "2dsdxx电话");
                jsonObject.put("ds", "xxxx");
                jsonObject.put("xx", "2ds电dxxx话");
                jsonObject.put("ddd", "2dds电xxxd话");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
