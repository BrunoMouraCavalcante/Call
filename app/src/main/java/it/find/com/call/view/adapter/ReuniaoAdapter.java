package it.find.com.call.view.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import it.find.com.call.R;
import it.find.com.call.interfaces.students_in_meetings.ControlImpl;
import it.find.com.call.presenter.data.Reuniao;
import it.find.com.call.view.activity.ModifyMeetingActivity;

/**
 * Created by Bruno on 26-Feb-18.
 */

public class ReuniaoAdapter extends RecyclerView.Adapter<ReuniaoAdapter.ViewHolder>{
    private ArrayList<Reuniao> mDataset;
    private static ControlImpl.presenterImpl presenter;
    private int type;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTvDate;
        public TextView mTvPresence;
        public TextView mTvLate;
        public TextView mTvMissed;
        public Integer id;
        public String date;
        public Integer type;
        public int position;
        public ViewHolder(View v) {
            super(v);
            mTvDate = v.findViewById(R.id.tv_date_reuniao);
            mTvPresence = v.findViewById(R.id.tv_presence_count);
            mTvLate = v.findViewById(R.id.tv_late_count);
            mTvMissed = v.findViewById(R.id.tv_miss_count);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("meeting_id", id);
                    bundle.putInt("type", type);
                    bundle.putString("date", date);
                    Intent intent = new Intent(v.getContext(), ModifyMeetingActivity.class);
                    intent.putExtras(bundle);
                    presenter.getActivity().startActivityForResult(intent,100);
                    //v.getContext().startActivity(intent);
                }
            });


        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReuniaoAdapter(ArrayList<Reuniao> myDataset, int type) {
        mDataset = myDataset;
        this.type = type;
    }

    public void setReuniaoList(ArrayList<Reuniao> myDataset, int type) {
        this.mDataset = myDataset;
        this.type = type;
    }

    public void setPresenter(ControlImpl.presenterImpl presenter) {
        this.presenter = presenter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReuniaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_reuniao, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        String dateString = DateFormat.format("dd-MM-yyyy", mDataset.get(position).getDate()).toString();
        holder.mTvDate.setText(dateString);
        holder.mTvPresence.setText(String.valueOf(mDataset.get(position).getPresence()));
        holder.mTvLate.setText(String.valueOf(mDataset.get(position).getLate()));
        holder.mTvMissed.setText(String.valueOf(mDataset.get(position).getMiss()));
        holder.id = mDataset.get(position).getMeeting_id();
        holder.type = type;
        holder.date = dateString;
        holder.position = position;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
