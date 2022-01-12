package com.tyopo.verora.util

import android.content.Context
import android.widget.Toast

fun Context.showToast(value: String, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, value, length).show()

fun Context.getId(name: String, resourceType: String) = resources.getIdentifier(name, resourceType, packageName)

fun Context.getDrawableId(name: String) = getId(name, "drawable")