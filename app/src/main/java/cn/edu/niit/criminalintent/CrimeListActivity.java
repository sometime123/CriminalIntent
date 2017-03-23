package cn.edu.niit.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by liaozq on 2017/3/1.
 */

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
