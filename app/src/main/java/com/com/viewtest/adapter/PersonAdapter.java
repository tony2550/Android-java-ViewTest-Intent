package com.com.viewtest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.com.viewtest.MainActivity;
import com.com.viewtest.R;
import com.com.viewtest.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    private static final String TAG = "PersonAdapter2";
    private PersonAdapter personAdapter = this;
    private MainActivity mContext;

    public PersonAdapter(MainActivity mContext) {
        this.mContext=mContext;
    }


    // 3. 컬렉션
    private List<Person> persons = new ArrayList<>(); // private는 행위를 통해서 상태를 변경하기 위해 사용

    // 4. 컬렉션 데이터 세팅
    public void addItems(List<Person> persons) {
        this.persons=persons;
        notifyDataSetChanged(); // 데이터를 변경하는 곳에는 붙혀 줄것
    }

    public void addItem(Person person) {// 모듈화
        this.persons.add(person); // 데이터 추가
        notifyDataSetChanged(); // ui 동기화
        mContext.mRvScroll(); // 스크롤 동기화
    }

    public List<Person> getItems() {
        return persons;
    }

    public void removeItem(int index) {
        this.persons.remove(index);
        notifyDataSetChanged();
    }

    // ViewHolder 객체 만드는 친구
    // 콜백 함수
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.person_item, parent, false);

        return new MyViewHolder(view);
    }
    // ViewHolder 데이터 갈아끼우는 친구
    @Override
    public void onBindViewHolder(PersonAdapter.MyViewHolder holder, int position) {
        Person person = persons.get(position);
        holder.setItem(person);
    }

    // 어댑터가 알아서 호출해서 사이즈를 계산한다.
    @Override
    public int getItemCount() {
        return persons.size();
    }

    //1. 내부 클래스로 뷰홀더 만들기
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName, tvTel;

        public MyViewHolder(View itemView) { // view를 들고와서 갈아 끼우는 역할 생성자
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvTel = itemView.findViewById(R.id.tvTel);

            initListener();
        }

        private void initListener(){
            itemView.setOnClickListener(v -> { // 람다는 현재클래스의 바로 위 클래스를 바인딩 해준다 부모의 레퍼런스를 같이 가져온다
                Log.d(TAG, "initListener: "+ getAdapterPosition());
                int index = getAdapterPosition();
                Log.d(TAG, "initListener: "+ personAdapter.getItems().get(index).getName());
                personAdapter.removeItem(index);
//                TextView t = v.findViewById(R.id.tvName);
//                t.getText();

            });
        }

        public void setItem(Person person){
            tvName.setText(person.getName());
            tvTel.setText(person.getTel());
        }
    }
}
