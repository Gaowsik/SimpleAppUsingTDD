package com.example.simpleappdevelopmentusingtdd


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.Description
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaListAssertions.assertListItemCount
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Matchers.allOf

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PlayListFeature {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayScreenTitle() {
        onView(withText("Playlists"))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayListOfPlaylists() {
        assertListItemCount(R.id.recycle_list_playlist, 10)
        onView(
            allOf(
                withId(R.id.textPlaylistName),
                isDescendantOfA(nthChildOf(withId(R.id.recycle_list_playlist), 0))
            )
        ).check(matches(withText("Hard Rock Cafe"))).check(
            matches(isDisplayed())

        )

        onView(
            allOf(
                withId(R.id.textPlayListCategory),
                isDescendantOfA(nthChildOf(withId(R.id.recycle_list_playlist), 0))
            )
        ).check(matches(withText("rock"))).check(
            matches(isDisplayed())

        )

        onView(
            allOf(
                withId(R.id.imageView),
                isDescendantOfA(nthChildOf(withId(R.id.recycle_list_playlist), 0))
            )
        ).check(matches(withDrawable(R.mipmap.ic_launcher))).check(
            matches(isDisplayed())

        )


    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }


}

