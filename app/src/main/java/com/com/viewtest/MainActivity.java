package com.com.viewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.com.viewtest.adapter.PersonAdapter;
import com.com.viewtest.model.Person;
import com.com.viewtest.provider.PersonProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

// 어댑터와 리사이클러뷰 연결
public class MainActivity extends AppCompatActivity {

    private MainActivity mContext = this; // private로 context
    private RecyclerView rvPersons;
    private RecyclerView.LayoutManager layoutManager; // 방향 설정을 위한 매니저
    private PersonAdapter personAdapter; // 어댑터 메모리에 띄우기
    private FloatingActionButton fabAdd; // 버튼 메모리에 띄우기

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initAdapter();
        initData();
        initListener();
    }

    private void initListener() {
        fabAdd.setOnClickListener(view -> {
            personAdapter.addItem(new Person("이름new","0101010"));
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder,RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int index = viewHolder.getAdapterPosition();
                personAdapter.removeItem(index);
            }
        }).attachToRecyclerView(rvPersons);
    }

    private void initAdapter(){ // 어댑터 관련 함수
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //컨택스트 개념!!
        rvPersons.setLayoutManager(layoutManager);
        personAdapter = new PersonAdapter(mContext);
        rvPersons.setAdapter(personAdapter); // 어댑터 리사이클러뷰 연결
    }


    private void initData() { // 데이터 세팅 함수
        PersonProvider p = new PersonProvider();
        personAdapter.addItems(p.findAll());

    }
    // 반복되는건 인터페이스 만들기
    private void init(){ // 뷰연결
        rvPersons = findViewById(R.id.rvPersons);
        fabAdd = findViewById(R.id.fabAdd);

    }

    public void mRvScroll() {
        rvPersons.scrollToPosition(personAdapter.getItemCount()-1);
    }
}