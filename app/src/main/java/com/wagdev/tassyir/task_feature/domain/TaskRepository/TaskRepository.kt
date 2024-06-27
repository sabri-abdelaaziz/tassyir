package com.wagdev.tassyir.task_feature.domain.TaskRepository

import com.wagdev.tassyir.task_feature.data.local.TaskDao
import com.wagdev.tassyir.task_feature.domain.Model.Task

import kotlinx.coroutines.flow.Flow
interface TaskRepository {

fun getAllTasks():Flow<List<Task>>
suspend fun getTaskById(id:Int):Task?
suspend fun insertTask(task:Task)
suspend fun deleteTask(task: Task)


}