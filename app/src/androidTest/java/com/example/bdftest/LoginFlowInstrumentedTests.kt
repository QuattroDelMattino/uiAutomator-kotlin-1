package com.example.bdftest

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LoginFlowInstrumentedTests {
    private lateinit var device: UiDevice
    private val username: String = "DuckDodgers"

    @Before
    fun startMainActivity() {
        // Initialize UiDevice instance
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        // Use shell command to launch the app:
        device.executeShellCommand("am start -n com.example.bdftest/com.example.bdftest.MainActivity")
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.bdftest", appContext.packageName)
    }

    fun ValidUserLogin()
    {
        device.wait(Until.hasObject((By.res("UserInputField"))), 5_000)
        val inputTextField = device.findObject(By.res("UserInputField"))
        inputTextField.click()
        inputTextField.text = username

        device.findObject(By.res("ContinueButton")).click()
    }

    fun userTypesOtherCharacters(inputText: String){
        device.wait(Until.hasObject((By.res("UserInputField"))), 5_000)
        val inputTextField = device.findObject(By.res("UserInputField"))
        inputTextField.click()
        inputTextField.text = inputText

        device.wait(Until.hasObject((By.res("ContinueButton"))), 5_000)
        device.findObject(By.res("ContinueButton")).click()
    }

    @Test
    fun `When_user_enters_the_correct_name_then_logged_in_page_is_shown`() {
        ValidUserLogin()

        val msg1 = device.findObject(By.res("LoggedInUserText")).text
        val msg2 = device.findObject(By.res("SuccessMsg")).text

        assertEquals(msg1, "Welcome back $username")
        assertEquals(msg2, "You have successfully logged in")
        device.takeScreenshot(File("/sdcard/Pictures/When_user_enters_the_correct_name.png"));
    }

    @Test
    fun `When_user_leaves_input_field_empty_then_error_message_is_shown`() {
        device.wait(Until.hasObject((By.res("ContinueButton"))), 5_000)
        device.findObject(By.res("ContinueButton")).click()
        val userTyped = device.findObject(By.res("ErrorMsg")).text

        assertEquals(userTyped, "Numele userului trebuie inserat!")

        device.takeScreenshot(File("/sdcard/Pictures/When_user_leaves_input_field_empty.png"));
    }

    @Test
    fun `When_user_types_special_characters_then_error_message_is_shown`() {
        //input speciale
        device.wait(Until.hasObject((By.res("UserInputField"))), 5_000)
        val inputTextField = device.findObject(By.res("UserInputField"))
        inputTextField.click()
        inputTextField.text = "Duck&*("

        device.wait(Until.hasObject((By.res("ContinueButton"))), 5_000)
        device.findObject(By.res("ContinueButton")).click()
        val userTyped = device.findObject(By.res("ErrorMsg")).text

        assertEquals(userTyped, "Acest input-field nu suporta caractere speciale")
        device.takeScreenshot(File("/sdcard/Pictures/When_user_types_special_characters.png"));
    }

    @Test
    fun `When_user_types_wrong_username_then_error_message_is_shown`() {
        //input valid, user incorect
        device.wait(Until.hasObject((By.res("UserInputField"))), 5_000)
        val inputTextField = device.findObject(By.res("UserInputField"))
        inputTextField.click()
        inputTextField.text = "DuckDodgersss"

        device.wait(Until.hasObject((By.res("ContinueButton"))), 5_000)
        device.findObject(By.res("ContinueButton")).click()
        val userTyped = device.findObject(By.res("ErrorMsg")).text

        assertEquals(userTyped, "User necunoscut")

        device.takeScreenshot(File("/sdcard/Pictures/When_user_types_wrong_username.png"));

    }

    @Test
    fun `When_user_types_numbers_then_error_message_is_shown`() {
        //input cu numere
        userTypesOtherCharacters("Duck123")

        val userTyped = device.findObject(By.res("ErrorMsg")).text
        assertEquals(userTyped, "Acest input-field nu suporta caractere speciale")
        device.takeScreenshot(File("/sdcard/Pictures/When_user_types_numbers.png"));
    }

    @Test
    fun `When_user_types_whitespace_then_error_message_is_shown`() {
        //input cu spatiu gol
        userTypesOtherCharacters("Duck ")

        val userTyped = device.findObject(By.res("ErrorMsg")).text
        assertEquals(userTyped, "Acest input-field nu suporta caractere speciale")
        device.takeScreenshot(File("/sdcard/Pictures/When_user_types_whitespace.png"));
    }
}