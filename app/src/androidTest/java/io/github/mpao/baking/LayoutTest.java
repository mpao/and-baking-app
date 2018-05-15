package io.github.mpao.baking;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.github.mpao.baking.ui.MainActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LayoutTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void selectRecipe() {

        onView( allOf( withId(R.id.list) ) ).check(matches( isDisplayed() ) );
        onView( allOf( withId(R.id.list) ) ).perform(actionOnItemAtPosition(0, click() ) );
        onView( allOf( withId(R.id.list) ) ).check( matches(isDisplayed()) );

    }

}
