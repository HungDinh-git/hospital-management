package com.example.qlbv;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityRule =
            new ActivityScenarioRule<>(RegisterActivity.class);

    private void delay() {
        try {
            Thread.sleep(2000); // Delay 2s mỗi bước
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEmptyFields_showError() {
        delay();
        onView(withId(R.id.btnRegister)).perform(click());
        delay();
        // Kiểm tra Toast hoặc các hành động tương ứng với lỗi ở đây
    }

    @Test
    public void testRegisterPatientSuccess() {
        delay();
        onView(withId(R.id.edtFullname)).perform(typeText("Nguyen Van B"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtEmail)).perform(typeText("hung"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtPassword)).perform(typeText("123"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtConfirmPassword)).perform(typeText("123"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.spinnerRole)).perform(click());
        delay();
        onView(withText("Bệnh nhân")).perform(click());
        delay();
        onView(withId(R.id.btnRegister)).perform(click());
        delay();
    }

    @Test
    public void testRegisterDoctorSuccess() {
        delay();
        onView(withId(R.id.edtFullname)).perform(typeText("Phan The Hoang"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtEmail)).perform(typeText("hoang"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtPassword)).perform(typeText("123"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtConfirmPassword)).perform(typeText("123"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.spinnerRole)).perform(click());
        delay();
        onView(withText("Bác sĩ")).perform(click());
        delay();
        onView(withId(R.id.btnRegister)).perform(click());
        delay();
    }

    @Test
    public void testMismatchedPasswords_showError() {
        delay();
        onView(withId(R.id.edtFullname)).perform(typeText("Nguyen Van A"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtEmail)).perform(typeText("a@example.com"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtPassword)).perform(typeText("123456"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtConfirmPassword)).perform(typeText("654321"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.btnRegister)).perform(click());
        delay();
    }

    @Test
    public void testRegisterWithExistingEmail_showError() {
        delay();
        onView(withId(R.id.edtFullname)).perform(typeText("Dinh Duc Hung"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtEmail)).perform(typeText("hung"), closeSoftKeyboard()); // email đã có
        delay();
        onView(withId(R.id.edtPassword)).perform(typeText("123"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtConfirmPassword)).perform(typeText("123"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.spinnerRole)).perform(click());
        delay();
        onView(withText("Bệnh nhân")).perform(click());
        delay();
        onView(withId(R.id.btnRegister)).perform(click());
        delay();
    }
}
