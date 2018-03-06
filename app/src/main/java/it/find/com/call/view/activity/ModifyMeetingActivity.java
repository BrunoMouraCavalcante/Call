package it.find.com.call.view.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import it.find.com.call.R;
import it.find.com.call.interfaces.students_in_meetings.StudentMeetingImp;
import it.find.com.call.model.models.StudentMeetingModel;
import it.find.com.call.presenter.presenters.ModifyMeetingPresenter;
import it.find.com.call.view.adapter.ModifyMeetingAdapter;

public class ModifyMeetingActivity extends AppCompatActivity implements StudentMeetingImp.ViewImpl {

    private TextView mTvDate;
    private TextView mTvType;
    private TextView mTvEmptyListText;
    private ConstraintLayout mClProgressBar;
    private ConstraintLayout mClText;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ModifyMeetingAdapter mAdapter;
    private StudentMeetingModel model;
    private ModifyMeetingPresenter presenter;
    private Button mBtnSave, mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_meeting);

        int meeting_id = getIntent().getExtras().getInt("meeting_id");
        int type = getIntent().getExtras().getInt("type");
        String date = getIntent().getExtras().getString("date");

        model = new StudentMeetingModel();
        presenter = new ModifyMeetingPresenter(meeting_id,type);
        presenter.setView(this,this);

        mTvDate = findViewById(R.id.tv_meeting_date);
        mTvType = findViewById(R.id.tv_meeting_type);
        mTvEmptyListText = findViewById(R.id.tv_empty_student_list);
        mClProgressBar = findViewById(R.id.cl_progress_bar);
        mClText = findViewById(R.id.cl_info);
        mBtnSave = findViewById(R.id.btn_update_status);
        mBtnCancel = findViewById(R.id.btn_cancel_update);

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressBar(true);
                presenter.updateStudentMeeting();
            }
        });

        mTvDate.setText(date);
        mTvType.setText(presenter.getType());


        mRecyclerView = findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        presenter.fillListStudents();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            mClProgressBar.setVisibility(View.VISIBLE);
            mClText.setVisibility(View.GONE);
        } else {
            mClText.setVisibility(View.VISIBLE);
            mClProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showStudentMeetingList() {
        if (presenter.getStudenMeetingsList().size() > 0) {
            mRecyclerView.setVisibility(View.VISIBLE);
            mTvEmptyListText.setVisibility(View.GONE);
            if (mAdapter == null) {
                mAdapter = new ModifyMeetingAdapter(presenter.getStudenMeetingsList());
            } else {
                mAdapter.setAdapterMembers(presenter.getStudenMeetingsList());
            }
            mAdapter.notifyDataSetChanged();
            mAdapter.setPresenter(presenter);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mRecyclerView.setVisibility(View.GONE);
            mTvEmptyListText.setVisibility(View.VISIBLE);
        }
        showProgressBar(false);
    }
}
