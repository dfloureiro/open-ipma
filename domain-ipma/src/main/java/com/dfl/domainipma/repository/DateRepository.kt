package com.dfl.domainipma.repository

interface DateRepository {
    fun isToday(date: String): Boolean
}