package com.example.hitman1337x.journalapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EntriesScreenTest {

    @Rule
    public ActivityTestRule<DiaryEntries> mEntriesActivityActivityTestRule = new ActivityTestRule<DiaryEntries>(DiaryEntries.class);

    @Test
    public void clickAddEntryButton_opensAddEntriesUi() throws Exception {
        onView(withId(R.id.textview)).perform(click()).check(matches(isDisplayed()));
    }
}

