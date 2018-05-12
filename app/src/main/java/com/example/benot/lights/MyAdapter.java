package com.example.benot.lights;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[][] mDataset;
    private KasaInfo kasaInfo;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView mTextView;
        private Switch mSwitch;

        public ViewHolder(View v) {
            super(v);

            mTextView = v.findViewById(R.id.text_title);
            mSwitch = v.findViewById(R.id.toggle);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(KasaInfo kasaInfo) {
        mDataset = kasaInfo.getLights();
        this.kasaInfo = kasaInfo;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_children, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position][0]);
        holder.mSwitch.setChecked(Integer.parseInt(mDataset[position][1]) == 1);
        holder.mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    kasaInfo.changeState(position);
                } catch (IOException e) {

                }
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


}