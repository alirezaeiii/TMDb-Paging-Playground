package com.sample.android.tmdb.widget

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.GridLayoutAnimationController
import kotlin.properties.Delegates

class AutofitRecyclerView : RecyclerView {

    var manager:GridLayoutManager by Delegates.notNull()
    private var columnWidth = -1

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        if (attrs != null){
            val attrsArray = intArrayOf(android.R.attr.columnWidth)
            val ta = context.obtainStyledAttributes(attrs, attrsArray)
            columnWidth = ta.getDimensionPixelSize(0, -1)
            ta.recycle()
        }

        manager = GridLayoutManager(context, 1)
        layoutManager = manager
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        if (columnWidth > 0) {
            val spanCount = Math.max(1, measuredWidth / columnWidth)
            manager.spanCount = spanCount
        }
    }

    override fun attachLayoutAnimationParameters(child: View, params: ViewGroup.LayoutParams,
                                                 index: Int, count: Int) {
        val layoutManager = layoutManager
        if (adapter != null && layoutManager is GridLayoutManager) {

            var animationParams: GridLayoutAnimationController.AnimationParameters? =
                    params.layoutAnimationParameters as? GridLayoutAnimationController.AnimationParameters

            if (animationParams == null) {
                animationParams = GridLayoutAnimationController.AnimationParameters()
                params.layoutAnimationParameters = animationParams
            }

            val columns = layoutManager.spanCount

            animationParams.count = count
            animationParams.index = index
            animationParams.columnsCount = columns
            animationParams.rowsCount = count / columns

            val invertedIndex = count - 1 - index
            animationParams.column = columns - 1 - invertedIndex % columns
            animationParams.row = animationParams.rowsCount - 1 - invertedIndex / columns

        } else {
            super.attachLayoutAnimationParameters(child, params, index, count)
        }
    }
}