package com.agzuniverse.agz.opensalve.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.agzuniverse.agz.opensalve.R;

public class SuppliesNeededAdapter extends RecyclerView.Adapter<SuppliesNeededAdapter.SuppliesNeededViewHolder> {

    private LayoutInflater inflater;
    private String[] supplies;

    public SuppliesNeededAdapter(Context context, String[] supplies) {
        inflater = LayoutInflater.from(context);
        this.supplies = supplies;
    }

    @NonNull
    @Override
    public SuppliesNeededViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.supplies_needed_row, viewGroup, false);
        SuppliesNeededViewHolder holder = new SuppliesNeededViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SuppliesNeededViewHolder viewHolder, int i) {
        String current = supplies[i];
        viewHolder.supply.setText(current);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class SuppliesNeededViewHolder extends RecyclerView.ViewHolder {
        public TextView supply;

        public SuppliesNeededViewHolder(@NonNull View itemView) {
            super(itemView);
            supply = itemView.findViewById(R.id.supply);
        }
    }
}
