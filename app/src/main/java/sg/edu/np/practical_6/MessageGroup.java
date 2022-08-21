package sg.edu.np.practical_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Button grp1button, grp2button;
        grp1button = findViewById(R.id.grp1button);
        grp2button = findViewById(R.id.grp2button);
        grp1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefrag(new group1());
            }
        });
        grp2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefrag(new group2());
            }
        });
    }
    public void replacefrag(Fragment frag){
        FragmentTransaction fragt = getSupportFragmentManager().beginTransaction();
        fragt.replace(R.id.framed,frag);
        fragt.commit();
    }
}