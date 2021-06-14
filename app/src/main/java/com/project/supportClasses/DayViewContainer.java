package com.project.supportClasses;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.kizitonwose.calendarview.ui.ViewContainer;
import com.project.integratedservices.R;

public class DayViewContainer extends ViewContainer {
    public DayViewContainer(View view) {
        super(view);

       final AppCompatTextView textView = view.findViewById(R.id.calendarDayText);
    }
}
