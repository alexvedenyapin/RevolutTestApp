package com.alexvedenyapin.revoluttestapp.app.arch

import io.reactivex.Scheduler

data class RxWorkers(val subscribeWorker: Scheduler, val observeWorker: Scheduler)