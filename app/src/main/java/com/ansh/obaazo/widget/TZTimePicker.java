package com.ansh.obaazo.widget;

import android.app.Activity;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.Locale;

public class TZTimePicker implements TimePickerDialog.OnTimeSetListener {
    private TZDatePicker.PickerCallback pickerCallback;
    private TimePickerDialog timePickerDialog;

/*    public TZTimePicker(Activity mActivity, TZDatePicker.PickerCallback pickerCallback) {
        this.pickerCallback = pickerCallback;
        Calendar now = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(mActivity, this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), false);
        timePickerDialog.show();
    }*/

   /* @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        boolean isPM = (hourOfDay >= 12);
        pickerCallback.onSelect(String.format(Locale.getDefault(), "%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"));
    }*/


    public TZTimePicker(Activity mActivity, TZDatePicker.PickerCallback pickerCallback) {
        this.pickerCallback = pickerCallback;
        Calendar now = Calendar.getInstance();
        TimePickerDialog dpd = TimePickerDialog.newInstance(this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
        dpd.setMinTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), 0);
        //dpd.setMaxTime();
        dpd.show(mActivity.getFragmentManager(), TZTimePicker.class.getSimpleName());
    }

    public TZTimePicker(Activity mActivity, boolean isBackTimeEnable, TZDatePicker.PickerCallback pickerCallback) {
        this.pickerCallback = pickerCallback;
        Calendar now = Calendar.getInstance();
        TimePickerDialog dpd = TimePickerDialog.newInstance(this, now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), true);
        if (isBackTimeEnable) {
            dpd.setMinTime(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), 0);
        }
        //dpd.setMaxTime();
        dpd.show(mActivity.getFragmentManager(), TZTimePicker.class.getSimpleName());
    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        boolean isPM = (hourOfDay >= 12);
        pickerCallback.onSelect(String.format(Locale.getDefault(), "%02d:%02d %s", (hourOfDay == 12 || hourOfDay == 0) ? 12 : hourOfDay % 12, minute, isPM ? "PM" : "AM"), false);
    }
}
