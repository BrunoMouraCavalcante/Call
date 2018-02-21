package it.find.com.call.view.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import it.find.com.call.R;
import it.find.com.call.presenter.data.Student;
import it.find.com.call.presenter.presenters.StudentPresenter;

/**
 * Created by Bruno on 16-Jan-18.
 */

public class AdapterMembers extends RecyclerView.Adapter<AdapterMembers.ViewHolder> {
    private ArrayList<Student> mDataset;
    private static StudentPresenter presenter;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public Button mBtnRemove;
        public Integer id;
        public int position;
        public ViewHolder(View v) {
            super(v);
            mTextView = v.findViewById(R.id.tvName);
            mBtnRemove = v.findViewById(R.id.btnRemove);
            mBtnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    new AlertDialog.Builder(v.getContext())
                    .setTitle("Excluir membro")
                    .setMessage("Deseja excluir o membro "+mTextView.getText()+"?")
                     .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             presenter.removeMember(id,position);
                         }
                     })
                     .setNegativeButton("Não", null)
                     .show();

                }
            });
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterMembers(ArrayList<Student> myDataset) {
        mDataset = myDataset;
    }

    public void setAdapterMembers(ArrayList<Student> myDataset) {
        this.mDataset = myDataset;
    }

    public void setPresenter(StudentPresenter presenter) {
        this.presenter = presenter;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AdapterMembers.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_members, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).getName());
        holder.id = mDataset.get(position).getId();
        holder.position = position;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
