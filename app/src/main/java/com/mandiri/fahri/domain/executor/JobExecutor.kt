package com.mandiri.fahri.domain.executor

import java.util.concurrent.*

interface  ThreadExcutor : Executor
class JobExecutor  : ThreadExcutor{

    private val threadPoolExecutor = ThreadPoolExecutor(
        3,
        5,
        10,
        TimeUnit.SECONDS,
        LinkedBlockingQueue(),
        JobThreadFactory()
    )
    override fun execute(command : Runnable) {
        command.let {
            threadPoolExecutor.execute(it)
        }
    }

    class JobThreadFactory(private var counter: Int = 0) : ThreadFactory {

        override fun newThread(runnable: Runnable?) =
            Thread(runnable, "android_${counter.inc()}")
    }
}