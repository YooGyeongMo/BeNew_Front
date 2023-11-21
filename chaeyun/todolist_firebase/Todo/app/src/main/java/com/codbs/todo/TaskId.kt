package com.codbs.todo

open class TaskId {
    @JvmField
    var TaskId: String? = null
    fun <T : TaskId?> withId(id: String): T {
        TaskId = id
        return this as T
    }
}