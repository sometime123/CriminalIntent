package cn.edu.niit.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import cn.edu.niit.criminalintent.model.Crime;
import cn.edu.niit.criminalintent.model.CrimeLab;

/**
 * Created by liaozq on 2017/3/1.
 */

public class CrimeListFragment extends Fragment {
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    @Nullable
    @Override   //p162：RecyclerView回收再利用Textview,定位屏幕textview视图任务委托给LayoutManager
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
     //    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));    //设置布局管理器

        updateUI();   //將Adapter和RecyclerView綁定
        return  view;
    }

    //覆盖fragment的onResume方法刷新当前页面
    @Override
    public void onResume() {
        super.onResume();
        updateUI();      //更新
    }

    private void updateUI() {                                    //getActivity()代表宿主Activity
        CrimeLab crimelab = CrimeLab.get(getActivity());        //调用Crimelab类get()方法,返回静态变量
        List<Crime> crimes = crimelab.getCrimes();
       if(mAdapter==null){
        mAdapter  = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }else{
           mAdapter.notifyDataSetChanged();//怎样刷新具体单个Item?????
        //   mAdapter.notifyItemChanged(mCrimes.get(position));
       }

    }

    //定义ViewHolder内部类:容纳itemView视图-->显示crime
    private  class  CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;

        public CrimeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }
        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View view) {
           //   Intent intent = new Intent(getActivity(),CrimeActivity.class);
          //  Intent intent = CrimeActivity.newIntent(getActivity(),mCrime.getId());
            Intent intent = CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{      //创建adapter内部类 p164
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        @Override  //创建View视图，然後，封裝到ViewHolder中。
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime,parent,false);  //parent改为null：
            return new CrimeHolder(view);
        }

        @Override   //把View视图和模型層綁定起來
        public void onBindViewHolder(CrimeHolder holder, int position) {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
