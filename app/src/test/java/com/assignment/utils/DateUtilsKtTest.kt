package com.assignment.utils

import com.assignment.api.NYApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @Author: Samsad Chalil Valappil
 * @Date: 16/08/2022
 */
@RunWith(JUnit4::class)
class DateUtilsKtTest {

    @Test
    fun getMyLocale() {
    }

    @Test
    fun getDate() {
    }

    @Test
    fun testGetDate() {
    }

    @Test
    fun getDateString() {
    }

    @Test
    fun getDateString_ZeroMilli_result() {
        val result = getDateString(0, NYApi.PUBLISH_DATE_FORMAT)
        Assert.assertThat(result, `is`("1970-01-01"))
    }

    @Test
    fun getDate_ZeroMilli_result() {
        val result = getDate(0, NYApi.PUBLISH_DATE_FORMAT)
        Assert.assertThat(result, `is`(getDate("1970-01-01", NYApi.PUBLISH_DATE_FORMAT)))
    }
}