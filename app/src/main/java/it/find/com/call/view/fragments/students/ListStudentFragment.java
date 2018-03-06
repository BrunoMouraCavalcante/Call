package it.find.com.call.view.fragments.students;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import it.find.com.call.R;
import it.find.com.call.interfaces.student.StudentImpl;
import it.find.com.call.presenter.presenters.StudentPresenter;
import it.find.com.call.view.adapter.AdapterMembers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListStudentFragment extends Fragment implements StudentImpl.ViewListImpl {

    private final String TAG = ListStudentFragment.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mTvEmptyListText;
    public AdapterMembers mAdapter;
    public static StudentPresenter presenter;

    public ListStudentFragment() { }

    public static ListStudentFragment newInstance(StudentPresenter presenterUnique) {
        ListStudentFragment fragment = new ListStudentFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        presenter = presenterUnique;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_student, container, false);

        mTvEmptyListText = view.findViewById(R.id.tv_empty_student_list);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_students_list);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        presenter.fillListStudents();

        return view;
    }

    @Override
    public void showProgressBar(boolean show) {
        presenter.showProgressBar(show);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT). show();
    }

    @Override
    public void createListStudents() {
        mRecyclerView.setVisibility(View.VISIBLE);
        mTvEmptyListText.setVisibility(View.GONE);
        if (mAdapter == null) {
            mAdapter = new AdapterMembers(presenter.getStudents());
        } else {
            mAdapter.setAdapterMembers(presenter.getStudents());
        }
        mAdapter.notifyDataSetChanged();
        mAdapter.setPresenter(presenter);
        mRecyclerView.setAdapter(mAdapter);
        showProgressBar(false);
    }

    @Override
    public void createEmptyList() {
        mRecyclerView.setVisibility(View.GONE);
        mTvEmptyListText.setVisibility(View.VISIBLE);
        showProgressBar(false);
    }
}
