package com.breaktime.mustune.settings.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.data.api.repository.SettingsRepository
import com.breaktime.mustune.common.domain.BaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetNotificationsEnabledUseCase @Inject constructor(private val settingsRepository: SettingsRepository
) : BaseOutcomeUseCase<Nothing, SetNotificationsEnabledUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Nothing> {
        settingsRepository.setNotificationsEnabled(params.isEnabled)
        return Outcome.Success.Empty
    }

    data class Params(val isEnabled: Boolean)
}