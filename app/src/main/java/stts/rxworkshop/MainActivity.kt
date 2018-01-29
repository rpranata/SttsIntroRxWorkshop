package stts.rxworkshop

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.RxCompoundButton
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import stts.rxworkshop.databinding.ActivityMainBinding
import stts.rxworkshop.di.Injection

interface RegisterViewHandler {
  fun register()
}

class RegisterViewData(
  @JvmField val emailError: ObservableField<String> = ObservableField(""),
  @JvmField val passwordError: ObservableField<String> = ObservableField(""),
  @JvmField val isSignUpButtonEnabled: ObservableBoolean = ObservableBoolean(true)
)

class MainActivity :
  AppCompatActivity(),
  RegistrationView,
  RegisterViewHandler {

  private companion object {
    const val TAG = "RegisterActivity"
  }

  // TODO: Inject this
  private lateinit var viewModel: RegisterViewModel

  private lateinit var binding: ActivityMainBinding

  private val registerViewData = RegisterViewData()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.data = registerViewData
    binding.handler = this

    val viewModelFactory = Injection.provideViewModelFactory()
    viewModel = ViewModelProviders
      .of(this, viewModelFactory)
      .get(RegisterViewModel::class.java)
    viewModel.initialize(this)
  }

  override fun onStart() {
    super.onStart()
    viewModel.data.observe(
      this,
      Observer<RegistrationData> { data ->
        data ?: return@Observer
        updateUI(data)
      }
    )
  }

  override fun onDestroy() {
    super.onDestroy()
    binding.unbind()
  }

  override val email: Observable<CharSequence>
    get() = RxTextView.textChanges(binding.email)

  override val password: Observable<CharSequence>
    get() = RxTextView.textChanges(binding.password)

  override val tos: Observable<Boolean>
    get() = RxCompoundButton.checkedChanges(binding.tosAcceptanceCheckbox!!)

  override fun register() {
    viewModel.register().observe(
      this,
      Observer<RegistrationResult> { data ->
        data ?: return@Observer

        when (data) {
          is SuccessfulRegistration -> {
            Toast
              .makeText(
                this,
                "Registration successful with ${data.response}",
                Toast.LENGTH_LONG
              )
              .show()
          }
          FailedRegistration -> {
            Toast
              .makeText(
                this,
                "Registration failure",
                Toast.LENGTH_LONG
              )
              .show()
          }

        }
      }
    )

  }

  private fun updateUI(data: RegistrationData) {
    Log.e(TAG, "On data changed -> $data")

    registerViewData.emailError.set(
      if (data.emailData == InvalidEmail) getString(R.string.validator_invalid_email) else ""
    )

    registerViewData.passwordError.set(
      if (data.passwordData == InvalidPassword)
        getString(R.string.validator_invalid_password)
      else
        ""
    )

    registerViewData.isSignUpButtonEnabled.set(data.isComplete)
  }

}
