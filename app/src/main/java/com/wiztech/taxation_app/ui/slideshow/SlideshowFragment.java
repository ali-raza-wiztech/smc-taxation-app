package com.wiztech.taxation_app.ui.slideshow;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.wiztech.taxation_app.BuildConfig;
import com.wiztech.taxation_app.LoginSession;
import com.wiztech.taxation_app.MainActivity;
import com.wiztech.taxation_app.R;
import com.wiztech.taxation_app.Util;
import com.wiztech.taxation_app.databinding.FragmentSlideshowBinding;
import com.wiztech.taxation_app.networking.APIClient;
import com.wiztech.taxation_app.networking.ApiEndpointInterface;
import com.wiztech.taxation_app.networking.responses.GetCurrentBillAndHistoryResponse;
import com.wiztech.taxation_app.networking.responses.GetCurrentBillData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    private FragmentSlideshowBinding binding;
    ApiEndpointInterface apiInterface;
    GetCurrentBillData getCurrentBillData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Util.displayLoader(getActivity(),"Wait...");
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.ivBack.setOnClickListener(this);
//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        if (getArguments() != null) {
            int consumer_account_id = getArguments().getInt("consumer_account_id");
            int attribute_group_id = getArguments().getInt("attribute_group_id");
            binding.tvName.setText(""+getArguments().getString("recoveryHead"));
            binding.tvBillHistoryTitle.setText(getArguments().getString("recoveryHead").toUpperCase()+" Billing and payment History".toUpperCase());

            apiInterface = APIClient.getClient().create(ApiEndpointInterface.class);
            Call<GetCurrentBillAndHistoryResponse> call = apiInterface.getCurrentBillAndHistory(
                    "Bearer "+ LoginSession.getUserToken(getActivity()),
                    consumer_account_id, attribute_group_id);
            call.enqueue(new Callback<GetCurrentBillAndHistoryResponse>() {
                @Override
                public void onResponse(Call<GetCurrentBillAndHistoryResponse> call, Response<GetCurrentBillAndHistoryResponse> response) {
                    System.out.println();
                    Util.dismissLoader();
                    if (response.body()==null){
                        Toast.makeText(getActivity(),"Session Expired",Toast.LENGTH_SHORT).show();
                        ((MainActivity) getActivity()).moveToLogin();
                    }
                    if (response.isSuccessful()) {
                        if (response.body().getCode()==200){
                            if (binding!=null){
                                binding.tvNoBillGenerated.setVisibility(View.GONE);
                            }
                            setDataOnViews(response.body().getData().getGetCurrentBillData());
                            setHistoryListing(response.body().getData().getBillHistory());
                        }else{
                            if (binding.tvNoBillGenerated!=null){
                                binding.tvNoBillGenerated.setVisibility(View.VISIBLE);
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<GetCurrentBillAndHistoryResponse> call, Throwable t) {
                    System.out.println();
                    Util.dismissLoader();
                }
            });
        }

        binding.btnBillDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermissionGranted(getActivity())){
                    if (getCurrentBillData!=null){
                        disableBillDetailButton();
                        showLoader();
                        Util.displayLoader(getActivity(),"Downloading Bill, Wait...");
                        loadBillApi();
                    }
                }

//                Fragment fragment= new BillDetailFrag();
//                FragmentManager fragmentManager = Objects.requireNonNull((MainActivity) getActivity()).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.add(R.id.container,fragment);
//                fragmentTransaction.addToBackStack("");
//                fragmentTransaction.commit();
            }
        });
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void setDataOnViews(GetCurrentBillData data) {
        getCurrentBillData=data;
        System.out.println(data);
        if (binding!=null && data!=null){
            binding.tvPayableAmount.setText("" + data.getPayableAmount());
            binding.tvDueDate.setText("Due Date: " + data.getBillLayout().getDueDayOfMonth() + "-" + data.getBillingMonth());
            binding.tvNoBillGenerated.setVisibility(View.GONE);
            binding.btnBillDetail.setVisibility(View.VISIBLE);
        }else{
            binding.tvNoBillGenerated.setVisibility(View.VISIBLE);
            binding.btnBillDetail.setVisibility(View.INVISIBLE);
        }
    }

    private void setHistoryListing(List<GetCurrentBillData> billHistory) {
        if (binding!=null){
            if (billHistory!=null && billHistory.size()>1){
                binding.tvLastMonthBill.setText(""+billHistory.get(0).getBillAmount());
            }else{
                binding.tvLastMonthBill.setText("0");
            }
        }
        BillHistoryAdapter adapter = new BillHistoryAdapter(billHistory, getActivity());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        binding.recyclerView.setAdapter(adapter);
    }

    void loadBillApi(){
        Call<ResponseBody> call= apiInterface.getBillPage("Bearer "+LoginSession.getUserToken(getActivity()),getCurrentBillData.getId());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Util.dismissLoader();
                try {
//                    File path = Environment.getExternalStorageDirectory();
//                    File file = new File(path, "file_name.png");
//                    FileOutputStream fileOutputStream = new FileOutputStream(file);
//                    IOUtils.write(response.body().bytes(), fileOutputStream);

                    hideLoader();
                    enableBillDetailButton();
                    Thread thread = new Thread(() -> {
                        try  {
                            //Your code goes here
                            writeResponseBodyToDisk(response.body().byteStream());


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    thread.start();


                }
                catch (Exception ex){
                    System.out.println();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Util.dismissLoader();
                System.out.println();
            }
        });
    }
    public static boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                //Log.v(TAG, "Permission is granted");

                return true;
            } else {
                //Toast.makeText(getApplicationContext(), "Permission is revoked",Toast.LENGTH_SHORT).show();
                //Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else{
            //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(getApplicationContext(), "Permission is revoked",Toast.LENGTH_SHORT).show();
            //Log.v(TAG, "Permission is granted");
            return true;
        }
    }
    private boolean writeResponseBodyToDisk(InputStream body) {
        try {
            // todo change the file location/name according to your needs

            //File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            java.io.File futureStudioIconFile = new java.io.File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + "/" + "water-bill.pdf");
            InputStream inputStream = body;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                // long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                //inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " );
                }
                outputStream.flush();

                java.io.File file = new java.io.File(Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                        + "/" + "water-bill.pdf");

                //File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/"+ filename);


                try
                {
                    //hideLoader();
                    //enableBillDetailButton();
                    Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW);
                    File file1 = new File(file.getAbsolutePath());
                    String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(Uri.fromFile(file1).toString());
                    String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
                    Uri uri = FileProvider.getUriForFile(getActivity(), BuildConfig.APPLICATION_ID + ".provider",file1);
                    myIntent.setDataAndType(uri,mimetype);
                    myIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    startActivity(myIntent);
                }
                catch (Exception e)
                {
                    // TODO: handle exception
                    String data = e.getMessage();
                }

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
    void showLoader(){
        binding.progressBar1.setVisibility(View.VISIBLE);
    }
    void hideLoader(){
        binding.progressBar1.setVisibility(View.GONE);
    }
    void disableBillDetailButton(){
            binding.btnBillDetail.setVisibility(View.GONE);
    }
    void enableBillDetailButton() {
        binding.btnBillDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.ivBack:
                ((MainActivity)getActivity()).onBackPressed();
                break;
        }
    }
}