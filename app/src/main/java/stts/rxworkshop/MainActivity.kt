package stts.rxworkshop

import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.CompoundButton
import stts.rxworkshop.databinding.ActivityMainBinding
import stts.rxworkshop.validators.AlphanumericValidator
import stts.rxworkshop.validators.EmailValidator

interface CanValidateEmail {
  fun validateEmail()
}

interface CanValidatePassword {
  fun validatePassword()
}

class RegisterViewData(
  @JvmField val isSignUpButtonEnabled: ObservableBoolean = ObservableBoolean(true)
)

class MainActivity : AppCompatActivity(), CanValidateEmail, CanValidatePassword {

  private lateinit var binding: ActivityMainBinding

  private val registerViewData = RegisterViewData()

  private var isEmailValid = false
  private var isPasswordValid = false

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.data = registerViewData

    binding.email.addTextChangedListener(EmailTextChangedListener(this))
    binding.password.addTextChangedListener(PasswordTextChangedListener(this))
  }

  override fun onDestroy() {
    super.onDestroy()
    binding.unbind()
  }

  override fun validateEmail() {
    val email = binding.email.text
    if (!TextUtils.isEmpty(email) && EmailValidator.validate(email)) {
      binding.emailControl.isErrorEnabled = false
    } else {
      binding.emailControl.isErrorEnabled = true
      binding.emailControl.error = getString(R.string.validator_invalid_email)
    }

    isEmailValid = !binding.emailControl.isErrorEnabled
    toggleSignInButton()
  }

  override fun validatePassword() {
    val password = binding.password.text
    if (!TextUtils.isEmpty(password) && AlphanumericValidator.validate(password)) {
      binding.passwordControl.isErrorEnabled = false
    } else {
      binding.passwordControl.isErrorEnabled = true
      binding.passwordControl.error = getString(R.string.validator_invalid_password)
    }

    isPasswordValid = !binding.passwordControl.isErrorEnabled
    toggleSignInButton()
  }

  private fun toggleSignInButton() {
    registerViewData.isSignUpButtonEnabled.set(
      isEmailValid && isPasswordValid
    )
  }

}

private class EmailTextChangedListener(
  private val validator: CanValidateEmail
) : TextWatcher {

  override fun afterTextChanged(email: Editable?) {
    validator.validateEmail()
  }

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    // No op
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    // No op
  }

}

private class PasswordTextChangedListener(
  private val validator: CanValidatePassword
) : TextWatcher {

  override fun afterTextChanged(email: Editable?) {
    validator.validatePassword()
  }

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    // No op
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    // No op
  }

}
