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

package io.element.android.features.messages.impl.timeline.components.event

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import io.element.android.libraries.designsystem.preview.ElementPreviews
import androidx.compose.ui.unit.sp
import io.element.android.features.messages.impl.timeline.model.event.TimelineItemStateContent
import io.element.android.features.messages.impl.timeline.model.event.aTimelineItemStateEventContent
import io.element.android.libraries.designsystem.preview.ElementPreview
import io.element.android.libraries.designsystem.preview.ElementPreviewDark
import io.element.android.libraries.designsystem.theme.components.Text

@Composable
fun TimelineItemStateView(
    content: TimelineItemStateContent,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 13.sp,
        text = content.body,
        textAlign = TextAlign.Center,
    )
}

@ElementPreviews
@Composable
internal fun TimelineItemStateViewLightPreview() {
    ElementPreview { ContentToPreview() }
}

@Composable
private fun ContentToPreview() {
    TimelineItemStateView(
        content = aTimelineItemStateEventContent(),
    )
}
