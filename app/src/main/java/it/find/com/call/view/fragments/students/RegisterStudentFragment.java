package it.find.com.call.view.fragments.students;


import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import it.find.com.call.R;
import it.find.com.call.interfaces.student.StudentImpl;
import it.find.com.call.presenter.data.Student;
import it.find.com.call.presenter.presenters.StudentPresenter;
import it.find.com.call.utils.AnimatorListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterStudentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterStudentFragment extends Fragment implements StudentImpl.ViewSaveImpl{

    private Button mBtnCancel;
    private Button mBtnSave;
    private ImageButton mBtnRemovePhoto;
    private ImageButton mBtnTakePhoto;
    private ImageButton mBtnGallery;
    private ImageView mIvSetPhoto;
    private EditText mEtName;
    private EditText mEtLastName;
    private EditText mEtEmail;
    private boolean action = false;
    public static StudentPresenter presenter;

    public RegisterStudentFragment() {
        // Required empty public constructo
    }

    public static RegisterStudentFragment newInstance(StudentPresenter presenterUnique) {
        RegisterStudentFragment fragment = new RegisterStudentFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_student, container, false);

        mBtnCancel = view.findViewById(R.id.ib_cancel_student);
        mBtnSave = view.findViewById(R.id.ib_student_save);
        mBtnRemovePhoto = view.findViewById(R.id.ib_remove_photo);
        mBtnTakePhoto = view.findViewById(R.id.ib_take_photo);
        mBtnGallery = view.findViewById(R.id.ib_add_galery);
        mIvSetPhoto = view.findViewById(R.id.iv_add_photo);
        mEtName = view.findViewById(R.id.et_name);
        mEtLastName = view.findViewById(R.id.et_lastname);
        mEtEmail = view.findViewById(R.id.et_email);

        mBtnCancel.setOnClickListener(listenerCancel);
        mBtnSave.setOnClickListener(listenerSave);
        mBtnTakePhoto.setOnClickListener(listenerTakePhoto);
        mBtnGallery.setOnClickListener(listenerAddGallery);
        mIvSetPhoto.setOnClickListener(listenerPhoto);

        mBtnRemovePhoto.setVisibility(View.GONE);
        mBtnTakePhoto.setVisibility(View.GONE);
        mBtnGallery.setVisibility(View.GONE);

        hideImageButtons();

        return view;
    }

    private View.OnClickListener listenerPhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (action) {
                hideImageButtons();
            } else {
                showImageButtons();
            }
            action = !action;
        }
    };

    private View.OnClickListener listenerTakePhoto = new View.OnClickListener() {
        @Override
        public void onClick(View v) {}
    };

    private View.OnClickListener listenerAddGallery = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private View.OnClickListener listenerCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            cleanView();
        }
    };

    private void hideImageButtons() {
        animationHideButton(mBtnRemovePhoto);
        animationHideButton(mBtnTakePhoto);
        animationHideButton(mBtnGallery);
    }

    private void showImageButtons() {
        animationShowButton(mBtnRemovePhoto);
        animationShowButton(mBtnTakePhoto);
        animationShowButton(mBtnGallery);
    }

    private View.OnClickListener listenerSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validateFields()) {
                Student student = new Student();
                student.setName(mEtName.getText().toString());
                student.setLastName(mEtLastName.getText().toString());
                student.setEmail(mEtEmail.getText().toString());
                presenter.createStudent(student);
            }
        }
    };

    public boolean validateFields() {
        if (mEtName.getText().toString().trim().isEmpty()) {
            Vibrator v = (Vibrator) presenter.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            mEtName.requestFocus();
            mEtName.startAnimation(shakeError());
            v.vibrate(500);
            return false;
        }

        if (mEtLastName.getText().toString().trim().isEmpty()) {
            Vibrator v = (Vibrator) presenter.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            mEtLastName.requestFocus();
            mEtLastName.startAnimation(shakeError());
            v.vibrate(500);
            return false;
        }

        if (mEtEmail.getText().toString().trim().isEmpty()) {
            Vibrator v = (Vibrator) presenter.getContext().getSystemService(Context.VIBRATOR_SERVICE);
            mEtEmail.requestFocus();
            mEtEmail.startAnimation(shakeError());
            v.vibrate(500);
            return false;
        }
        return true;
    }

    public TranslateAnimation shakeError() {
        TranslateAnimation shake = new TranslateAnimation(0, 10, 0, 0);
        shake.setDuration(500);
        shake.setInterpolator(new CycleInterpolator(7));
        return shake;
    }

    private void animationShowButton(ImageButton view) {
        view.animate()
                .translationY(0)
                .alpha(1.0f)
                .setDuration(300)
                .setListener(new AnimatorListener(view, false));
    }

    private void animationHideButton(ImageButton view) {
        view.animate()
                .translationY(view.getHeight())
                .alpha(0.0f)
                .setDuration(300)
                .setListener(new AnimatorListener(view, true));
    }

    @Override
    public void showProgressBar(boolean show) {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cleanView() {
        mEtEmail.setText("");
        mEtName.setText("");
        mEtLastName.setText("");
        mIvSetPhoto.setImageResource(getResources().getIdentifier("ic_account_circle_black_48dp", "drawable", getContext().getPackageName()));
    }
}
