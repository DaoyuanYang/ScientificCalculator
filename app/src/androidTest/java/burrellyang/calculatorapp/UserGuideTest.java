package burrellyang.calculatorapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserGuideTest {

    @Rule
    public ActivityTestRule<UserGuide> guideActivityTestRule = new ActivityTestRule<UserGuide>(UserGuide.class);
    private UserGuide guideActivity = null;

    @Before
    public void setUp() {
        guideActivity = guideActivityTestRule.getActivity();
    }

    @Test @UiThreadTest
    public void testLaunch(){
        Button buttonReturn = guideActivity.findViewById(R.id.buttonReturn);
        ScrollView scrollView2 = guideActivity.findViewById(R.id.scrollView2);
        TextView titleText = guideActivity.findViewById(R.id.titleText);
        buttonReturn.performClick();
        assertNotNull(buttonReturn);
        assertNotNull(scrollView2);
        assertNotNull(titleText);
    }

    @After
    public void tearDown() {
        guideActivity = null;
    }
}