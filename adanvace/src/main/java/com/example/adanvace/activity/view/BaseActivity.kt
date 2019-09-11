package com.example.adanvace.activity.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager

/**
 * @author alan
 * Function Activity
 * 1.状态栏
 * 2.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val statusBarColor = Color.WHITE
    protected val statusBarTextColor = Color.WHITE

    /**
     * 布局
     * @return int
     */
    @get:LayoutRes
    abstract val contentView: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.hide()
        if (supportActionBar != null) {
        }
        setStatusBarColor(statusBarColor, statusBarTextColor)
        setContentView(contentView)
        requestPermission()
        initView()
        initData()
        initEvent()
    }

    /**
     * 请求权限
     */
    protected open fun requestPermission() {

    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initEvent()

    protected fun setStatusBarColor(statusBarColor: Int, textColor: Int) {
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 设置状态栏底色颜色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = statusBarColor

            // 如果亮色，设置状态栏文字为黑色
            if (isLightColor(textColor)) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
    }

    /**
     * 是否是亮色
     * @param textColor
     * @return
     */
    private fun isLightColor(textColor: Int): Boolean {
        return true
    }
}
