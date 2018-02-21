package it.find.com.call.utils;

import android.animation.Animator;
import android.view.View;

/**
 * Created by Bruno on 08-Feb-18.
 */

public class AnimatorListener implements Animator.AnimatorListener{

    private View element;
    private boolean shouldHide;

    public AnimatorListener(View element, boolean shouldHide) {
        this.element = element;
        this.shouldHide = shouldHide;
    }

    public AnimatorListener() {
    }

    public View getElement() {
        return element;
    }

    public void setElement(View element) {
        this.element = element;
    }

    public boolean isShouldHide() {
        return shouldHide;
    }

    public void setShouldHide(boolean shouldHide) {
        this.shouldHide = shouldHide;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        if (!shouldHide) {
            element.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (shouldHide) {
            element.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
