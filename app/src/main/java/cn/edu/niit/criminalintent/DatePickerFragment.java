package cn.edu.niit.criminalintent;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by liaozq on 2017/3/8.
 */

public class DatePickerFragment extends DialogFragment {


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_date,null);


        //1：创建  内容视图和带标题栏和Ok按钮的dialog,没有数据传递
        return new AlertDialog.Builder(getActivity()).setView(v).setTitle(R.string.date_piker_title)
                  .setPositiveButton(android.R.string.ok,null).create();

    }

}
