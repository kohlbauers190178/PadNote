package net.htlgkr.kohlbauers190178.padnote.util;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class MyDatePicker {

    private long date;

    MaterialDatePicker<Long> datePicker;

    public MyDatePicker() {
        datePicker = MaterialDatePicker.Builder.datePicker().setTitleText("Select date (Optional)").build();
        datePicker.addOnPositiveButtonClickListener(this::setDate);
    }


    public void setDatePickerListener(MaterialPickerOnPositiveButtonClickListener<Long> materialPickerOnPositiveButtonClickListener) {
        this.datePicker.clearOnPositiveButtonClickListeners();
        this.datePicker.addOnPositiveButtonClickListener(materialPickerOnPositiveButtonClickListener);
    }

    public MaterialDatePicker<Long> getDatePicker() {
        return datePicker;
    }

    public void showDatePicker(FragmentManager fragmentManager) {
        datePicker.show(fragmentManager, "");
    }


    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
