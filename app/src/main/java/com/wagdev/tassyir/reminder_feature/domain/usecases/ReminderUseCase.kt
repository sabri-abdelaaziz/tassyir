package com.wagdev.tassyir.reminder_feature.domain.usecases

data class ReminderUseCase(
    val getAllRemindersUseCase: GetAllRemindersUseCase,
    val getReminderByIdUseCase: GetReminderByIdUseCase,
    val deleteReminderUseCase:DeleteReminderUseCase,
    val addEditReminderUseCase:AddEditReminderUseCase
)