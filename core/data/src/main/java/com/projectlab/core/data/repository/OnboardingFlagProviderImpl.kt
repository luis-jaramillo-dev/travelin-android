package com.projectlab.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import com.projectlab.core.domain.proto.OnboardingFlag
import com.projectlab.core.domain.repository.OnboardingFlagProvider
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class OnboardingFlagProviderImpl @Inject constructor(
    private val onboardingFlagStore: DataStore<OnboardingFlag>,
) : OnboardingFlagProvider {
    override suspend fun hasOpenedAppBefore(): Boolean {
        val onboardingFlag = try {
            onboardingFlagStore.data.first()
        } catch (_: IOException) {
            OnboardingFlag.getDefaultInstance()
        }

        return onboardingFlag.openedApp
    }

    override suspend fun setAsOpenedBefore() {
        onboardingFlagStore.updateData { flag ->
            flag.toBuilder().setOpenedApp(true).build()
        }
    }
}
