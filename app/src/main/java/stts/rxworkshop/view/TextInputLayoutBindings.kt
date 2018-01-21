package stts.rxworkshop.view

import android.databinding.BindingAdapter
import android.support.design.widget.TextInputLayout

/**
 * Custom setters for [TextInputLayout] property binding
 * https://developer.android.com/topic/libraries/data-binding/index.html
 *
 * Created by RenD on 21/1/18.
 */
object TextInputLayoutBindings {

  @BindingAdapter("errorMessage")
  @JvmStatic
  fun setErrorMessage(textInputLayout: TextInputLayout, errorMessage: CharSequence?) {
    textInputLayout.error = errorMessage
  }

}
