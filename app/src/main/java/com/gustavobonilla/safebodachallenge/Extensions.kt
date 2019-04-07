package com.gustavobonilla.safebodachallenge

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.support.annotation.DimenRes
import android.support.v4.content.ContextCompat
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Extension that allows a [ViewGroup] to inflate any layout.
 *
 * @param layoutRes the layout resource to be inflated.
 * @return the [View] inflated.
 */
fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

/**
 * Extension that allows to make visible or not visible a view by handling as an boolean attribute.
 */
var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

/**
 * Tells if an object is null.
 */
fun Any?.isNull(): Boolean {
    return this == null
}

/**
 * Tells if an object is not null.
 */
fun Any?.isNotNull(): Boolean {
    return this != null
}

/**
 * Gets the value of a attribute in a safe way.
 *
 * @param getValue function that get the value supposing it can be returned.
 * @param defaultValue the default value in case the object is null return this value.
 *
 * @return the value of the attribute if object not null, otherwise the defaultValue.
 */
fun <T>Any?.getAttribute(getValue: ()-> T, defaultValue: T): T {
    return if (this != null) {
        getValue()
    } else {
        defaultValue
    }
}

/**
 * Get pixel value based on sp unit value.
 *
 * @param sp the value in sp unit.
 */
fun View.getPixelFromSp(sp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.resources.displayMetrics)
}

/**
 * Get pixel value based on dp unit value.
 *
 * @param dp the value in dp unit.
 */
fun View.getPixelFromDp(dp: Float): Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics)
}

/**
 * Extension that allows to get a [BitmapDescriptor] from a Vector in the resources of app.
 *
 * @param vectorResId the Vector resource id.
 * @return the [BitmapDescriptor] object for the given vectorResId.
 */
fun Activity.bitmapDescriptorFromVector(vectorResId: Int): BitmapDescriptor {
    val vectorDrawable = ContextCompat.getDrawable(this, vectorResId)
    vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)
    val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    vectorDrawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}

/**
 * Gets a dimens float value.
 *
 * @param idRes the id of dimens resource.
 */
fun Context.getFloatFromDimens(@DimenRes idRes: Int): Float {
    val typedValue = TypedValue()
    resources.getValue(idRes, typedValue, true)
    return typedValue.float
}

/**
 * Check if an object is of type of T.
 */
inline fun <reified T> checkType(obj: Any): Boolean {
    return obj is T
}

/**
 * Defines a block of code that should run when the device os version is equals or above Android M.
 */
inline fun supportsMarshmallow(code: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        code()
    }
}

inline fun <T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

