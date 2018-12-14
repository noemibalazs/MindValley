package com.example.android.mindvalley;

import android.support.test.rule.ActivityTestRule;

import com.example.android.mindvalley.mindvalley.ui.MainActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static android.support.test.espresso.assertion.ViewAssertions.matches;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ViewPagerTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void isSwipped(){
        onView(withId(R.id.view_pager)).perform(swipeLeft()).check(matches(isDisplayed()));

    }

    @Test
    public void isTablayout(){
        onView(withId(R.id.tab_layout)).perform(click()).check(matches(isDisplayed()));
    }




}
