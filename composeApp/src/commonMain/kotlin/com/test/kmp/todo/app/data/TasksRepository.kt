package com.test.kmp.todo.app.data

import com.test.kmp.todo.app.data.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

private val fakeData = listOf(
    Task(
        id = "1",
        name = "Task 1",
        description = "Short description."
    ),
    Task(
        id = "2",
        name = "Detailed Task 2: Analyze requirements & design 🔍",
        description = "This task involves analyzing project requirements and designing solutions to meet them."
    ),
    Task(
        id = "3",
        name = "T3 🚀",
        description = "A very short one."
    ),
    Task(
        id = "4",
        name = "Complex Task 4: Implementation, testing, & deployment! 💻⚙️",
        description = "This is a much longer description that includes all the steps necessary for implementation, testing, and deployment, covering edge cases and best practices."
    ),
    Task(
        id = "5",
        name = "Medium Task 5",
        description = "Medium length description to balance things out."
    ),
    Task(
        id = "6",
        name = "Quick Fix 6 🛠️",
        description = "Brief."
    ),
    Task(
        id = "7",
        name = "Task 7: Document everything! 📄",
        description = "Here is another detailed explanation that spans several lines, ensuring that the variety of description lengths is adequately tested in the application."
    ),
    Task(
        id = "8",
        name = "One-liner Task 8",
        description = "Simple one-liner."
    ),
    Task(
        id = "9",
        name = "T9: Special Characters Test! @#$%^&*()",
        description = "Description with special characters: !@#$%^&*() and numbers 12345."
    ),
    Task(
        id = "10",
        name = "Comprehensive Analysis 10: Let's break it down 🧠📊",
        description = "A longer description explaining the need for a detailed analysis, covering multiple aspects of the project and ensuring a robust implementation strategy."
    )
)


interface TasksRepository {
    val allTasksFlow: StateFlow<List<Task>>
    fun addTask(task: Task)
    fun markAsFinished(taskId: String)
    fun getTask(taskId: String): Task
    fun revokeTask(taskId: String)
    fun updateTask(updatedTask: Task)
}

class TasksRepositoryImpl() : TasksRepository {
    private val _allTasksFlow = MutableStateFlow(fakeData)
    override val allTasksFlow: StateFlow<List<Task>> = _allTasksFlow

    override fun addTask(task: Task) {
        _allTasksFlow.value += task
    }

    override fun markAsFinished(taskId: String) {
        _allTasksFlow.update { currentList ->
            currentList.map { task ->
                if(task.id == taskId){
                    task.copy(
                        isFinished = true
                    )
                } else {
                    task
                }
            }
        }
    }

    override fun getTask(taskId: String): Task {
        return _allTasksFlow.value.first { it.id == taskId }
    }

    override fun revokeTask(taskId: String) {
        _allTasksFlow.update { currentList ->
            currentList.map { task ->
                if(task.id == taskId){
                    task.copy(
                        isFinished = false
                    )
                } else {
                    task
                }
            }
        }
    }

    override fun updateTask(updatedTask: Task) {
        _allTasksFlow.update { currentList ->
            currentList.map { task ->
                if(task.id == updatedTask.id){
                    updatedTask
                } else {
                    task
                }
            }
        }
    }
}
