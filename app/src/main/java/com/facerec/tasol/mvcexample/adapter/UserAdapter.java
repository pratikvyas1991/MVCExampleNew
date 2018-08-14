package com.facerec.tasol.mvcexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facerec.tasol.mvcexample.R;
import com.facerec.tasol.mvcexample.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tasol on 14/8/18.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    List<UserModel> userList =new ArrayList<>();
    Context mContext;
    public UserAdapter(List<UserModel> userList,Context context) {
        this.userList = userList;
        this.mContext = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_adapter_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        UserModel userModel =userList.get(position);
        holder.tvEmail.setText(userModel.getmUserEmail());
        holder.tvName.setText(userModel.getmUserName());
        holder.tvAge.setText(userModel.getmUserAge());

        holder.chbPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int num = position+1;
                if(isChecked){
                    Toast.makeText(mContext," Number  : "+num+" Checked ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvEmail,tvAge;
        CheckBox chbPresent;
        public UserViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvEmail = (TextView)itemView.findViewById(R.id.tvEmail);
            tvAge = (TextView)itemView.findViewById(R.id.tvAge);
            chbPresent= (CheckBox) itemView.findViewById(R.id.chbPresent);
        }
    }
}
