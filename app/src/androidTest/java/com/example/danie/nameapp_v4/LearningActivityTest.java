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

public class LearningActivityTest {

    @Rule
    public ActivityTestRule<LearningActivity> mActivityRule =
            new ActivityTestRule(LearningActivity.class);

    @Test
    public void testCase1() {
        onView(withId(R.id.learningInputEditeTxt)).perform(typeText("entotre"));
        onView(withId(R.id.learningInputEditeTxt)).check(matches(withText("entotre")));
    }


    @Test
    public void testCase2() {
        onView(withId(R.id.learningCheckButton)).perform(click());
        onView(withId(R.id.learningScoreTxtView)).check(matches(withText("-1")));

    }
}
