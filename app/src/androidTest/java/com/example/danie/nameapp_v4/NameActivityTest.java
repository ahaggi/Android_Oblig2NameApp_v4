package com.example.danie.nameapp_v4;

import android.app.Activity;
import android.app.Instrumentation.ActivityResult;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by daniel on 31.01.18.
 */

public class NameActivityTest {

    @Rule
    public ActivityTestRule<NameActivity> nameActivity = new ActivityTestRule(NameActivity.class);
    @Rule
    public ActivityTestRule<AddPersonActivity> addPersonActivity = new ActivityTestRule(AddPersonActivity.class);

    @Test
    public void testCase4() {
        onView(withId(R.id.menuAdd)).perform(click());
        onView(withId(R.id.editText2)).perform(typeText("test_name"));

        Resources resources = InstrumentationRegistry.getTargetContext().getResources();

        Uri u = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(R.drawable.abdella) + '/' +
                resources.getResourceTypeName(R.drawable.abdella) + '/' +
                resources.getResourceEntryName(R.drawable.abdella));

        Intent resultData = new Intent();
        resultData.setData(u);
        //ActivityResult(int resultCode, Intent resultData)
        ActivityResult result = new ActivityResult(Activity.RESULT_OK, resultData);

        Matcher<Intent> expectedIntent = allOf(hasAction(Intent.ACTION_OPEN_DOCUMENT));

        //For each time you call init(), you must follow it up with a call to release() at the end of the test. This will clear the Intents state and stop recording intents
        Intents.init();
        intending(expectedIntent).respondWith(result);
        onView(withId(R.id.button5)).perform(click());
        intended(expectedIntent); // to check if button5.onclick did actually launched Intent.ACTION_OPEN_DOCUMENT
        Intents.release();

        pressBack();

        onView(withId(R.id.button7_save)).perform(click());

        onView(withId(R.id.namesList)).check(matches(hasDescendant(withText("test_name"))));
    }

    @Test
    public void testCase6() {
        /**
     AdapterView is a special type of widget that loads its data dynamically from an Adapter.
     The most common example of an AdapterView is ListView. As opposed to static widgets like
     LinearLayout, only a subset of the AdapterView children may be loaded into the current
     view hierarchy. A simple onView() search would not find views that are not currently loaded.

     Espresso handles this by providing a separate onData() entry point which is able to first load
     the adapter item in question, bringing it into focus prior to operating on it or any of its children.

         */
        onData(hasToString(startsWith("Daniel"))).inAdapterView(withId(R.id.namesList)).atPosition(0).perform(click());

        onView(withId(R.id.textView2)).check(matches(withText("Daniel")));
    }
}
