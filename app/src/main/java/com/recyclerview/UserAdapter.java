package com.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.recyclerview.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

  private List<User> userList;

  public UserAdapter() {
  }

  public void setData(List<User> userList) {
    this.userList = userList;
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
    return new UserViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
    User user = userList.get(position);
    holder.tvName.setText(user.getName());
    holder.tvAge.setText(user.getAge());
  }

  @Override
  public int getItemCount() {
    return null == userList ? 0 : userList.size();
  }


  public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView tvName;
    public TextView tvAge;

    public UserViewHolder(@NonNull View itemView) {
      super(itemView);
      tvName = itemView.findViewById(R.id.tv_name);
      tvAge = itemView.findViewById(R.id.tv_age);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          if (listener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
              listener.onItemClick(v, position);
            }
          }

        }
      });
    }

  }

  private OnClickListener listener;

  public interface OnClickListener {
    void onItemClick(View v, int position);
  }

  public void setOnClickListener(@Nullable OnClickListener listener) {
    this.listener = listener;
  }
}
