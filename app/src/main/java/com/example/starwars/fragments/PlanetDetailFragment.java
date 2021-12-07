package com.example.starwars.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.starwars.R;
import com.example.starwars.databinding.FragmentPlanetDetailBinding;

public class PlanetDetailFragment extends Fragment {

    public static final String TAG = "PlanetDetailsFragment";

    private FragmentPlanetDetailBinding binding;
    private PlanetDetailFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPlanetDetailBinding.inflate(inflater, container, false);
        args = PlanetDetailFragmentArgs.fromBundle(getArguments());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        binding.setPlanet(args.getPlanet());

        binding.planetDetailImage.setImageBitmap(args.getImage());

        binding.toolbar.setNavigationOnClickListener(v -> {
            Navigation.findNavController(v).navigateUp();
        });

        binding.toolbar.setOnMenuItemClickListener(item -> {
            switch(item.getItemId()) {
                case R.id.action_favorite:
                    favorite();
                    return true;
                case R.id.action_share:
                    createShareIntent();
                    return true;
                default:
                    return false;
            }
        });

    }

    public void favorite() {
        Log.d(TAG, "favorite");
    }

    public void createShareIntent() {
        String shareText = "createShareIntent";

        Intent shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);

        startActivity(shareIntent);
    }

}