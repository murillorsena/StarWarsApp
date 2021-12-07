package com.example.starwars.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwars.databinding.ItemGameForYouBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.ViewHolder>{

    private final String TAG = "VerticalRecyclerViewAdapter";
    
    private Context context;
    private List<String> categories2;

    private HashMap<Integer, RecyclerView.Adapter> hashMap = new HashMap<Integer, RecyclerView.Adapter>();

    private HashMap<Integer, HashMap<String, RecyclerView.Adapter>> categories = new HashMap<Integer, HashMap<String, RecyclerView.Adapter>>();

    public VerticalRecyclerViewAdapter(Context context) {
        this.context = context;

        /*

        categories.put(0, new HashMap<String, RecyclerView.Adapter>() {{
            put("Personagens", new PeopleAdapter());
        }});

        categories.put(1, new HashMap<String, RecyclerView.Adapter>() {{
            put("Planetas", new PlanetAdapter());
        }});

        categories.put(2, new HashMap<String, RecyclerView.Adapter>() {{
            put("Filmes", new FilmAdapter(context));
        }});

        categories.put(3, new HashMap<String, RecyclerView.Adapter>() {{
            put("Espécies", new SpecieAdapter());
        }});

        categories.put(4, new HashMap<String, RecyclerView.Adapter>() {{
            put("Veículos", new VehicleAdapter());
        }});

        categories.put(5, new HashMap<String, RecyclerView.Adapter>() {{
            put("Naves", new StarshipAdapter());
        }});

         */


        hashMap.put(0, new PeopleAdapter(context));
        hashMap.put(1, new PlanetAdapter(context));
        hashMap.put(2, new FilmAdapter(context));
        hashMap.put(3, new SpecieAdapter(context));
        hashMap.put(4, new VehicleAdapter(context));
        hashMap.put(5, new StarshipAdapter(context));


        categories2 = new ArrayList<>();
        categories2.add("Personagens");
        categories2.add("Planetas");
        categories2.add("Filmes");
        categories2.add("Espécies");
        categories2.add("Veículos");
        categories2.add("Naves");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGameForYouBinding binding = ItemGameForYouBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalRecyclerViewAdapter.ViewHolder holder, int position) {
        String category = categories2.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categories2 == null ? 0 : categories2.size();
        //return categories == null ? 0 : categories.size();
    }

    public RecyclerView.Adapter getItemAtPosition(int position) {
        return hashMap.get(position);
        //return categories.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemGameForYouBinding binding;
        private LinearLayoutManager layoutManager;
        private RecyclerView.Adapter adapter;

        public ViewHolder(ItemGameForYouBinding binding) {

            super(binding.getRoot());

            this.binding = binding;
            layoutManager = new LinearLayoutManager(
                binding.getRoot().getContext(),
                RecyclerView.HORIZONTAL,
                false
            );

        }

        public void bind(String category) {

            switch(category) {
                case "Personagens":
                    adapter = hashMap.get(0); break;
                case "Planetas":
                    adapter = hashMap.get(1); break;
                case "Filmes":
                    adapter = hashMap.get(2); break;
                case "Espécies":
                    adapter = hashMap.get(3); break;
                case "Veículos":
                    adapter = hashMap.get(4); break;
                case "Naves":
                    adapter = hashMap.get(5); break;
                default:
                    throw new IllegalStateException("Unexpected value: " + category);
            }

            binding.textviewTitle.setText(category);
            binding.recyclerviewHorizontal.setLayoutManager(layoutManager);
            binding.recyclerviewHorizontal.setAdapter(adapter);

            binding.recyclerviewHorizontal.setOnScrollChangeListener(new RecyclerView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    Log.d(TAG, "onScrollChange");
                }
            });

        }

    }

}
