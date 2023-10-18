package com.wiztech.taxation_app.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.wiztech.taxation_app.MainActivity;
import com.wiztech.taxation_app.R;
import com.wiztech.taxation_app.ui.slideshow.SlideshowFragment;

import java.util.List;
import java.util.Objects;

public class AttributeGroupsAdapter extends RecyclerView.Adapter<AttributeGroupsAdapter.ViewHolder>{
    private List<ConsumerAccount> listdata;
    Context context;

    // RecyclerView recyclerView;
    public AttributeGroupsAdapter(List<ConsumerAccount> listdata, Context context) {
        this.listdata = listdata;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.home_group_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ConsumerAccount myListData = listdata.get(position);
        if (position==0){
            holder.relativeLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.left_pizza) );
        }else if (position==1){
            holder.relativeLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.right_pizza) );
        }else if (position==2){
            holder.relativeLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.pizz_bottom_left) );
        }
        else if (position/2  ==0){
            holder.relativeLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.left_pizza) );
        }else {
            holder.relativeLayout.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.right_pizza) );
        }


        holder.tv_name.setText(""+myListData.getAttributeGroup().title);
//        holder.tvCnic.setText(""+myListData.getCnic());
//        holder.tvGender.setText(""+myListData.getRelationshipWithPensioner());
//        holder.tv_address.setText(""+myListData.getAddress());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putInt("consumer_account_id",myListData.getId());
                bundle.putInt("attribute_group_id",myListData.getAttributeGroup().getId());
                bundle.putString("recoveryHead",""+myListData.getAttributeGroup().getTitle());
                Fragment fragment= new SlideshowFragment();
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = Objects.requireNonNull((MainActivity) context).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container,fragment);
                fragmentTransaction.addToBackStack("");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tv;
        public TextView tvCnic;
        public TextView tv_name,tv_address;
        public RelativeLayout relativeLayout;
        public Button btn_nomineeBankDetailHistory;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_name = itemView.findViewById(R.id.tv_name);
//            this.tvGender =  itemView.findViewById(R.id.tv_gender);
//            this.tvCnic =  itemView.findViewById(R.id.tv_cnic);
//            this.tv_address=itemView.findViewById(R.id.tv_address);
//            this.btn_nomineeBankDetailHistory=itemView.findViewById(R.id.btn_nomineeBankDetailHistory);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

}
