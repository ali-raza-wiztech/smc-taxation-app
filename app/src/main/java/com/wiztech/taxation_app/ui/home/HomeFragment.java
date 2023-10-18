package com.wiztech.taxation_app.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wiztech.taxation_app.Entities.User;
import com.wiztech.taxation_app.LoginSession;
import com.wiztech.taxation_app.MainActivity;
import com.wiztech.taxation_app.Util;
import com.wiztech.taxation_app.databinding.FragmentHomeBinding;
import com.wiztech.taxation_app.networking.APIClient;
import com.wiztech.taxation_app.networking.ApiEndpointInterface;
import com.wiztech.taxation_app.networking.responses.GetConsumerAccountsResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ApiEndpointInterface apiInterface;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        User user= LoginSession.getUser(getActivity());

//        binding.tvName.setText(""+user.getFullName());

        //final TextView textView = binding.textHome;
        final RecyclerView recyclerView = binding.recyclerView;
        Util.displayLoader(getActivity(),"Please wait..");

        apiInterface = APIClient.getClient().create(ApiEndpointInterface.class);
        Call<GetConsumerAccountsResponse> call = apiInterface.getConsumerAccounts(
                "Bearer " + LoginSession.getUserToken(getActivity()),
                10);
        call.enqueue(new Callback<GetConsumerAccountsResponse>() {
            @Override
            public void onResponse(Call<GetConsumerAccountsResponse> call, Response<GetConsumerAccountsResponse> response) {
               // progressdialog.dismiss();
                Util.dismissLoader();
                if (response.body()==null){
                    Toast.makeText(getActivity(),"Session Expired",Toast.LENGTH_SHORT).show();
                    ((MainActivity) getActivity()).moveToLogin();
                }
                if (response.isSuccessful()) {
                    setupAttributeGroups(recyclerView, response.body().getData().getBillHistory());
                    double totalDues=response.body().getData().getTotalDues();
                    if (binding!=null){
                        binding.tvTotalDues.setText("Rs "+totalDues);
                    }
                }
                System.out.println();
            }

            @Override
            public void onFailure(Call<GetConsumerAccountsResponse> call, Throwable t) {
                System.out.println();
                Util.dismissLoader();
            }
        });


        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        return root;
    }


    void setupAttributeGroups(RecyclerView recyclerView, List<ConsumerAccount> list) {
        AttributeGroupsAdapter adapter = new AttributeGroupsAdapter(list, getActivity());
//        recyclerView.setHasFixedSize(true);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.grid_horizontal_spacing));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}