package scheduler

import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.ScheduledFuture
import java.util.concurrent.TimeUnit

class Scheduler(private val executorService: ScheduledExecutorService) {

    fun shutDown() {
        executorService.shutdown()
    }

    fun schedule(task: Runnable, initialDelay: Int, period: Int): ScheduledFuture<*> {
        return executorService.scheduleAtFixedRate(task, initialDelay.toLong(), period.toLong(), TimeUnit.MILLISECONDS)
    }
}