package com.example.ahha_android.ui.sign

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ahha_android.R
import com.example.ahha_android.data.EasyPeasySharedPreference
import com.example.ahha_android.ui.main.MainActivity

class SignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        if (!EasyPeasySharedPreference.getAccessToken().isNullOrBlank()) {
            moveMainActivity()
        }
    }

    private fun moveMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}