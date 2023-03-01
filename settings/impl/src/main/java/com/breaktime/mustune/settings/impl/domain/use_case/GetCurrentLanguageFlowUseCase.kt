package com.breaktime.mustune.settings.impl.domain.use_case

import com.breaktime.mustune.common.domain.BaseFlowUseCase
import com.breaktime.mustune.data.api.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrentLanguageFlowUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : BaseFlowUseCase<String, GetCurrentLanguageFlowUseCase.Params>() {
    override fun execute(params: Params): Flow<String> = settingsRepository.getCurrentLanguage()

    object Params
}