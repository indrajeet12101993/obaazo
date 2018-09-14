package com.ansh.obaazo.widget;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.widget.DatePicker;

import com.ansh.obaazo.R;

import java.util.Calendar;


public class TZDatePicker implements DatePickerDialog.OnDateSetListener {


    /**
     * 7 * 24 * 60 * 60 * 1000
     * for 7 days
     */
    private DatePickerDialog dpd;
    private PickerCallback pickerCallback;

    public TZDatePicker(Activity mActivity, boolean preventPastDate, PickerCallback pickerCallback) {
        Calendar now = Calendar.getInstance();
        this.pickerCallback = pickerCallback;
        dpd = new DatePickerDialog(mActivity, R.style.DialogThemePicker, this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        if (preventPastDate) {
            dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
        }
        dpd.show();
    }


    public TZDatePicker(Activity mActivity, boolean preventPastDate, PickerCallback pickerCallback, long minDate, long maxDate) {
        Calendar now = Calendar.getInstance();
        this.pickerCallback = pickerCallback;
        dpd = new DatePickerDialog(mActivity, R.style.DialogThemePicker, this, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        if (preventPastDate) {
            dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 10000);
        }
        dpd.getDatePicker().setMinDate(minDate);
        dpd.getDatePicker().setMaxDate(maxDate);
        dpd.show();
    }

    public void setDateValidation(long minDate, long maxDate) {
        if (minDate == 0) return;
        if (maxDate == 0) return;
        dpd.getDatePicker().setMinDate(minDate);
        dpd.getDatePicker().setMaxDate(maxDate);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        // pickerCallback.onSelect(year + "-" + (month + 1) + "-" + dayOfMonth);
        //  pickerCallback.onSelect(dayOfMonth + "/" + (month + 1) + "/" + year);

        Calendar now = Calendar.getInstance();
        boolean isCurrentDate = (now.get(Calendar.DATE) == dayOfMonth && now.get(Calendar.MONTH) == month && now.get(Calendar.YEAR) == year);
        pickerCallback.onSelect(dayOfMonth + "/" + (month + 1) + "/" + year, isCurrentDate);

        //  pickerCallback.onSelect((month + 1) + "/" + dayOfMonth + "/" + year, isCurrentDate);
    }


    public interface PickerCallback {
        void onSelect(String date, boolean isCurrentDate);
    }
}


