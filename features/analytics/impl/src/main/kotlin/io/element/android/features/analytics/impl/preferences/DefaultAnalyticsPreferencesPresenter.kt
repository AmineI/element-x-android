/*
 * Copyright (c) 2023 New Vector Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.element.android.features.analytics.impl.preferences

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import com.squareup.anvil.annotations.ContributesBinding
import io.element.android.features.analytics.api.AnalyticsOptInEvents
import io.element.android.features.analytics.api.Config
import io.element.android.features.analytics.api.preferences.AnalyticsPreferencesPresenter
import io.element.android.features.analytics.api.preferences.AnalyticsPreferencesState
import io.element.android.libraries.core.meta.BuildMeta
import io.element.android.libraries.di.AppScope
import io.element.android.services.analytics.api.AnalyticsService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class DefaultAnalyticsPreferencesPresenter @Inject constructor(
    private val analyticsService: AnalyticsService,
    private val buildMeta: BuildMeta,
) : AnalyticsPreferencesPresenter {

    @Composable
    override fun present(): AnalyticsPreferencesState {
        val localCoroutineScope = rememberCoroutineScope()
        val isEnabled = analyticsService.getUserConsent()
            .collectAsState(initial = false)

        fun handleEvents(event: AnalyticsOptInEvents) {
            when (event) {
                is AnalyticsOptInEvents.EnableAnalytics -> localCoroutineScope.setIsEnabled(event.isEnabled)
            }
        }

        return AnalyticsPreferencesState(
            applicationName = buildMeta.applicationName,
            isEnabled = isEnabled.value,
            policyUrl = Config.POLICY_LINK,
            eventSink = ::handleEvents
        )
    }

    private fun CoroutineScope.setIsEnabled(enabled: Boolean) = launch {
        analyticsService.setUserConsent(enabled)
    }
}
