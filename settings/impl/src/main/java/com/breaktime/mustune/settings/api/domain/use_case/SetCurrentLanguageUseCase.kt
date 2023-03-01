package com.breaktime.mustune.settings.api.domain.use_case

import com.breaktime.mustune.data.api.repository.SettingsRepository
import javax.inject.Inject

class SetCurrentLanguageUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {
    suspend operator fun invoke(language: String) {
        settingsRepository.setCurrentLanguage(language)
    }
}