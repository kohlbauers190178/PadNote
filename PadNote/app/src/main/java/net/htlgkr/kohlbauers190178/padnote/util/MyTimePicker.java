package net.htlgkr.kohlbauers190178.padnote.util;

import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class MyTimePicker {

    private MyTime myTime;

    private MaterialTimePicker timePicker;

    public MaterialTimePicker getTimePicker() {
        return timePicker;
    }

    public void showMyTimePicker(FragmentManager fragmentManager) {


        timePicker.show(fragmentManager, "");
    }

    public MyTimePicker() {
        timePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H).setHour(12).setMinute(10).setTitleText("Select Time").build();
        timePicker.addOnPositiveButtonClickListener(s -> setMyTime(new MyTime(timePicker.getHour(), timePicker.getMinute())));
    }

    public MyTimePicker(int hours, int minutes) {
        timePicker = new MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_12H).setHour(hours).setMinute(minutes).setTitleText("Select Time").build();
    }

    public void setTimePickerListener(View.OnClickListener listener){
        timePicker.clearOnCancelListeners();
        timePicker.addOnPositiveButtonClickListener(listener);
    }

    public MyTime getMyTime() {
        return myTime;
    }

    public void setMyTime(MyTime myTime) {
        this.myTime = myTime;
    }
}
