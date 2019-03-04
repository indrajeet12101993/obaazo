package com.ansh.obaazo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ansh.obaazo.R;
import com.ansh.obaazo.utils.AppConstant;
import com.ansh.obaazo.utils.PreferencesUtils;
import com.savvi.rangedatepicker.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ActivityDateSelecte extends BaseActivity {

    private CalendarPickerView calendar;
    private String TAG = ActivityDateSelecte.class.getSimpleName();
    private TextView tvStartDate;
    private TextView tvEndDate;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    private SimpleDateFormat monthFormat = new SimpleDateFormat("dd MMM yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Calendar today = Calendar.getInstance();
        Date startDate = today.getTime();


        final Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 10);

        final Calendar lastYear = Calendar.getInstance();
        // lastYear.add(Calendar.YEAR, -10);
        calendar = (CalendarPickerView) findViewById(R.id.calendar_view);
        // calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMMM, YYYY", Locale.getDefault()));

        calendar.init(lastYear.getTime(), nextYear.getTime(), new SimpleDateFormat("MMM yyyy", Locale.getDefault())) //
                .inMode(CalendarPickerView.SelectionMode.RANGE) //
                .withSelectedDate(new Date());
        calendar.setOnInvalidDateSelectedListener(new CalendarPickerView.OnInvalidDateSelectedListener() {
            @Override
            public void onInvalidDateSelected(Date date) {

            }
        });

        calendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                Log.e(TAG, "onDateSelected: ");
                List<Date> selectedDates = calendar.getSelectedDates();
                if (selectedDates.size() > 1) {
                    Date endDate = selectedDates.get(0);
                    Date startDate = selectedDates.get(selectedDates.size() - 1);
                    PreferencesUtils.putString(AppConstant.START_DATE, simpleDateFormat.format(endDate));
                    PreferencesUtils.putString(AppConstant.END_DATE, simpleDateFormat.format(startDate));
                    tvEndDate.setText(monthFormat.format(startDate));
                    tvStartDate.setText(monthFormat.format(endDate));

                } else {
                    Date endDate = selectedDates.get(0);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(endDate);
                    calendar.add(Calendar.DATE, 1);
                    Date startDate = calendar.getTime();
                    tvStartDate.setText(monthFormat.format(endDate));
                    tvEndDate.setText("End Date");
                    PreferencesUtils.putString(AppConstant.START_DATE, simpleDateFormat.format(endDate));
                    PreferencesUtils.putString(AppConstant.END_DATE, simpleDateFormat.format(startDate));
                    tvEndDate.setText(monthFormat.format(startDate));
                    tvStartDate.setText(monthFormat.format(endDate));
                }
            }

            @Override
            public void onDateUnselected(Date date) {
                Log.e(TAG, "onDateUnselected: ");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_date_selecte;
    }

    @Override
    protected void initView() {
        initCustomToolbar();
        tvStartDate = findViewById(R.id.tv_end_date);
        tvEndDate = findViewById(R.id.tv_start_date);

    }

    @Override
    protected void initListener() {
        findViewById(R.id.btn_done).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(PreferencesUtils.getString(AppConstant.START_DATE))
                        && !TextUtils.isEmpty(PreferencesUtils.getString(AppConstant.END_DATE))) {
                    setResult(Activity.RESULT_OK);
                    onBackPressed();
                } else {
                    Toast.makeText(ActivityDateSelecte.this, "First Select start and end date", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    protected void bindDataWithUi() {

    }
}
