package cn.edu.niit.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by liaozq on 2017/3/1.  //为避免重复代码封装为抽象类
 */

public abstract class SingleFragmentActivity extends AppCompatActivity{
   //AppCompatActivity是FragmentActivity的子类
    protected  abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //以代码方式将fragment添加到Activity   P127//FragmentTransaction frgTrtion = fm.beginTransaction();
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        //创建一个新的Fragment事务，加入一个添加操作，然后提交该事务
        if(fragment == null){    //当指定 id容器中 的fragment不存在时,(上一行代码)
            fragment =  createFragment();  //new CrimeFragment();
            fm.beginTransaction()        //创建并返回Fragment事务 实例，由此得到一个fragment事务队列
                    .add(R.id.fragment_container,fragment)   //容器ID，和添加一個事务
                    .commit();

        }
    }

}
