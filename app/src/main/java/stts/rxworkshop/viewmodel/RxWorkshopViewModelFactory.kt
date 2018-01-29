package stts.rxworkshop.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import stts.rxworkshop.RegisterViewModel

/**
 * Factory class for all the view models.
 *
 * TODO: Provided with DI (Dagger)
 * Created by rendy on 28/1/18.
 */
object RxWorkshopViewModelFactory : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
      @Suppress("UNCHECKED_CAST")
      return RegisterViewModel() as T
    }
    throw IllegalArgumentException("Unknown ViewModel class")
  }

}
