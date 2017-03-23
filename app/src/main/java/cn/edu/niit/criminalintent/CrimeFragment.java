package cn.edu.niit.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

import cn.edu.niit.criminalintent.model.Crime;
import cn.edu.niit.criminalintent.model.CrimeLab;

/**
 * Created by liaozq on 2017/2/28.  控制器层
 * 模型与视图对象交互的控制器，用于显示特定Crime的明细信息
 */

public class CrimeFragment extends Fragment {
       public static  final String ARG_CRIME_ID = "crime_id";
       public static  final String DIALOG_DATE  = "DialogDate";
       private Crime mCrime;
       private EditText mTitleField;
       private CheckBox mSolvedCheckBox;
       private Button mDateButton;

    @Override  //Fragment的onCreate是public公共方法，CrimeActivity需要调用 而《Activity中的onCreate是保护方法protected》
    public void onCreate(@Nullable Bundle savedInstanceState) {     //P123
        super.onCreate(savedInstanceState);
        // mCrime = new Crime();
        UUID crimeId = (UUID) getArguments().getSerializable(ARG_CRIME_ID);   //从fragmtd的argument中获取数据UUID
        mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);


    }
     //采用静态方法：附加(bundle)argument给fragment-->获取intent中的数据  (fragment创建后，添加给activity前)
    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();                                 //使用Bundle封装数据(类似Map)
        args.putSerializable(ARG_CRIME_ID,crimeId);                //用序列化 传递 对象实例putSerializable(Key,Object)
        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);                               //将(Bundle)argument附加给fragment
        return fragment;
    }
    //覆盖onActivityResult




    @Nullable
    @Override //实例化 视图 布局，然后将 视图放回给托管Activity.
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //fragment视图是  直接通过调用LayoutInflater.inflate方法并传入布局ID生成的；inflate()的作用就是将一个用xml定义的布局文件查找出来
        //第二个参数是 视图的父视图；  第三个参数：告知布局是否将生成的视图添加给父视图（这里false,会以Activity代码添加）

         View v = inflater.inflate(R.layout.fragment_crime,container,false);


        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);     //frgment类：P125页
        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);

        mDateButton.setText(mCrime.getDate().toString());
        mTitleField.setText(mCrime.getTitle());
        mSolvedCheckBox.setChecked(mCrime.isSolved());


        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int after) {

            }

            @Override  //1:用户输入，2
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mCrime.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }
        });
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                 DatePickerFragment dialog = new DatePickerFragment();//用newInstance（）替换构造方法
                //设置目标Fragment  p205  建立联系
                dialog.show(manager,DIALOG_DATE);
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

}
