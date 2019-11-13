package burrellyang.calculatorapp;

import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.Button;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;

    @Before
    public void setUp() {

        mActivity = mActivityTestRule.getActivity();
    }

    @Test @UiThreadTest
    public void testLaunch(){
        Button button = mActivity.findViewById(R.id.myButton);
        button.performClick();
        mActivity.jumpToCalculator();
        assertNotNull(button);
    }

    @After
    public void tearDown() {
        mActivity = null;
    }

}