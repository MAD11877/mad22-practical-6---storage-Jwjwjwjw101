package sg.edu.np.practical_6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //launch from ListActivity
        Intent receivingEnd = getIntent();
        //Integer genInt = receivingEnd.getIntExtra("genInt",0);
        Integer userID = receivingEnd.getIntExtra("userID",-100);
        DBHandler db = new DBHandler(this);
        User user = db.getSpecificUSer(userID);
        //User usr = ListActivity.user20List.get(position);

        //TextView txt = findViewById(R.id.idText);
        //txt.setText(String.format("MAD %s",genInt));//java string formatting, like python printf

        TextView nameTxt = findViewById(R.id.nameText);
        TextView descTxt = findViewById(R.id.descText);

        nameTxt.setText(String.format("%s", user.name));
        descTxt.setText(String.format("%s", user.description));

        Button btn = findViewById(R.id.following);
        //User usr = initUser();
        setF(user,btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastN;
                if(user.followed == false){ //if user is not following
                    user.followed = true;//set to follow
                    toastN = "Followed";
                }
                else{//if user is following
                    user.followed = false; //set to unfollow
                    toastN = "Unfollowed";
                }
                setF(user,btn);
                db.updateUser(user);

                //Toast message
                Toast tNotif = Toast.makeText(MainActivity.this,toastN,Toast.LENGTH_SHORT);
                tNotif.show();
            }
        });

        //event/onclick listener for message button
        Button messageButton = findViewById(R.id.message);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newAct = new Intent(MainActivity.this,MessageGroup.class);
                startActivity(newAct);
            }
        });

    }

    //method to initialise user
    public User initUser(){
        User user = new User("username","desc",1,false);
        return user;
    }

    //method to set the text based on the condition
    public void setF(User user,Button btn){
        TextView txt = btn;
        if(user.followed == false){
            txt.setText("Follow");
        }
        else{
            txt.setText("Unfollow");
        }
    }
}