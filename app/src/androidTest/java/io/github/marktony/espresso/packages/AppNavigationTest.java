package io.github.marktony.espresso.packages;

import android.graphics.drawable.ColorDrawable;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.appcompat.widget.Toolbar;
import android.view.Gravity;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
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
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

/**
 * Created by lizhaotailang on 2017/5/12.
 * Tests for the {@link DrawerLayout} layout
 * component in {@link MainActivity} which manages the navigation
 * within the app.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AppNavigationTest {

    /**
     * {@link ActivityTestRule} is a JUnit {@link Rule @Rule} to launch your activity under test.
     *
     * <p>
     * Rules are interceptors which are executed for each test method and are important building
     * blocks of Junit tests.
     */
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule
            = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void clickOnNavigationDrawerItem_ShowsPackagesScreen() {
        // Open drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left drawer should be closed.
                .perform(open()); // Open the drawer.

        // Start packages screen
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_home));

        // Check that packages fragment was opened.
        onView(withId(R.id.fragment_packages))
                .check(matches(isDisplayed()));

    }

    @Test
    public void clickOnNavigationDrawerItem_ShowsCompaniesScreen() {
        // Open drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left drawer should be closed.
                .perform(open()); // Open the drawer.

        // Start companies screen
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_companies));

        // Check that companies fragment was opened.
        onView(withId(R.id.recyclerViewCompaniesList))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnNavigationDrawerItem_ShowsSettingsScreen() {
        // Open drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        // Start settings screen.
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_settings));

        // Check that title is correct.
        onView(allOf(withParent(withId(R.id.toolbar)),
                withText(R.string.nav_settings)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnNavigationDrawerItem_ShowsAboutScreen() {
        // Open drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        // Start about screen.
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_about));

        // Check that title is correct.
       onView(allOf(withParent(withId(R.id.toolbar)),
                withText(R.string.nav_about)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void clickOnNavigationDrawerItem_ChangeTheme() {
        // Open drawer to click on navigation.
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(open());

        // Get the color value of toolbar in package fragment.
        Toolbar toolbar = (Toolbar) mainActivityActivityTestRule.getActivity().findViewById(R.id.toolbar);
        int color = ((ColorDrawable) toolbar.getBackground()).getColor();

        // Click the navigate item of changing theme.
        onView(withId(R.id.nav_view))
                .perform(navigateTo(R.id.nav_switch_theme));

        // Compare the current color with the old one.
        // If not match, the action of changing theme is successful.
        onView(withId(R.id.toolbar))
                .check(matches(not(withBackgroundColor(color))));

    }

    /**
     * A customized {@link Matcher} for testing that
     * if one color match the background color of current view.
     * @param backgroundColor A color int value.
     *
     * @return Match or not.
     */
    public static Matcher<View> withBackgroundColor(final int backgroundColor) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                int color = ((ColorDrawable) view.getBackground().getCurrent()).getColor();
                return color == backgroundColor;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with background color value: " + backgroundColor);
            }
        };
    }

}
