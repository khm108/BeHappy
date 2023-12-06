package com.hello.ourApplication.Registration;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.hello.ourApplication.DTO.Signup;
import com.hello.ourApplication.DTO.SignupResponse;
import com.hello.ourApplication.MainActivity;
import com.hello.ourApplication.R;
import com.hello.ourApplication.Retrofit.RetrofitAPI;
import com.hello.ourApplication.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private RetrofitClient retrofitClient;
    private RetrofitAPI retrofitAPI;
    private Button googleLogin;
    private Button btnSignUp;
    private EditText idText;
    private EditText pwText;
    private EditText nameText;
    private String operation = "signup";
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        // googleLogin = (Button)findViewById(R.id.google_login);
        btnSignUp = (Button)findViewById(R.id.signUpDoneButton);
        idText = (EditText)findViewById(R.id.insertID);
        pwText = (EditText)findViewById(R.id.insertPassword);
        nameText = (EditText)findViewById(R.id.insertName);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = idText.getText().toString();
                String pw = pwText.getText().toString();
                String name = nameText.getText().toString();
                hideKeyboard();

                // 회원가입 정보 미입력시
                if (id.trim().length() == 0 || pw.trim().length() == 0 || name.trim().length() == 0 || id == null || pw == null || name == null) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                    builder.setTitle("알림")
                            .setMessage("정보를 다 입력 바랍니다.")
                            .setPositiveButton("확인", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                } else {
                    //로그인 통신
                    SignUpResponse();
                }
            }
        });

    }

    public void SignUpResponse(){
        String userID = idText.getText().toString().trim();
        String userPassword = pwText.getText().toString().trim();
        String userName = nameText.getText().toString().trim();

        //loginRequest에 사용자가 입력한 id와 pw를 저장
        Signup signupRequest = new Signup(operation, userID, userPassword, userName);

        //retrofit 생성
        retrofitClient = RetrofitClient.getInstance();
        retrofitAPI = RetrofitClient.getRetrofitInterface();

        //loginRequest에 저장된 데이터와 함께 init에서 정의한 getLoginResponse 함수를 실행한 후 응답을 받음
        retrofitAPI.getSignupResponse(signupRequest).enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {

                Log.d("retrofit", "Data fetch success");

                //통신 성공
                if (response.isSuccessful() && response.body() != null) {

                    //response.body()를 result에 저장
                    SignupResponse result = response.body();

                    //받은 코드 저장
                    String resultCode = result.getStatusCode();

                    String success = "200"; //로그인 성공
                    String duplicate = "400"; // 로그인 실패


                    if (resultCode.equals(success)) {

                        Toast.makeText(SignUpActivity.this, userID + "님 환영합니다.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                        intent.putExtra("userId", userID);
                        startActivity(intent);
                        SignUpActivity.this.finish();

                    }else if(resultCode.equals(duplicate)) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                        builder.setTitle("알림")
                                .setMessage("동일한 유저가 존재합니다.")
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    }else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                        builder.setTitle("알림")
                                .setMessage("예기치 못한 오류가 발생하였습니다." + resultCode)
                                .setPositiveButton("확인", null)
                                .create()
                                .show();
                    }
                }
            }

            //통신 실패
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                builder.setTitle("알림")
                        .setMessage("예기치 못한 오류가 발생하였습니다.")
                        .setPositiveButton("확인", null)
                        .create()
                        .show();
            }
        });
    }


    //키보드 숨기기
    private void hideKeyboard()
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(idText.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(pwText.getWindowToken(), 0);
    }

    //화면 터치 시 키보드 내려감
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() { // 뒤로 가기 했을 때
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}