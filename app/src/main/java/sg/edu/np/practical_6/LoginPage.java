package sg.edu.np.practical_6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");
        ((Button)findViewById(R.id.loginButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView inName = findViewById(R.id.inputUsrName);
                TextView inPwd = findViewById(R.id.inputPwd);
                String iName = inName.getText().toString();
                String iPwd = inPwd.getText().toString();
                ref.child("mad").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if ((iName.equals(snapshot.child("username").getValue().toString())) && (iPwd.equals(snapshot.child("password").getValue().toString()))){
                            Intent i = new Intent(LoginPage.this, ListActivity.class);
                            startActivity(i);
                        }
                        else {
                            Toast.makeText(LoginPage.this, "Incorrect name or password", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("fail", "Failed to read value", error.toException());

                    }
                });


            }
        });
    }

}
