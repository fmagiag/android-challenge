package com.magiag.androidchallenge

import com.magiag.androidchallenge.data.repository.data.ShowsDataRepository
import com.magiag.androidchallenge.data.repository.data.ShowsDataStore
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class ServiceTest {

    private var mRepository: ShowsDataRepository? = null

    @Before
    fun setup() {
        mRepository = ShowsDataStore()
    }

    @Test
    fun validateShowsService() {

        val testObserver = mRepository!!.getShows(1).test()
        testObserver.awaitDone(60, TimeUnit.SECONDS)

        // Assert
        testObserver.assertNoErrors()
                .assertValue { value -> value != null }
    }
}
