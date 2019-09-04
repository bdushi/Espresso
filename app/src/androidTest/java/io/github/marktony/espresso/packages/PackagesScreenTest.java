package io.github.marktony.espresso.packages;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.Gravity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.github.marktony.espresso.R;
import io.github.marktony.espresso.mvp.packages.MainActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerActions.open;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;
import static androidx.test.espresso.contrib.NavigationViewActions.navigateTo;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by lizhaotailang on 2017/5/12.
 * The tests for {@link io.github.marktony.espresso.mvp.packages.PackagesFragment}.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PackagesScreenTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void navigateToPackagesScreen() {
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left drawer should be closed.
                .perform(open()); // Open the drawer.

        // Start packages screen
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_home));
    }

    @Test
    public void test_PackagesScreenDisplayed() {
        // Check that the bottom navigation view was displayed.
        onView(withId(R.id.bottomNavigationView))
                .check(matches(isDisplayed()));

        // Check that the fab was displayed.
        onView(withId(R.id.fab))
                .check(matches(isDisplayed()));
        onView(withId(R.id.fab))
                .check(matches(isClickable()));
    }

}
