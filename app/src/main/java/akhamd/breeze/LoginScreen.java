package akhamd.breeze;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LoginScreen extends AppCompatActivity {
    Button LoginButton;
    EditText UsernameText;
    EditText PasswordText;
    Intent LoginSuccess;
    User CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = (Button) findViewById(R.id.LoginButton);
        UsernameText = (EditText) findViewById(R.id.LoginUsername);
        PasswordText = (EditText) findViewById(R.id.LoginPassword);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentUser = getUser(UsernameText.getText().toString(), PasswordText.getText().toString());

                if (CurrentUser != null)
                {
                    Toast.makeText(getApplicationContext(), "Welcome " + CurrentUser.getName() + "!",
                            Toast.LENGTH_SHORT).show();

                    if (CurrentUser.getType() == User.UserType.GUEST)
                    {
                        // Redirect to main screen
                        LoginSuccess = new Intent(LoginScreen.this, MainScreen.class);
                        startActivity(LoginSuccess);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private User getUser(String username, String password)
    {
        return new User(username, "Redmond, WA", "http://appamatix.com/wp-content/uploads/2016/05/04-450x427.jpg", User.UserType.GUEST);
    }
}
