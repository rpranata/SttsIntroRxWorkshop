package stts.rxworkshop.di

import stts.rxworkshop.viewmodel.RxWorkshopViewModelFactory

/**
 * The singleton that provides the ViewModelFactory
 *
 * TODO: Provided with DI (Dagger)
 * Created by rendy on 28/1/18.
 */
object Injection {

  fun provideViewModelFactory() = RxWorkshopViewModelFactory

}