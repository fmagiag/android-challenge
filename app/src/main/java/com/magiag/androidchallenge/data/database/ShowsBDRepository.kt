//package com.magiag.androidchallenge.data.database
//
//import android.content.Context
//import com.magiag.androidchallenge.data.entity.ShowEntity
//import java.util.concurrent.Executors
//
//import androidx.lifecycle.LiveData
//
//class ShowsBDRepository private constructor(context: Context) {
//
//    var mShows: LiveData<List<ShowEntity>>
//    private val mDb: ShowRoomDatabase
//    private val executor = Executors.newSingleThreadExecutor()
//
//    private val allShows: LiveData<List<ShowEntity>>
//        get() = mDb.showDao().getAll()
//
//    init {
//        mDb = ShowRoomDatabase.getDatabase(context)
//        mShows = allShows
//    }
//
//    fun deleteAllShows() {
//        executor.execute { mDb.showDao().deleteAll() }
//    }
//
//    fun getShowById(showId: Int): ShowEntity {
//        return mDb.showDao().getShowById(showId)
//    }
//
//    fun insertShow(show: ShowEntity) {
//        executor.execute { mDb.showDao().insertShow(show) }
//    }
//
//    fun deleteShow(show: ShowEntity) {
//        executor.execute { mDb.showDao().deleteShow(show) }
//    }
//
//    companion object {
//        private var ourInstance: ShowsBDRepository? = null
//
//        fun getInstance(context: Context): ShowsBDRepository {
//            if (ourInstance == null) {
//                ourInstance = ShowsBDRepository(context)
//            }
//            return ourInstance!!
//        }
//    }
//}
