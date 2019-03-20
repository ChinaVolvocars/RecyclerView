package com.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.recyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private List<User> userList;
  private UserAdapter userAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button btnNotifyItemChanged = findViewById(R.id.btn_notify_item_changed);


    RecyclerView recyclerView = findViewById(R.id.recycler_view);

    //初始化数据

    userList = new ArrayList<User>();
    for (int i = 0; i < 10; i++) {
      User user = new User();
      user.setAge(String.valueOf(i));
      user.setName("士兵76：" + i);
      userList.add(user);
    }


    userAdapter = new UserAdapter();
    userAdapter.setData(userList);
    recyclerView.setAdapter(userAdapter);
    //添加分隔线
    RecyclerView.ItemDecoration itemDecoration =
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    recyclerView.addItemDecoration(itemDecoration);
    //设置布局管理器
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    //瀑布流
//    StaggeredGridLayoutManager gridLayoutManager =
//            new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//    recyclerView.setLayoutManager(gridLayoutManager);

    GridLayoutManager layoutManage = new GridLayoutManager(this, 2);
    recyclerView.setLayoutManager(layoutManage);


    btnNotifyItemChanged.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        User user = new User();
        user.setAge(String.valueOf(745));
        user.setName("插入一条数据：" + 745);
        userList.add(0, user);
        userAdapter.notifyItemInserted(0);
        userAdapter.notifyDataSetChanged();
      }
    });
    userAdapter.setOnClickListener(new UserAdapter.OnClickListener() {
      @Override
      public void onItemClick(View v, int position) {
        Toast.makeText(MainActivity.this, "点击:" + position, Toast.LENGTH_SHORT).show();
      }
    });
  }


}
