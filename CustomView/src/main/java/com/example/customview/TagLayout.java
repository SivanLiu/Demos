package com.example.customview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class TagLayout extends ViewGroup {

    private List<Rect> childrenBounds = new ArrayList<>();
    private Context context;

    public TagLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthUsed = 0;
        int heightUsed = 0;
        int lineMaxHeight = 0;
        int lineWidthUsed = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            if (specMode != MeasureSpec.UNSPECIFIED &&
                    lineWidthUsed + childView.getMeasuredWidth() > specWidth) {
                lineWidthUsed = 0;
                heightUsed += lineMaxHeight;
                lineMaxHeight = 0;
                measureChildWithMargins(childView, widthMeasureSpec, 0, heightMeasureSpec, heightUsed);
            }
            Rect childBound;
            if (childrenBounds.size() <= i) {
                childBound = new Rect();
                childrenBounds.add(childBound);
            } else {
                childBound = childrenBounds.get(i);
            }
            childBound.set(lineWidthUsed, heightUsed, lineWidthUsed + childView.getMeasuredWidth(),
                    heightUsed + childView.getMeasuredHeight());
            lineWidthUsed += childView.getMeasuredWidth();
//            heightUsed += childView.getMeasuredHeight();
            widthUsed = Math.max(widthUsed, lineWidthUsed);
            lineMaxHeight = Math.max(lineMaxHeight, childView.getMeasuredHeight());
        }
        int width = widthUsed;
        int height = heightUsed + lineMaxHeight;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            Rect children = childrenBounds.get(i);
            childView.layout(children.left, children.top, children.right, children.bottom);
//            if(i ==0){
//                childView.layout(left, top, (left+right)/2, (top+bottom)/2);
//            }else {
//                childView.layout((left+right)/2, (top+bottom)/2, right, bottom);
//            }
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(context, attrs);
    }
}
//            LayoutParams layoutParams = childView.getLayoutParams();
//            int specWidthMode = MeasureSpec.getMode(widthMeasureSpec);
//            int specWidthSize = MeasureSpec.getSize(widthMeasureSpec);
//            int childWidthMode;
//            int childWidthSize;
//            int childWidthSpec = 0;
//            switch (layoutParams.width) {
//                case LayoutParams.MATCH_PARENT:
//                    switch (specWidthMode) {
//                        case MeasureSpec.EXACTLY:
//                        case MeasureSpec.AT_MOST:
//                            childWidthMode = MeasureSpec.EXACTLY;
//                            childWidthSize = specWidthSize - usedWidth;
//                            childWidthSpec = MeasureSpec.makeMeasureSpec(childWidthSize, childWidthMode);
//                            break;
//                            case MeasureSpec.UNSPECIFIED:
//                                childWidthMode = MeasureSpec.UNSPECIFIED;
//                                childWidthSize = 0;
//                                childWidthSpec = MeasureSpec.makeMeasureSpec(childWidthSize, childWidthMode);
//                                break;
//                    }
//                    break;
//                case LayoutParams.WRAP_CONTENT:
//                    break;
//                default:
//                    break;
//            }
//
//            childView.measure(childWidthSpec, childHeightSpec);