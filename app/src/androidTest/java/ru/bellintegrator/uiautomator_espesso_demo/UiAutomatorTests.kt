package ru.bellintegrator.uiautomator_espesso_demo

import android.content.Context
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private lateinit var device: UiDevice

private const val BASIC_SAMPLE_PACKAGE = "ru.bellintegrator.uiautomator_espesso_demo"
private const val LAUNCH_TIMEOUT = 5000L
private const val STRING_TO_BE_TYPED = "UiAutomator"

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class UiAutomatorTests {

    @Before
    fun startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage: String = device.launcherPackageName
        assertThat(launcherPackage, notNullValue())
        device.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT
        )

        // Launch the app
        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager.getLaunchIntentForPackage(
            BASIC_SAMPLE_PACKAGE)?.apply {
            // Clear out any previous instances
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(
            Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
            LAUNCH_TIMEOUT
        )
    }

    @Test
    fun test1(){

    }

}