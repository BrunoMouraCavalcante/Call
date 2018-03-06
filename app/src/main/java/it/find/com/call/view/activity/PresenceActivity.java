package it.find.com.call.view.activity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import it.find.com.call.R;
import it.find.com.call.interfaces.meetings.MeetingImpl;
import it.find.com.call.interfaces.students_in_meetings.StudentMeetingImp;
import it.find.com.call.presenter.data.Meeting;
import it.find.com.call.presenter.presenters.MeetingPresenter;
import it.find.com.call.view.adapter.PresenceAdapter;

/**
 * Created by Bruno on 16-Jan-18.
 */

public class PresenceActivity extends AppCompatActivity implements MeetingImpl.ViewImpl, StudentMeetingImp.ViewImpl{

    private Button btnAccept;
    private Button btnCancel;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private PresenceAdapter mAdapter;
    private Spinner mSpinner;
    private Calendar myCalendar = Calendar.getInstance();
    private EditText mEtDate;
    private TextView mTvEmptyListText;
    private DatePickerDialog.OnDateSetListener date;
    private ConstraintLayout mClProgressBar;
    private ConstraintLayout mClData;
    public MeetingPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int spin = getIntent().getExtras().getInt("spin");
        presenter = new MeetingPresenter(this.getApplicationContext());
        presenter.setView(this);
        presenter.setStudentMeetingView(this);

        mClProgressBar = findViewById(R.id.cl_progressBar);
        mClData = findViewById(R.id.cl_data);
        btnCancel = findViewById(R.id.btnCancel);
        btnAccept = findViewById(R.id.btnSave);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()) {
                    showProgressBar(true);
                    presenter.setMeetingType(((mSpinner.getSelectedItemPosition()+1) == 1 ?
                            getString(R.string.reuniao):
                            getString(R.string.sede)));
                    Meeting meeting = new Meeting();
                    meeting.setType((mSpinner.getSelectedItemPosition()+1));
                    meeting.setDate(meeting.convertToTimestamp(mEtDate.getText().toString()));
                    presenter.createMeeting(meeting);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cleanScreen();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        presenter.fillListStudents();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mSpinner = (Spinner) findViewById(R.id.spinner);
        String[] test = new String[]{getString(R.string.reuniao),getString(R.string.sede)};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(PresenceActivity.this,android.R.layout.simple_spinner_item, test);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter);

        mSpinner.setSelection(spin);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        mEtDate = (EditText) findViewById(R.id.etDate);
        mEtDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(PresenceActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mTvEmptyListText = findViewById(R.id.tvEmptyList);
    }

    private void cleanScreen() {
        mEtDate.setText("");
        recreate();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, new Locale("pt", "BR"));
        mEtDate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void showProgressBar(boolean show) {
        if (show) {
            mClProgressBar.setVisibility(View.VISIBLE);
            mClData.setVisibility(View.GONE);
        } else {
            mClProgressBar.setVisibility(View.GONE);
            mClData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showStudentMeetingList() { }

    @Override
    public boolean validateFields() {
        if (mEtDate.getText().toString().isEmpty()) {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            mEtDate.requestFocus();
            mEtDate.startAnimation(shakeError());
            v.vibrate(500);
            return false;
        }
        return true;
    }

    @Override
    public void cleanFields() {
        mEtDate.setText("");
    }

    @Override
    public void createListStudents() {
        mRecyclerView.setVisibility(View.VISIBLE);
        btnAccept.setClickable(true);
        mTvEmptyListText.setVisibility(View.GONE);
        if (mAdapter == null) {
            mAdapter = new PresenceAdapter(presenter.getStudents());
        } else {
            mAdapter.setAdapterMembers(presenter.getStudents());
        }
        mAdapter.setPresenter(presenter.getSmiPresenter().getThis());
        mAdapter.notifyDataSetChanged();
        mRecyclerView.setAdapter(mAdapter);
        showProgressBar(false);
    }

    @Override
    public void createEmptyList() {
        mRecyclerView.setVisibility(View.GONE);
        mTvEmptyListText.setVisibility(View.VISIBLE);
        btnAccept.setClickable(false);
        showProgressBar(false);
    }

    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }
}
