package io.github.marktony.espresso.onboarding;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.SmallTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.viewpager.widget.ViewPager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.marktony.espresso.R;
import io.github.marktony.espresso.ui.onboarding.OnboardingActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by lizhaotailang on 2017/5/13.
 * Tests for the {@link ViewPager} and
 * other layout components in {@link OnboardingActivity}.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class OnboardingScreenTest {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<OnboardingActivity> mOnboardingActivityTestRule
            = new ActivityTestRule<>(OnboardingActivity.class);

    @Test
    public void swipeViewPager_scrollPage() {
        // Check that the finish button was invisible.
        onView(withId(R.id.buttonFinish))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        // Check that the previous button was not available.
        onView(withId(R.id.imageButtonPre))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        // Check that the ViewPager was displayed.
        onView(withId(R.id.view_pager))
                .check(matches(isDisplayed()));

        // Scroll the ViewPager
        // Now the app is in the second position.
        onView(withId(R.id.view_pager))
                .perform(swipeLeft());

        // Check that the finish button was gone.
        onView(withId(R.id.buttonFinish))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        // Check that the previous button was visible.
        onView(withId(R.id.imageButtonPre))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Check that the next button was visible.
        onView(withId(R.id.imageButtonNext))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Click the next button to scroll the ViewPager.
        onView(withId(R.id.imageButtonNext))
                .perform(click()); // Now the app is in the third position.

        // Check that the finish button was visible and enabled.
        onView(withId(R.id.buttonFinish))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

        // Check that the next button was not available.
        onView(withId(R.id.imageButtonNext))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        // Check the finish button is enabled.
        onView(withId(R.id.buttonFinish))
                .check(matches(isEnabled()));

        // Check the finish button is visible.
        onView(withId(R.id.buttonFinish))
                .perform(click());

        // Check that main activity was opened.
        onView(withId(R.id.drawer_layout))
                .check(matches(isDisplayed()));
    }

}
