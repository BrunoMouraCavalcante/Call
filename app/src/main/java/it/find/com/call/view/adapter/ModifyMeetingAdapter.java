package it.find.com.call.view.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import it.find.com.call.R;
import it.find.com.call.interfaces.students_in_meetings.StudentMeetingImp;
import it.find.com.call.presenter.data.StudentMeetingWithStudent;

/**
 * Created by Bruno on 05-Mar-18.
 */

public class ModifyMeetingAdapter extends RecyclerView.Adapter<ModifyMeetingAdapter.ViewHolder>{
    private ArrayList<StudentMeetingWithStudent> mDataset;
    private  StudentMeetingImp.PresenterImpl presenter;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageButton mIbPresence;
        public ImageButton mIbMissed;
        public ImageButton mIbLate;
        public int position;
        public int id;
        public int status;
        public StudentMeetingImp.PresenterImpl presenter;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text_view_id);
            mIbPresence = (ImageButton) v.findViewById(R.id.ib_presence);
            mIbMissed = (ImageButton) v.findViewById(R.id.ib_missed);
            mIbLate = (ImageButton) v.findViewById(R.id.ib_late);

            mIbPresence.setOnClickListener(this);
            mIbMissed.setOnClickListener(this);
            mIbLate.setOnClickListener(this);
        }

        public void paintWithStatus(View v) {
            if (status == 1) {
                setColors(ResourcesCompat.getColor(v.getResources(), R.color.presence, null),0, 0);
            } else if (status == 2) {
                setColors(0,0, ResourcesCompat.getColor(v.getResources(), R.color.late, null));
            } else if (status == 3) {
                setColors(0,ResourcesCompat.getColor(v.getResources(), R.color.miss, null), 0);
            }
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ib_presence) {
                //Toast.makeText(v.getContext(), "ITEM PRESSED = " + String.valueOf(getAdapterPosition())+ " Presence", Toast.LENGTH_LONG).show();
                setColors(ResourcesCompat.getColor(v.getResources(), R.color.presence, null),0, 0);
                presenter.setStudentStatus(position,1);
            } else if (v.getId() == R.id.ib_late) {
                setColors(0,0, ResourcesCompat.getColor(v.getResources(), R.color.late, null));
                presenter.setStudentStatus(position,2);
            } else if (v.getId() == R.id.ib_missed) {
                setColors(0,ResourcesCompat.getColor(v.getResources(), R.color.miss, null), 0);
                presenter.setStudentStatus(position,3);
            }
        }

        private void setColors (@Nullable int p, @Nullable int m, @Nullable int l) {
            mIbPresence.setColorFilter(p);
            mIbMissed.setColorFilter(m);
            mIbLate.setColorFilter(l);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ModifyMeetingAdapter(ArrayList<StudentMeetingWithStudent> myDataset) {
        mDataset = myDataset;
    }

    public void setAdapterMembers(ArrayList<StudentMeetingWithStudent> myDataset) {
        mDataset = myDataset;
    }
    public void setPresenter(StudentMeetingImp.PresenterImpl presenter) { this.presenter = presenter; }

    // Create new views (invoked by the layout manager)
    @Override
    public ModifyMeetingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getFirst_name());
        holder.position = position;
        holder.id = mDataset.get(position).getId();
        holder.status = mDataset.get(position).getStatus();
        holder.presenter = this.presenter;
        holder.paintWithStatus(holder.mTextView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
