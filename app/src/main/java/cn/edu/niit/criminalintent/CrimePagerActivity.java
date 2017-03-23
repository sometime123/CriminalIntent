package cn.edu.niit.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

import cn.edu.niit.criminalintent.model.Crime;
import cn.edu.niit.criminalintent.model.CrimeLab;

/**
 * Created by liaozq on 2017/3/7.
 */

public class CrimePagerActivity extends AppCompatActivity {
    private static final String EXTRA_CRIME_ID = "crime_id";

    private ViewPager mViewPager;
    private List<Crime> mCrimes;
    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent  = new Intent(packageContext,CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID,crimeId);
        return  intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();                                 //从CrimeLab中获取数据集list
        FragmentManager fragmentManager = getSupportFragmentManager();           //获取activity的frgManger实例
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            //匿名实例是adapter的代理  ：负责管理Viewpager
            @Override      //将返回的fragment添加给activity，然后才能使frgment完成自己的工作。添加视图到viewpager
            public Fragment getItem(int position) {
               Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());       //利用实例Id创建fragment并返回
            }

            @Override  //返回数组列表中列表项数目
            public int getCount() {
                return mCrimes.size();
            }
        });

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);  //？？

        for(int i = 0;i<mCrimes.size();i++){          //循环检查crime的id,如果Crime实例的id与intent extra中Id匹配
            if(mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);                     //设定显示指定列表
                break;
            }
        }

    }
}
