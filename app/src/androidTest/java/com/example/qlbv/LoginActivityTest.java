package com.example.qlbv;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    private void delay() {
        try {
            Thread.sleep(1000); // delay 2 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEmptyFields_showErrorToast() {
        delay();
        onView(withId(R.id.btnLogin)).perform(click());
        delay();
        onView(withId(R.id.edtUsername)).check(matches(withText("")));
        delay();
        onView(withId(R.id.edtPassword)).check(matches(withText("")));
    }

    @Test
    public void testRegisterTextView_opensRegisterActivity() {
        delay();
        onView(withId(R.id.tvRegister)).perform(click());
        delay();
    }

    @Test
    public void testIncorrectLogin_showErrorToast() {
        delay();
        onView(withId(R.id.edtUsername)).perform(typeText("sai_user"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtPassword)).perform(typeText("sai_mk"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.btnLogin)).perform(click());
        delay();
    }

    @Test
    public void testCorrectLogin_navigateToDashboard() {
        delay();
        onView(withId(R.id.edtUsername)).perform(typeText("hung"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.edtPassword)).perform(typeText("123"), closeSoftKeyboard());
        delay();
        onView(withId(R.id.btnLogin)).perform(click());
        delay();
    }
}
