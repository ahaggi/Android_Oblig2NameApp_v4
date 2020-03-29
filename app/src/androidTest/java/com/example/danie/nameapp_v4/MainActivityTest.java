package com.example.danie.nameapp_v4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.test.rule.ActivityTestRule;

import org.junit.*;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by daniel on 31.01.18.
 */

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class, true, false);

    @Test
    public void testCase3() {
        Context context = getInstrumentation().getTargetContext();
        SharedPreferences preferencesEditor = PreferenceManager.getDefaultSharedPreferences(context);

//        preferencesEditor.edit().clear().apply();

        preferencesEditor.edit().putString("owner_name", "Default").apply();

        mActivityRule.launchActivity(new Intent());

        onView(withId(R.id.textView6)).check(matches(withText("Owner Info")));

    }
}
