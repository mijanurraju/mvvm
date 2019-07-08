package com.iventurebd.mymvvm.utils.widget

import android.content.Context
import com.google.android.flexbox.FlexboxLayoutManager

class UnscrollableFlexboxLayoutManager(context: Context) : FlexboxLayoutManager(context) {
    override fun canScrollVertically(): Boolean = false
    override fun canScrollHorizontally(): Boolean = false
}