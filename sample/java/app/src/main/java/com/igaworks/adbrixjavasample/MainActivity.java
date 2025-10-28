package com.igaworks.adbrixjavasample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

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
import com.igaworks.adbrix.constants.ABEvent;
import com.igaworks.adbrix.constants.ABEventProperty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getCanonicalName();
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
        findViewById(R.id.main_button_login).setOnClickListener(this);
        findViewById(R.id.main_button_purchase).setOnClickListener(this);
        findViewById(R.id.main_button_customEvent).setOnClickListener(this);

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
    protected void onDestroy() {
        super.onDestroy();
        deepLinkDialog = null;
    }

    @Override
    public void onClick(View view) {
        final int viewId = view.getId();
        if(viewId == R.id.main_button_login){
            Adbrix.getInstance().logEvent(ABEvent.LOGIN);
        } else if(viewId == R.id.main_button_purchase){
            Adbrix.getInstance().logEvent(ABEvent.PURCHASE,
                    new Utils.JSONObjectBuilder()
                            .addProperty(ABEventProperty.ITEMS, Utils.makeJSONArray(new Utils.JSONObjectBuilder()
                                    .addProperty(ABEventProperty.ITEM_PRODUCT_ID, "상품번호")
                                    .addProperty(ABEventProperty.ITEM_PRODUCT_NAME, "상품이름")
                                    .addProperty(ABEventProperty.ITEM_CATEGORY1, "식품")
                                    .addProperty(ABEventProperty.ITEM_CATEGORY2, "과자")
                                    .addProperty(ABEventProperty.ITEM_PRICE, 5000.0)
                                    .addProperty(ABEventProperty.ITEM_DISCOUNT, 500.0)
                                    .addProperty(ABEventProperty.ITEM_QUANTITY, 5L)
                                    .build()))
                            .addProperty(ABEventProperty.ORDER_ID, "주문번호")
                            .addProperty(ABEventProperty.PAYMENT_METHOD, "BankTransfer")
                            .addProperty(ABEventProperty.ORDER_SALES, 25500.0)
                            .addProperty(ABEventProperty.DELIVERY_CHARGE, 3000.0)
                            .addProperty(ABEventProperty.DISCOUNT, 0.0)
                            .build());
        } else if(viewId == R.id.main_button_customEvent){
            Adbrix.getInstance().logEvent("custom_event",
                    new Utils.JSONObjectBuilder()
                            .addProperty("custom_property_1", 34000L)
                            .addProperty("custom_property_2", 42.195)
                            .addProperty("custom_property_3", "Seoul")
                            .addProperty("custom_property_4", true)
                            .build());
        }
    }

    private void showDeepLinkUri(Intent intent){
        if(intent!=null && intent.getData()!=null){
            String uri = intent.getData().toString();
            if(!TextUtils.isEmpty(uri)){
                deepLinkDialog = new AlertDialog.Builder(this)
                        .setTitle("Deep Link가 수신되었습니다.")
                        .setMessage(uri)
                        .show();
            }
        }
    }
}
