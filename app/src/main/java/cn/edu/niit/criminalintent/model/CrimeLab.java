package cn.edu.niit.criminalintent.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



/**
 * Created by liaozq on 2017/3/1.   模型层：新增Crimelab对象，他是一个数据集中存储池，用来存储crime对象。
 * 单例模型：保证该类中的对象的唯一性：1.建立私有静态对象* 2.不让其他类建立该类对象* 3.对外提供对对象
 */

public class CrimeLab {
   //静态变量：1可以通过“类.变量”来引用。2程序运行时分配内存。3只有一份内存被分配。4静态变量定义后必须声明初始化
    private  static CrimeLab sCrimeLab;
    private List<Crime> mCrimes;                                            //保存Crime对象；



    public static CrimeLab get(Context context){                            //创建get（）方法
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(context);                               //调用构造方法
        }
        return sCrimeLab;
    }


      //通过将构造方法限定为private使他类无法创建CrimeLab对象，唯一实例只能通过List<Crime> getCrimes()方法访问。
    private CrimeLab(Context context){                                  //私有构造方法中用空的list保存Crime对象。



             mCrimes = new ArrayList<>();
             for(int i = 0; i < 100 ; i++){
                 Crime crime = new Crime();
                 crime.setTitle("陋习#"+i);
                 crime.setSolved(i % 2 == 0);                            //随几决定f,true
                 mCrimes.add(crime);
             }
    }


    public List<Crime> getCrimes(){                                     //返回数组列表
        return  mCrimes;
    }
    public Crime getCrime(UUID id){                                     //返回指定Id的Crime对象
        for(Crime crime : mCrimes){
            if(crime.getId().equals(id)){
                return  crime;
            }
        }
        return null;
    }
}
