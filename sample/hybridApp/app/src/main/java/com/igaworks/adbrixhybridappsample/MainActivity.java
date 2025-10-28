package com.igaworks.adbrixhybridappsample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.igaworks.adbrix.Adbrix;
import com.igaworks.adbrix.AdbrixDeepLink;
import com.igaworks.adbrix.AdbrixDeferredDeepLinkListener;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getCanonicalName();
    private static final String JAVASCRIPT_INTERFACE_NAME = "adbrixWebBridge";
    private AlertDialog deepLinkDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_constraintLayout_container), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        WebView webView = findViewById(R.id.main_webView);
        initWebView(webView);
        showDeepLinkUri(getIntent());
        Adbrix.getInstance().blockDeferredDeepLinkLaunch(new AdbrixDeferredDeepLinkListener() {
            @Override
            public void onDeferredDeepLinkReceived(Context context, AdbrixDeepLink adbrixDeepLink) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(deepLinkDialog != null){
                            deepLinkDialog.dismiss();
                        }
                        Log.i(TAG, "deferred deep link is received. uri:"+adbrixDeepLink.getDeepLink());
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Deferred Deep Link가 수신되었습니다.")
                                .setMessage(adbrixDeepLink.getDeepLink())
                                .show();
                    }
                });
            }
        });
    }

    @Override
    protected void onNewIntent(@NonNull Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        showDeepLinkUri(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        WebView webView = findViewById(R.id.main_webView);
        webView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebView webView = findViewById(R.id.main_webView);
        webView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deepLinkDialog = null;
    }

    private void initWebView(WebView webView){
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d(TAG, "["+consoleMessage.messageLevel().name()+"] "+consoleMessage.sourceId()+" ("+consoleMessage.lineNumber()+") "+consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "onPageFinished. url:"+url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG, "onPageStarted. url:"+url);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webView.setRendererPriorityPolicy(WebView.RENDERER_PRIORITY_BOUND, true);
        }
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            webSettings.setEnableSmoothTransition(true);
        }
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setDisplayZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportMultipleWindows(false);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setGeolocationEnabled(false);
        webSettings.setDefaultTextEncodingName("utf-8");
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        webView.addJavascriptInterface(new AdbrixJavascriptInterface(), JAVASCRIPT_INTERFACE_NAME);
        webView.loadUrl("file:///android_asset/index.html");
    }

    private void showDeepLinkUri(Intent intent){
        if(intent!=null && intent.getData()!=null){
            String uri = intent.getData().toString();
            if(!TextUtils.isEmpty(uri)){
                Log.i(TAG, "deep link is received. uri:"+uri);
                deepLinkDialog = new AlertDialog.Builder(this)
                        .setTitle("Deep Link가 수신되었습니다.")
                        .setMessage(uri)
                        .show();
            }
        }
    }
}