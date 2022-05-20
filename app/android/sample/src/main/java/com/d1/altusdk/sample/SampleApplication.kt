package com.d1.altusdk.sample

import android.app.Application
import cx.d1.altusdk.infra.D1AltuSdk
import cx.d1.pushsdk.infra.D1PushSdk
import cx.d1.pushsdk.infra.D1PushSdkConfig

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        D1PushSdk.setup(this, D1PushSdkConfig)

        D1AltuSdk.init(this)
    }
}