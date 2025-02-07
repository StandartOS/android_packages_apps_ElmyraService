/*
 * Copyright (C) 2020 The Proton AOSP Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.protonaosp.elmyra

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.MatrixCursor
import android.provider.SearchIndexablesProvider
import android.provider.SearchIndexablesContract.*

class SearchProvider : SearchIndexablesProvider() {
    override fun onCreate() = true
    override fun queryXmlResources(projection: Array<String>?) = MatrixCursor(INDEXABLES_XML_RES_COLUMNS)
    override fun queryNonIndexableKeys(projection: Array<String>?) = MatrixCursor(NON_INDEXABLES_KEYS_COLUMNS)

    override fun queryRawData(projection: Array<String>?): Cursor {
        val ref = Array<Any?>(INDEXABLES_RAW_COLUMNS.size) { null }
        ref[COLUMN_INDEX_RAW_KEY] = context.getString(R.string.settings_entry_title)
        ref[COLUMN_INDEX_RAW_TITLE] = context.getString(R.string.settings_entry_title)
        ref[COLUMN_INDEX_RAW_SUMMARY_ON] = context.getString(R.string.settings_entry_summary)
        ref[COLUMN_INDEX_RAW_KEYWORDS] = context.getString(R.string.settings_search_keywords)

        // For breadcrumb generation
        ref[COLUMN_INDEX_RAW_SCREEN_TITLE] = context.getString(R.string.settings_entry_title)
        ref[COLUMN_INDEX_RAW_CLASS_NAME] = SettingsActivity::class.java.name

        ref[COLUMN_INDEX_RAW_INTENT_ACTION] = Intent.ACTION_MAIN
        ref[COLUMN_INDEX_RAW_INTENT_TARGET_PACKAGE] = context.applicationInfo.packageName
        ref[COLUMN_INDEX_RAW_INTENT_TARGET_CLASS] = SettingsActivity::class.java.name

        return MatrixCursor(INDEXABLES_RAW_COLUMNS).apply {
            addRow(ref)
        }
    }
}
