package com.alexvedenyapin.revoluttestapp.util

import com.alexvedenyapin.revoluttestapp.app.arch.RxWorkers
import io.reactivex.Observable

fun <T> Observable<T>.composeWith(workers: RxWorkers) = compose {
    it.subscribeOn(workers.subscribeWorker).observeOn(workers.observeWorker)
}