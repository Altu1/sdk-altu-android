package com.d1.altusdk.sample

data class RemoteMessageData (
    val sourceId: String?,
    val widgetIdentifier: String?
)

fun RemoteMessageData.isValid(): Boolean {
    return this.sourceId?.isNotBlank() == true && this.widgetIdentifier?.isNotBlank() == true
}