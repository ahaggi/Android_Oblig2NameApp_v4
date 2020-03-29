package com.example.danie.nameapp_v4;

import android.support.test.rule.ActivityTestRule;

import org.junit.*;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by daniel on 31.01.18.
 */

public class PictureActivityTest {

    @Rule
    public ActivityTestRule<PictureActivity> mActivityRule =
            new ActivityTestRule(PictureActivity.class);

    @Test
    public void testCase5() {
        onView(withId(0)).perform(click());
        onView(withId(R.id.textView2)).check(matches(withText("Daniel")));
    }
}
