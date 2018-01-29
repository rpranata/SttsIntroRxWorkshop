package stts.rxworkshop

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.plusAssign
import stts.rxworkshop.common.toLiveData
import stts.rxworkshop.validators.AlphanumericValidator
import stts.rxworkshop.validators.EmailValidator
import java.util.concurrent.TimeUnit

interface RegistrationView {
  val email: Observable<CharSequence>
  val password: Observable<CharSequence>
  val tos: Observable<Boolean>
}

data class RegistrationData(
  @JvmField val emailData: EmailData = UnknownEmail,
  @JvmField val passwordData: PasswordData = UnknownPassword,
  @JvmField val isComplete: Boolean = false
)

/**
 * View model object for the Registration screen
 *
 * Created by rendy on 28/1/18.
 */
class RegisterViewModel : ViewModel() {

  private companion object {
    const val TAG = "MainActivityViewModel"
  }

  private val compositeDisposable = CompositeDisposable()

  val data = MutableLiveData<RegistrationData>().apply {
    value = RegistrationData()
  }

  override fun onCleared() {
    super.onCleared()

    compositeDisposable.dispose()
  }

  fun initialize(view: RegistrationView) {
    compositeDisposable.clear()

    // TODO: Define all subscriptions
  }

  fun register(): LiveData<RegistrationResult> {
    val reportFailure: (() -> LiveData<RegistrationResult>) = {
      MutableLiveData<RegistrationResult>().apply {
        value = SuccessfulRegistration("{\"result\": \"success\"}")
      }
    }

    val data = data.value ?: return reportFailure()
    val emailData = data.emailData as? ValidEmail ?: return reportFailure()
    val passwordData = data.passwordData as? ValidPassword ?: return reportFailure()

    Log.e(
      TAG,
      "Registering username: ${emailData.email} and password: ${passwordData.password}"
    )

    return Observable
      .timer(5, TimeUnit.SECONDS)
      .map {
        SuccessfulRegistration("{\"result\": \"success\"}") as RegistrationResult
      }
      .toLiveData()
  }

}

sealed class EmailData

object UnknownEmail : EmailData()
data class ValidEmail(@JvmField val email: CharSequence) : EmailData()
object InvalidEmail : EmailData()


sealed class PasswordData

object UnknownPassword : PasswordData()
data class ValidPassword(@JvmField val password: CharSequence) : PasswordData()
object InvalidPassword : PasswordData()


sealed class RegistrationResult

object FailedRegistration : RegistrationResult()
data class SuccessfulRegistration(@JvmField val response: CharSequence) : RegistrationResult()