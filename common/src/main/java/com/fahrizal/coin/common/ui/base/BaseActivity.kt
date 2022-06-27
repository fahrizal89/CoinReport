package com.fahrizal.coin.common.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T> : AppCompatActivity() {

    private lateinit var viewBinding: ViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        constructViewBinding().let {
            viewBinding = it
            setContentView(it.root)
            init(savedInstanceState)
        }
    }

    fun getViewBinding(): T = viewBinding as T

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    fun showToast(@StringRes strRes: Int) =
        Toast.makeText(this, strRes, Toast.LENGTH_SHORT).show()

    abstract fun constructViewBinding(): ViewBinding
    abstract fun init(savedInstanceState: Bundle?)
}