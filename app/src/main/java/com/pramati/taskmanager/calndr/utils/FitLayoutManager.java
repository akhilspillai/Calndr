package com.pramati.taskmanager.calndr.utils;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by akhil on 8/3/16.
 * This layout manager aligns children properly in a horizontal manner
 */
public class FitLayoutManager extends RecyclerView.LayoutManager {

    private Context context;
    private Rect[] layoutInfo;
    private int horizontalScrollOffset = 0;
    private int widthSize;

    public FitLayoutManager(Context context) {
        this.context = context;
        layoutInfo = new Rect[7];
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state,
                          int widthSpec, int heightSpec) {
        super.onMeasure(recycler, state, widthSpec, heightSpec);
        widthSize = View.MeasureSpec.getSize(widthSpec);
        int heightSize = View.MeasureSpec.getSize(heightSpec);

        int childWidth = widthSize  / 5;

        layoutInfo[0] = new Rect();
        layoutInfo[0].left = 0;
        layoutInfo[0].top = 0;
        layoutInfo[0].right = childWidth;
        layoutInfo[0].bottom = heightSize;
        for (int i = 1 ; i < 7 ; i++) {
            layoutInfo[i] = new Rect();
            layoutInfo[i].left = layoutInfo[i - 1].right;
            layoutInfo[i].top = 0;
            layoutInfo[i].right = layoutInfo[i].left + widthSize;
            layoutInfo[i].bottom = heightSize;
        }

    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        fillVisibleChildren(recycler);
    }

    private void fillVisibleChildren(RecyclerView.Recycler recycler) {
        //before we layout child views, we first scrap all current attached views
        detachAndScrapAttachedViews(recycler);

        //layoutInfo is a Rect[], each element contains coordinates for a view.
        for(int i = 0; i < layoutInfo.length; i++){
            if(isVisible(i)){
                View view = recycler.getViewForPosition(i);
                addView(view);
                layoutDecorated(view, layoutInfo[i].left, layoutInfo[i].top, layoutInfo[i].right, layoutInfo[i].bottom);
            }
        }
    }

    private boolean isVisible(int index) {
        return layoutInfo[index].right < horizontalScrollOffset
                || layoutInfo[index].left > widthSize + horizontalScrollOffset;
    }

    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int travel;
        final int leftLimit = 0;
        final int rightLimit = findRightLimit(); //a helper method to find the rightmost child's right side.
        if(dx + horizontalScrollOffset < leftLimit){
            travel = horizontalScrollOffset;
            horizontalScrollOffset = leftLimit;
        }
        else if(dx + horizontalScrollOffset + widthSize > rightLimit){
            travel = rightLimit - horizontalScrollOffset - widthSize;
            horizontalScrollOffset = rightLimit - widthSize;
        }
        else{
            travel = dx;
            horizontalScrollOffset += dx;
        }
        offsetChildrenHorizontal(travel);
        fillVisibleChildren(recycler);
        return travel;
    }

    private int findRightLimit() {
        return layoutInfo[layoutInfo.length - 1].right;
    }
}
