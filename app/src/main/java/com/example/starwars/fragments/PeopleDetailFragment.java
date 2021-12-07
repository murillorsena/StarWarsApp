package com.example.starwars.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.starwars.R;
import com.example.starwars.databinding.FragmentPeopleDetailBinding;
import com.example.starwars.models.people.People;

public class PeopleDetailFragment extends Fragment {

    public static final String TAG = "PeopleDetailsFragment";

    private FragmentPeopleDetailBinding binding;
    private PeopleDetailFragmentArgs args;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPeopleDetailBinding.inflate(inflater, container, false);
        args = PeopleDetailFragmentArgs.fromBundle(getArguments());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        binding.setPeople(args.getPeople());

        binding.peopleDetailImage.setImageBitmap(args.getImage());

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