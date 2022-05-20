package com.d1.altusdk.sample

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.isVisible
import com.d1.altusdk.sample.databinding.ActivityMainBinding
import cx.d1.altusdk.core.data.domain.ConversationHistoryType
import cx.d1.altusdk.core.data.domain.D1AltuSdkEnvironment
import cx.d1.altusdk.core.data.domain.D1AltuSdkWebSocketEnvironment
import cx.d1.altusdk.core.infra.D1AltuSdkConfig
import cx.d1.altusdk.core.network.serializer.toObject
import cx.d1.altusdk.infra.D1AltuSdk
import cx.d1.altusdk.infra.ChatNotificationListener

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        loadingDefaultValuesForTest()

        binding.spinnerWebSocketEnvironment.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = binding.spinnerWebSocketEnvironment.selectedItem as String
                val environment = resources.getStringArray(R.array.environments)[3]
                binding.acetCustomWebSocketEnvironment.isVisible = item == environment
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinnerChatEnvironment.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val item = binding.spinnerChatEnvironment.selectedItem as String
                val environment = resources.getStringArray(R.array.environments)[3]
                binding.acetCustomChatEnvironment.isVisible = item == environment
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.fab.setOnClickListener {
            try {
                isFieldsFilled(
                    binding.acedSourceId,
                    binding.acedSlug,
                    binding.acedSecret,
                    binding.acedChannel
                )

                /**
                 * É re-configurado desta forma para obter novos dados do form de configuração.
                 * */
                setupChat()

                D1AltuSdk.openChat(
                    binding.acedChannel.text.toString(),
                    binding.acedSourceId.text.toString()
                )
            } catch (exception: Exception) {
                Log.v("OpenChat", exception.message ?: "Ocorreu um erro ao abrir o chat.")
            }
        }

        configChatNotificationListener()
        checkPendingChatNotification()
    }

    private fun checkPendingChatNotification() {
        if (D1AltuSdk.hasNotificationData()) {
            D1AltuSdk.consumeNotificationData()?.let(::openChat)
        }
    }

    private fun configChatNotificationListener() {
        D1AltuSdk.setChatNotificationListener(object : ChatNotificationListener {
            override fun onChatNotificationReceived(): Boolean {
                return if (D1AltuSdk.hasNotificationData()) {
                    D1AltuSdk.consumeNotificationData()?.let(::openChat) ?: false
                } else {
                    false
                }
            }
        })
    }

    private fun openChat(it: String): Boolean {
        return it.toObject(RemoteMessageData::class.java).takeIf { it.isValid() }?.let { remoteMessage ->
            setupChat()

            D1AltuSdk.openChat(
                widgetIdentifier = remoteMessage.widgetIdentifier ?: "",
                sourceId = remoteMessage.sourceId ?: ""
            )

            true
        } ?: false
    }

    private fun setupChat() {
        val config = D1AltuSdkConfig
            .setSlug(binding.acedSlug.text.toString())
            .setSecret(binding.acedSecret.text.toString())
            .setConversationHistoryType(getConversationHistoryType())
            .setWebSocketEnvironment(getWebSocketEnvironment())
            .setEnvironment(getChatEnvironment())

        D1AltuSdk.setup(config)
    }

    private fun getConversationHistoryType(): ConversationHistoryType {
        val item = binding.spinnerConversationHistoryType.selectedItem as String
        return ConversationHistoryType.valueOf(item)
    }

    private fun getWebSocketEnvironment(): D1AltuSdkWebSocketEnvironment {
        val item = binding.spinnerWebSocketEnvironment.selectedItem as String
        var url = ""

        if (binding.acetCustomWebSocketEnvironment.isVisible) {
            isFieldsFilled(binding.acetCustomWebSocketEnvironment)
            url = binding.acetCustomWebSocketEnvironment.text.toString()
        }

        return D1AltuSdkWebSocketEnvironment.toD1AltuSdkWebSocketEnvironment(item, url)
    }

    private fun getChatEnvironment(): D1AltuSdkEnvironment {
        val item = binding.spinnerChatEnvironment.selectedItem as String
        var url = ""

        if (binding.acetCustomChatEnvironment.isVisible) {
            isFieldsFilled(binding.acetCustomChatEnvironment)
            url = binding.acetCustomChatEnvironment.text.toString()
        }

        return D1AltuSdkEnvironment.toD1AltuSdkEnvironment(item, url)
    }

    private fun isFieldsFilled(vararg appCompatEditText: AppCompatEditText) {
        appCompatEditText.forEach {
            if (it.text.toString().isEmpty()) {
                it.error = "Preencha esse campo"
                throw Exception("FieldNotFilled")
            }
        }
    }

    private fun loadingDefaultValuesForTest() {
        binding.acedSourceId.setText("1")
        binding.acedSlug.setText("rethink")
        binding.acedSecret.setText("oPDAWJUBFQOemYHy")
        binding.acedChannel.setText("84db55734373c86e6f7c4312a13920c5")
    }
}