/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.adanvace.activity.viewpager.sourcecode;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter {
    private final DataSetObservable mObservable = new DataSetObservable();
    private DataSetObserver mViewPagerObserver;

    public static final int POSITION_UNCHANGED = -1;
    public static final int POSITION_NONE = -2;

    /**
     * Return the number of views available.
     */
    public abstract int getCount();

    /**
     * Called when a change in the shown pages is going to start being made.
     * @param container The containing View which is displaying this adapter's
     * page views.
     */
    public void startUpdate(ViewGroup container) {
        startUpdate((View) container);
    }

    /**
     * Create the page for the given position.  The adapter is responsible
     * for adding the view to the container given here, although it only
     * must ensure this is done by the time it returns from
     * {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View in which the page will be shown.
     * @param position The page position to be instantiated.
     * @return Returns an Object representing the new page.  This does not
     * need to be a View, but can be some other container of the page.
     */
    public Object instantiateItem(ViewGroup container, int position) {
        return instantiateItem((View) container, position);
    }

    /**
     * Remove a page for the given position.  The adapter is responsible
     * for removing the view from its container, although it only must ensure
     * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View from which the page will be removed.
     * @param position The page position to be removed.
     * @param object The same object that was returned by
     * {@link #instantiateItem(View, int)}.
     */
    public void destroyItem(ViewGroup container, int position, Object object) {
        destroyItem((View) container, position, object);
    }

    /**
     * Called to inform the adapter of which item is currently considered to
     * be the "primary", that is the one show to the user as the current page.
     *
     * @param container The containing View from which the page will be removed.
     * @param position The page position that is now the primary.
     * @param object The same object that was returned by
     * {@link #instantiateItem(View, int)}.
     */
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        setPrimaryItem((View) container, position, object);
    }

    /**
     * Called when the a change in the shown pages has been completed.  At this
     * point you must ensure that all of the pages have actually been added or
     * removed from the container as appropriate.
     * @param container The containing View which is displaying this adapter's
     * page views.
     */
    public void finishUpdate(ViewGroup container) {
        finishUpdate((View) container);
    }

    /**
     * Called when a change in the shown pages is going to start being made.
     * @param container The containing View which is displaying this adapter's
     * page views.
     *
     * @deprecated Use {@link #startUpdate(ViewGroup)}
     */
    @Deprecated
    public void startUpdate(View container) {
    }

    /**
     * Create the page for the given position.  The adapter is responsible
     * for adding the view to the container given here, although it only
     * must ensure this is done by the time it returns from
     * {@link #finishUpdate(ViewGroup)}.
     *
     * @param container The containing View in which the page will be shown.
     * @param position The page position to be instantiated.
     * @return Returns an Object representing the new page.  This does not
     * need to be a View, but can be some other container of the page.
     *
     * @deprecated Use {@link #instantiateItem(ViewGroup, int)}
     */
    @Deprecated
    public Object instantiateItem(View container, int position) {
        throw new UnsupportedOperationException(
                "Required method instantiateItem was not overridden");
    }

    @Deprecated
    public void destroyItem(View container, int position, Object object) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }


    @Deprecated
    public void setPrimaryItem(View container, int position, Object object) {
    }

    @Deprecated
    public void finishUpdate(View container) {
    }

    public abstract boolean isViewFromObject(View view, Object object);

    /**
     * Save any instance state associated with this adapter and its pages that should be
     * restored if the current UI state needs to be reconstructed.
     *
     * @return Saved state for this adapter
     */
    public Parcelable saveState() {
        return null;
    }

    /**
     * Restore any instance state associated with this adapter and its pages
     * that was previously saved by {@link #saveState()}.
     *
     * @param state State previously saved by a call to {@link #saveState()}
     * @param loader A ClassLoader that should be used to instantiate any restored objects
     */
    public void restoreState(Parcelable state, ClassLoader loader) {
    }


    public int getItemPosition(Object object) {
        return POSITION_UNCHANGED;
    }

    /**
     * This method should be called by the application if the data backing this adapter has changed
     * and associated views should update.
     */
    public void notifyDataSetChanged() {
        synchronized (this) {
            if (mViewPagerObserver != null) {
                mViewPagerObserver.onChanged();
            }
        }
        mObservable.notifyChanged();
    }

    /**
     * Register an observer to receive callbacks related to the adapter's data changing.
     *
     * @param observer The {@link DataSetObserver} which will receive callbacks.
     */
    public void registerDataSetObserver(DataSetObserver observer) {
        mObservable.registerObserver(observer);
    }

    /**
     * Unregister an observer from callbacks related to the adapter's data changing.
     *
     * @param observer The {@link DataSetObserver} which will be unregistered.
     */
    public void unregisterDataSetObserver(DataSetObserver observer) {
        mObservable.unregisterObserver(observer);
    }

    void setViewPagerObserver(DataSetObserver observer) {
        synchronized (this) {
            mViewPagerObserver = observer;
        }
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    public CharSequence getPageTitle(int position) {
        return null;
    }

    /**
     * Returns the proportional width of a given page as a percentage of the
     * ViewPager's measured width from (0.f-1.f]
     *
     * @param position The position of the page requested
     * @return Proportional width for the given page position
     */
    public float getPageWidth(int position) {
        return 1.f;
    }
}