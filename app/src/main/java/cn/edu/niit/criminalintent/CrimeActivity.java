/*
package cn.edu.niit.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

//控制层
public class CrimeActivity extends SingleFragmentActivity{

    private static final String EXTRA_CRIME_ID ="com.bignerdranch.android.criminalintent.crime_id";

    public static Intent newIntent(Context packageContext, UUID cimeId){
            Intent intent = new Intent(packageContext,CrimeActivity.class);
            intent.putExtra(EXTRA_CRIME_ID,cimeId);
            return  intent;
    }

    @Override    //重写
    protected Fragment createFragment() {
        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);   //???
        return CrimeFragment.newInstance(crimeId);

       // return new CrimeFragment();
    }
}
*/
