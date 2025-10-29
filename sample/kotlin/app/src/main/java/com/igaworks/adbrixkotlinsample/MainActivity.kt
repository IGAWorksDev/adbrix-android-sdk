package com.igaworks.adbrixkotlinsample

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.igaworks.adbrix.Adbrix
import com.igaworks.adbrix.constants.ABEvent
import com.igaworks.adbrix.constants.ABEventProperty
import com.igaworks.dfinerykotlinsample.Utils

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_constraintLayout_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<View>(R.id.main_button_login).setOnClickListener(this)
        findViewById<View>(R.id.main_button_purchase).setOnClickListener(this)
        findViewById<View>(R.id.main_button_customEvent).setOnClickListener(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }

    override fun onClick(view: View?) {
        val viewId: Int = view!!.id
        if (viewId == R.id.main_button_login) {
            Adbrix.getInstance().logEvent(ABEvent.LOGIN)
        } else if (viewId == R.id.main_button_purchase) {
            Adbrix.getInstance().logEvent(
                ABEvent.PURCHASE,
                Utils.JSONObjectBuilder()
                    .addProperty(
                        ABEventProperty.ITEMS, Utils.makeJSONArray(
                            Utils.JSONObjectBuilder()
                                .addProperty(ABEventProperty.ITEM_PRODUCT_ID, "상품번호")
                                .addProperty(ABEventProperty.ITEM_PRODUCT_NAME, "상품이름")
                                .addProperty(ABEventProperty.ITEM_CATEGORY1, "식품")
                                .addProperty(ABEventProperty.ITEM_CATEGORY2, "과자")
                                .addProperty(ABEventProperty.ITEM_PRICE, 5000.0)
                                .addProperty(ABEventProperty.ITEM_DISCOUNT, 500.0)
                                .addProperty(ABEventProperty.ITEM_QUANTITY, 5L)
                                .build()
                        )
                    )
                    .addProperty(ABEventProperty.ORDER_ID, "주문번호")
                    .addProperty(ABEventProperty.PAYMENT_METHOD, "BankTransfer")
                    .addProperty(ABEventProperty.ORDER_SALES, 25500.0)
                    .addProperty(ABEventProperty.DELIVERY_CHARGE, 3000.0)
                    .addProperty(ABEventProperty.DISCOUNT, 0.0)
                    .build()
            )
        } else if (viewId == R.id.main_button_customEvent) {
            Adbrix.getInstance().logEvent(
                "custom_event",
                Utils.JSONObjectBuilder()
                    .addProperty("custom_property_1", 34000L)
                    .addProperty("custom_property_2", 42.195)
                    .addProperty("custom_property_3", "Seoul")
                    .addProperty("custom_property_4", true)
                    .build()
            )
        }
    }
}