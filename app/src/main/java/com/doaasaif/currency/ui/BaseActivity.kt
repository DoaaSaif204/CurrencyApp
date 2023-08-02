package com.doaasaif.currency.ui

import android.os.Bundle
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.doaasaif.currency.helper.ProgressHelper


open abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
    }

    abstract fun getLayoutResourceId(): Int
    protected fun showProgressDailog(message: String) {
        ProgressHelper.showDialog(this, message)
    }
    protected fun dismissProgressDialog(){
        ProgressHelper.dismissDialog()
    }


}