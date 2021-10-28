package com.jinhee.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText email, password, name, passck;
    private AlertDialog dialog;
    private boolean validated = false;
    String urlPath = "http://101.101.218.94/validatedDB.php";
    String urlPath1 = "http://101.101.218.94/registerDB.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        passck = findViewById(R.id.passck);
        name = findViewById(R.id.name);
        Button validate = findViewById(R.id.validate);
        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                if (validated) {
                    return;
                }
                if (user.equals("")) {
                    dialog = builder.setMessage("아이디(이메일)는 빈 칸 일 수 없습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                    return;
                }
                try {
                    validateTask task = new validateTask(urlPath);
                    task.execute("m_email", user);
                    String callBackValue = task.get();
                    //String message = "";
                    System.out.println(callBackValue);
                    if (callBackValue.isEmpty() || callBackValue.contains("Error")) {
                        builder.setTitle("로그인 실패").setMessage("입력한 회원정보가 없습니다.");
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    } else {
                        try {
                            JSONObject response = new JSONObject(callBackValue);
                            boolean success = response.getBoolean("success");
                            if (success) {
                                dialog = builder.setMessage("사용할 수 있는 아이디 입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                email.setEnabled(false);
                                validated = true;
                                validate.setText("확인");
                            } else {
                                dialog = builder.setMessage("사용할 수 없는 아이디입니다.").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        });
//                    if (value.isEmpty() || value.contains("con_error") || value.contains("db_error") || value.contains("table_error")) {
//                        builder.setTitle("로그인 실패");
//                        if (value.contains("con_error"))
//                            message = "MySQL Connect가 안됩니다(비밀번호 확인)";
//                        else if (value.contains("db_error"))
//                            message = "DB가 준비되지 않았습니다(DB 확인)";
//                        else if (value.contains("table_error"))
//                            message = "Table이 준비되지 않았습니다";
//                        else
//                            message = "입력한 회원 정보가 없습니다.";
//                        SpannableString temp = new SpannableString(message);
//                        temp.setSpan(new ForegroundColorSpan(Color.RED), 0, message.length(),
//                                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                        builder.setMessage(temp);
//                        AlertDialog alertDialog = builder.create();
//                        alertDialog.show();
//                    } else {
//                        try {
//                            JSONObject jsonResponse = new JSONObject(value);
//                            boolean success = jsonResponse.getBoolean("success");
//                            if (success) {
//                                dialog = builder.setMessage("사용할 수 있는 아이디입니다.")
//                                        .setPositiveButton("확인", null)
//                                        .create();
//                                dialog.show();
//                                email.setEnabled(false);
//                                validated = true;
//                                validate.setText("확인");
//                            } else {
//                                validated = false;
//                                dialog = builder.setMessage("사용할 수 없는 아이디입니다.")
//                                        .setPositiveButton("확인", null)
//                                        .create();
//                                dialog.show();
//                            }
//                        } catch (Exception e) {
//                            e.getMessage();
//                        }
//                    }
//                } catch (Exception e) {
//                    e.getMessage();
//                }
//            }
//        });
        Button register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString();
                String pass = password.getText().toString();
                String passCk = passck.getText().toString();
                String userName = name.getText().toString();
                if (user.equals("") || pass.equals("") || passCk.equals("")
                        || userName.equals("")) Toast.makeText(getBaseContext(), "입력하세요",
                        Toast.LENGTH_LONG).show();
                else {
                    if (!passCk.equals(pass)) {
                        Toast.makeText(getBaseContext(), "password를 확인하세요",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        try {
                            registerTask task = new registerTask(urlPath1);
                            task.execute(user, pass, userName);
                            String callBackValue = task.get();
                            //String message = "";
                            System.out.println(callBackValue);
                            AlertDialog.Builder builder =
                                    new AlertDialog.Builder(RegisterActivity.this);
                            if (callBackValue.isEmpty() || callBackValue.contains("Error")) {
                                builder.setTitle("회원 등록 실패").setMessage("입력한 회원정보 등록에 실패했습니다.");
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else {
                                try {
                                    JSONObject response = new JSONObject(callBackValue);
                                    boolean success = response.getBoolean("success");
                                    if (success) {
                                        Toast.makeText(getBaseContext(), "회원등록성공", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(getBaseContext(), "회원등록실패", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                } catch (Exception e) {
                                    e.getMessage();
                                }
                            }
                        } catch (Exception e) {
                            e.getMessage();
                        }
                    }
                }
            }
        });
    }
}
//                            if (value.isEmpty() || value.contains("con_error") ||
//                                    value.contains("db_error") || value.contains("table_error")) {
//                                builder.setTitle("회원 등록 실패");
//                                if (value.contains("con_error"))
//                                    message = "MySQL Connect가 안됩니다(비밀번호 확인)";
//                                else if (value.contains("db_error"))
//                                    message = "DB가 준비되지 않았습니다(DB 확인)";
//                                else if (value.contains("table_error"))
//                                    message = "Table이 준비되지 않았습니다";
//                                else
//                                    message = "입력한 회원 정보 등록에 실패했습니다.";
//                                SpannableString temp = new SpannableString(message);
//                                temp.setSpan(new ForegroundColorSpan(Color.RED), 0, message.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//                                builder.setMessage(temp);
//                                builder.setPositiveButton("확인", null);
//                                AlertDialog alertDialog = builder.create();
//                                alertDialog.show();
//                            } else {
//                                try {
//                                    JSONObject jsonResponse = new JSONObject(value);
//                                    boolean success = jsonResponse.getBoolean("success");
//                                    if (success) {
//                                        Toast.makeText(getBaseContext(), "회원등록성공", Toast.LENGTH_SHORT).show();
//                                        finish();
//                                    } else {
//                                        Toast.makeText(getBaseContext(), "회원등록실패", Toast.LENGTH_SHORT).show();
//                                    }
//                                } catch (Exception e) {
//                                    e.getMessage();
//                                }
//                            }
//                        } catch (Exception e) {
//                            e.getMessage();
//                        }
//                    }
//                }
//            }
//        });
//    }
//}