package com.example.mysamplevendorrequest.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.example.mysamplevendorrequest.R;


public class SearchEditText extends AppCompatEditText implements View.OnTouchListener, View.OnFocusChangeListener {

    private Drawable clearDrawable;
    private OnFocusChangeListener onFocusChangeListener;
    private OnTouchListener onTouchListener;

    public SearchEditText(final Context context) {
        super(context);
        init();
    }

    public SearchEditText(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchEditText(final Context context, final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.onFocusChangeListener = onFocusChangeListener;
    }

    @Override
    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.onTouchListener = onTouchListener;
    }

    @Override
    public void addTextChangedListener(final TextWatcher watcher) {
        super.addTextChangedListener(new TextWatcher() {
            @Override
            public final void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
                if (isFocused()) {
                    setClearIconVisible(s.length() > 0);
                }
                watcher.onTextChanged(s,start,before,count);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                watcher.beforeTextChanged(s,start,count,after);
            }

            @Override
            public void afterTextChanged(Editable s) {
                watcher.afterTextChanged(s);
            }

        });
    }

    private void init() {

        Drawable tempDrawable = ContextCompat.getDrawable(getContext(), R.drawable.search_cancel);
        clearDrawable = DrawableCompat.wrap(tempDrawable);
        DrawableCompat.setTintList(clearDrawable, getHintTextColors());
        clearDrawable.setBounds(0, 0, clearDrawable.getIntrinsicWidth(), clearDrawable.getIntrinsicHeight());

        setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        setClearIconVisible(false);


        super.setOnTouchListener(this);
        super.setOnFocusChangeListener(this);
    }


    @Override
    public void onFocusChange(final View view, final boolean hasFocus) {
        if (hasFocus) {
            setClearIconVisible(getText().length() > 0);
        } else {
            setClearIconVisible(false);
        }

        if (onFocusChangeListener != null) {
            onFocusChangeListener.onFocusChange(view, hasFocus);
        }
    }


    @Override
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int x = (int) motionEvent.getX();
        if (clearDrawable.isVisible() && x > getWidth() - getPaddingRight() - clearDrawable.getIntrinsicWidth()) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                setError(null);
                setText(null);
            }
            return true;
        }

        if (onTouchListener != null) {
            return onTouchListener.onTouch(view, motionEvent);
        } else {
            return false;
        }

    }


    private void setClearIconVisible(boolean visible) {
        clearDrawable.setVisible(visible, false);
        setCompoundDrawables(null, null, visible ? clearDrawable : null, null);
    }


}