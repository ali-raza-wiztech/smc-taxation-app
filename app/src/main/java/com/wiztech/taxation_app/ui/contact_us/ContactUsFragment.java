package com.wiztech.taxation_app.ui.contact_us;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.wiztech.taxation_app.MainActivity;
import com.wiztech.taxation_app.R;
import com.wiztech.taxation_app.databinding.FragmentGalleryBinding;

public class ContactUsFragment extends Fragment implements View.OnClickListener {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.rlWhatsApp.setOnClickListener(this);
        binding.rlCall.setOnClickListener(this);
        binding.rlMsg.setOnClickListener(this);
        binding.rlEmail.setOnClickListener(this);
        binding.rlGetInTouch.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
       // final TextView textView = binding.textGallery;
       // galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.rlWhatsApp:
                whatsAppAction();
                break;
            case R.id.rlCall:
                callAction();
                break;
            case R.id.rlMsg:
                msgAction();
                break;
            case R.id.rlEmail:
                emailAction();
                break;
            case R.id.rlGetInTouch:
                getInTouchAction();
                break;
            case R.id.ivBack:
                ((MainActivity)getActivity()).onBackPressed();
                break;
        }
    }
    void whatsAppAction(){
        PackageManager pm=getActivity().getPackageManager();
        try {
            Uri uri = Uri.parse("smsto:03463414956");
            Intent i = new Intent(Intent.ACTION_SENDTO, uri);
            i.setPackage("com.whatsapp");
            startActivity(Intent.createChooser(i, ""));

        } catch (Exception e) {
            Toast.makeText(getActivity(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
        }
    }
    void callAction(){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:111757777"));
        startActivity(intent);
    }
    void msgAction(){
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:03463414956"));
        startActivity(sendIntent);
    }
    void emailAction(){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:tax@smc.gos.pk")); // only email apps should handle this
        startActivity(intent);
    }
    void getInTouchAction(){
        Fragment fragment= new ComplaintFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container,fragment);
        fragmentTransaction.addToBackStack("");
        fragmentTransaction.commit();
    }
}