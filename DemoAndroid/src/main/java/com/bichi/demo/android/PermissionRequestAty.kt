package com.bichi.demo.android

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.bichi.lib.common.android.ui.BaseActivity


class PermissionRequestAty : BaseActivity() {
    private val TAG = "PermissionRequestAty"
    //返回code
    private val OPEN_SET_REQUEST_CODE = 1
    private val permissions = arrayOf<String>(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lackPer = lacksPermission(permissions, this)
        if (lackPer.size == 0) {
            Toast.makeText(this, "都权限都有了", Toast.LENGTH_SHORT).show()
        } else {
            //请求权限，第二参数权限String数据，第三个参数是请求码便于在onRequestPermissionsResult 方法中根据code进行判断
            ActivityCompat.requestPermissions(this, lackPer.toTypedArray(), OPEN_SET_REQUEST_CODE)
        }
    }

    //如果返回true表示缺少权限
    fun lacksPermission(requestPermissions: Array<String>, context: Context): MutableList<String> {
        val resultList = mutableListOf<String>()
        for (permission in requestPermissions) {
            //判断是否缺少权限，true=缺少权限
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                resultList.add(permission)
            }
        }
        return resultList
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == OPEN_SET_REQUEST_CODE) {
            for (i in 0 until permissions.size) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {//选择了“始终允许”
                    Log.d(TAG, "permission ${permissions[i]} granted")
                    Toast.makeText(this, "" + "权限" + permissions[i] + "申请成功", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "permission ${permissions[i]} denied")
                }
            }
        }
    }
}