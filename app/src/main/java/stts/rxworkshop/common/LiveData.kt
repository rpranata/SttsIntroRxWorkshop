package stts.rxworkshop.common

import android.arch.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

/**
 * Created by rendy on 29/1/18.
 */
fun <T> Observable<T>.toLiveData() =
  LiveDataReactiveStreams
    .fromPublisher(
      this.toFlowable(BackpressureStrategy.LATEST)
    )