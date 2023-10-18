package com.wiztech.taxation_app.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.wiztech.taxation_app.R;
import com.wiztech.taxation_app.networking.responses.GetCurrentBillData;

import java.util.List;

public class BillHistoryAdapter extends RecyclerView.Adapter<BillHistoryAdapter.ViewHolder>{
    private List<GetCurrentBillData> listdata;
    Context context;

    // RecyclerView recyclerView;
    public BillHistoryAdapter(List<GetCurrentBillData> listdata, Context context) {
        this.listdata = listdata;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.bill_history_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GetCurrentBillData myListData = listdata.get(position);
            String dueDate=myListData.getBillLayout().getDueDayOfMonth()+"-"+myListData.getBillingMonth();


        holder.tv_dueDateValue.setText(dueDate);
        holder.tv_paidOnValue.setText(""+myListData.getPaymentDate()==null?"-":myListData.getPaymentDate());
        holder.tv_paidAmountValue.setText(""+myListData.getPaidAmount());
        holder.tvBillingMonth.setText(""+myListData.getBillingMonth());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle=new Bundle();
//                bundle.putInt("consumer_account_id",myListData.getId());
//                bundle.putInt("attribute_group_id",myListData.getAttributeGroup().getId());
//                bundle.putString("recoveryHead",""+myListData.getAttributeGroup().getTitle());
//                Fragment fragment= new SlideshowFragment();
//                fragment.setArguments(bundle);
//
//                FragmentManager fragmentManager = Objects.requireNonNull((MainActivity) context).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.container,fragment);
//                fragmentTransaction.addToBackStack("");
//                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public TextView tv_dueDateValue,tv_paidOnValue,tv_paidAmountValue,tvBillingMonth;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_dueDateValue = itemView.findViewById(R.id.tv_dueDateValue);
            this.tv_paidOnValue =  itemView.findViewById(R.id.tv_paidOnValue);
            this.tv_paidAmountValue =  itemView.findViewById(R.id.tv_paidAmountValue);
            this.tvBillingMonth=itemView.findViewById(R.id.tvBillingMonth);
            relativeLayout = itemView.findViewById(R.id.relativeLayout);
        }
    }

}
