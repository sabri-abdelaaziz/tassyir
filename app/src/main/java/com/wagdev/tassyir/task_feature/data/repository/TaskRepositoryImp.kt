package com.wagdev.tassyir.task_feature.data.repository

import com.wagdev.tassyir.task_feature.data.local.TaskDao
import com.wagdev.tassyir.task_feature.domain.Model.Task
import com.wagdev.tassyir.task_feature.domain.TaskRepository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImp(
    val taskDao: TaskDao
) :TaskRepository{
    override fun getAllTasks(): Flow<List<Task>> {
        return taskDao.getAllTasks()
    }
    override suspend fun getTaskById(id: Int): Task? {
        return taskDao.getTaskById(id)
    }
    override suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }
    override suspend fun deleteTask(task: Task) {
       taskDao.deleteTask(task)
    }

}