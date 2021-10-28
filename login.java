package com.jinhee.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class login extends AppCompatActivity {
    String urlPath = "http://101.101.218.94/connectedDB.php";
    InputMethodManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText emailEdit = findViewById(R.id.email);
        EditText pwdEdit = findViewById(R.id.pw);
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager.hideSoftInputFromWindow(emailEdit.getWindowToken(), 0);
                manager.hideSoftInputFromWindow(pwdEdit.getWindowToken(), 0);
                AlertDialog.Builder builder = new AlertDialog.Builder(com.jinhee.login.login.this);
                String email = emailEdit.getText().toString();
                String pw = pwdEdit.getText().toString();
                if (email.equals("") || pw.equals("")) {
                    builder.setTitle("알림창").setMessage("데이터를 입력해주세요");
//                                             SpannableString temp = new SpannableString("알림창");
//                                             temp.setSpan(new ForegroundColorSpan(Color.RED), 0, temp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                             builder.setTitle(temp).setMessage("데이터를 입력해주세요");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                } else {
                    String checkid =null, checkpwd = null;
                    try {
                        loginTask task = new loginTask(com.jinhee.login.login.this, urlPath);
                        task.execute(email, pw);
                        // String value = task.get();
                        String callBackValue = task.get();
                        if (callBackValue.equals("error") || callBackValue.equals("pwerr")) {
                            builder.setTitle("로그인 실패");
                            if (callBackValue.equals("pwerr"))
                                builder.setMessage("password가 오류입니다.");
                            else
                                builder.setMessage("해당ID가 존재하지 않습니다.");
                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                        } else {
                            try {
                                JSONObject jsonObject = new JSONObject(callBackValue);
                                JSONArray jsonArray = jsonObject.getJSONArray("member");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.getJSONObject(i);
                                    checkid = item.getString("m_email");
                                    checkpwd = item.getString("m_pw");
                                    String name = item.getString("m_name");
                                }
                            } catch (JSONException e) {
                                e.getMessage();
                            }
                            builder.setTitle("로그인 성공").setMessage("ID :" + email + "\nPassword : " + pw + "\n메인페이지로 이동합니다.");
                            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getBaseContext(), email+"님 환영합니다.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(com.jinhee.login.login.this, first.class);
                                    startActivity(intent);
                                }
                            });
                        }
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Button register = findViewById(R.id.register1);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(com.jinhee.login.login.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
//                                                 String message = "";
//                                                 if (value.contains("con_error") || value.contains("db_error") || value.contains("table_error")
//                                                         || value.equals("id_error") || value.equals("pw_error")) {
//                                                     builder.setTitle("로그인 실패");
//                                                     if (value.contains("con_error"))
//                                                         message = "MySQL Connect가 안됩니다(비밀번호 확인)";
//                                                     else if (value.contains("db_error"))
//                                                         message = "DB가 준비되지 않았습니다(DB 확인)";
//                                                     else if (value.contains("table_error"))
//                                                         message = "Table이 준비되지 않았습니다";
//                                                     else if (value.equals("pw_error"))
//                                                         message = "password가 오류입니다";
//                                                     else if (value.equals("id_error"))
//                                                         message = "해당 ID가 존재하지 않습니다.";
//                                                     SpannableString temp = new SpannableString(message);
//                                                     temp.setSpan(new ForegroundColorSpan(Color.RED), 0, message.length(),
//                                                             Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                                     builder.setMessage(temp);
//                                                     builder.setPositiveButton("확인",
//                                                             new DialogInterface.OnClickListener() {
//                                                                 @Override
//                                                                 public void onClick(DialogInterface dialogInterface, int which) {
//                                                                 }
//                                                             });
//                                                 } else {
//                                                     try {
//                                                         JSONObject jsonObject = new JSONObject(value);
//                                                         JSONArray jsonArray = jsonObject.getJSONArray("member");
//                                                         for (int i = 0; i < jsonArray.length(); i++) {
//                                                             JSONObject item = jsonArray.getJSONObject(i);
//                                                             checkid = item.getString("m_email");
//                                                             checkpwd = item.getString("m_pw");
//                                                             String name = item.getString("m_name");
//                                                         }
//                                                     } catch (JSONException e) {
//                                                         e.printStackTrace();
//                                                     }
//                                                     if (checkid != null) {
//                                                         builder.setTitle("로그인 성공");
//                                                         SpannableString temp = new SpannableString("ID :" + checkid + "\nPassword : " + checkpwd
//                                                                 + "\n메인페이지로 이동합니다.");
//                                                         temp.setSpan(new ForegroundColorSpan(Color.RED),
//                                                                 5, 5 + checkid.length(),
//                                                                 Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                                         temp.setSpan(new ForegroundColorSpan(Color.RED),
//                                                                 5 + checkid.length() + 12,
//                                                                 5 + checkid.length() + 12 + checkpwd.length(),
//                                                                 Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                                         builder.setMessage(temp);
//                                                         builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                                             @Override
//                                                             public void onClick(DialogInterface dialogInterface, int which) {
//                                                                 Toast.makeText(getBaseContext(), "메인페이지 이동", Toast.LENGTH_SHORT).show();
//                                                             }
//                                                         });
//                                                     } else {
//                                                         Toast.makeText(getBaseContext(), "오류", Toast.LENGTH_LONG).show();
//                                                     }
//                                                 }
//                                                 AlertDialog alertDialog = builder.create();
//                                                 alertDialog.show();
//                                             } catch (InterruptedException e) {
//                                                 e.printStackTrace();
//                                             } catch (ExecutionException e) {
//                                                 e.printStackTrace();
//                                             }
//                                         }
//                                     }
//                                 });
//                Button register = findViewById(R.id.register1);
//                register.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
//                        startActivity(intent);
//                    }
//                });
//            }
//        }