package it.find.com.call.view.fragments.pages;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import it.find.com.call.R;
import it.find.com.call.interfaces.students_in_meetings.ControlImpl;
import it.find.com.call.view.adapter.ReuniaoAdapter;

public class SedeFragment extends Fragment implements ControlImpl.SedeViewImpl{

    private static ControlImpl.presenterImpl presenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView mTvEmptyListText;
    public ReuniaoAdapter mAdapter;
    private ImageView mIvPresence, mIvLate, mIvMiss;

    public SedeFragment() {
        // Required empty public constructor
    }

    public static SedeFragment newInstance(ControlImpl.presenterImpl sPresenter) {
        SedeFragment fragment = new SedeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        presenter = sPresenter;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setSedeView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sede, container, false);

        mTvEmptyListText = view.findViewById(R.id.tv_reuniao_empty_list);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.reuniao_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mIvPresence = view.findViewById(R.id.iv_presence);
        mIvLate = view.findViewById(R.id.iv_late);
        mIvMiss = view.findViewById(R.id.iv_miss);

        presenter.fillListSede();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showProgressBar(boolean show) {
        presenter.showProgressBar(show);
    }

    @Override
    public void showToast(String message) { presenter.showToast(message); }


    @Override
    public void showSedeList() {
        if (presenter.getListSede().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mIvPresence.setVisibility(View.VISIBLE);
            mIvLate.setVisibility(View.VISIBLE);
            mIvMiss.setVisibility(View.VISIBLE);
            mTvEmptyListText.setVisibility(View.GONE);
            if (mAdapter == null) {
                mAdapter = new ReuniaoAdapter(presenter.getListSede() , 2);
            } else {
                mAdapter.setReuniaoList(presenter.getListSede(), 2);
            }
            mAdapter.notifyDataSetChanged();
            mAdapter.setPresenter(presenter);
            mRecyclerView.setAdapter(mAdapter);
            showProgressBar(false);
        } else {
            mIvPresence.setVisibility(View.GONE);
            mIvLate.setVisibility(View.GONE);
            mIvMiss.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
            mTvEmptyListText.setVisibility(View.VISIBLE);
            showProgressBar(false);
        }
    }
}
