package com.sample.android.tmdb.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Outline
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.view.ViewOutlineProvider
import com.google.android.material.shape.CutCornerTreatment
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapePathModel
import com.sample.android.tmdb.R
import com.sample.android.tmdb.util.lerp

class TopLeftCutoutBackgroundView : View {
    private val shapeDrawable = MaterialShapeDrawable()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.TopLeftCutoutBackgroundView)
        color = a.getColor(R.styleable.TopLeftCutoutBackgroundView_backgroundColor, Color.MAGENTA)
        maxCutSize = a.getDimension(R.styleable.TopLeftCutoutBackgroundView_topLeftCutSize, 0f)
        a.recycle()

        background = shapeDrawable
        syncCutSize()

        outlineProvider = MaterialShapeDrawableOutlineProvider(shapeDrawable)
    }

    var color: Int = Color.MAGENTA
        set(value) {
            shapeDrawable.setTint(value)
            field = value
        }

    var maxCutSize: Float = 0f
        set(value) {
            field = value
            syncCutSize()
        }

    var cutProgress: Float = 1f
        set(value) {
            if (value != field) {
                field = value
                syncCutSize()
            }
        }

    private fun syncCutSize() {
        val shapeModel = shapeDrawable.shapedViewModel ?: ShapePathModel()
        shapeModel.topLeftCorner = CutCornerTreatment(lerp(0f, maxCutSize, cutProgress))
        shapeDrawable.shapedViewModel = shapeModel
    }

    class MaterialShapeDrawableOutlineProvider(
            private val shapeDrawable: MaterialShapeDrawable
    ) : ViewOutlineProvider() {
        private val path = Path()

        override fun getOutline(view: View, outline: Outline) {
            shapeDrawable.getPathForSize(view.width, view.height, path)
            if (path.isConvex) {
                outline.setConvexPath(path)
            } else {
                outline.setRect(0, 0, view.width, view.height)
            }
        }
    }
}