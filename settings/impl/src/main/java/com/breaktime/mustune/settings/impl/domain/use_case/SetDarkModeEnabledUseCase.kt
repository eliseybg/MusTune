package com.breaktime.mustune.settings.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseOutcomeUseCase
import com.breaktime.mustune.common.domain.Outcome
import com.breaktime.mustune.data.api.repository.SettingsRepository
import javax.inject.Inject

class SetDarkModeEnabledUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
    ) : BaseOutcomeUseCase<Nothing, SetDarkModeEnabledUseCase.Params>() {
    override suspend fun execute(params: Params): Outcome<Nothing> {
        settingsRepository.setDarkModeEnabled(params.isEnabled)
        return Outcome.Success.Empty
    }

    data class Params(val isEnabled: Boolean)
}