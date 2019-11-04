package example.myapplication.auxiliary.rx

import io.reactivex.Scheduler

interface Schedulers {

    companion object {

        /**
         * Возвращает планировщик
         * потоков по-умолчанию.
         */
        fun schedulers(): Schedulers =
            DefaultSchedulers()

    }

    /**
     * Планировщик основного потока.
     */
    fun ui(): Scheduler

    /**
     * Фоновый планировщик потоков.
     */
    fun background(): Scheduler

    /**
     * Новая нить.
     */
    fun newThread(): Scheduler

}