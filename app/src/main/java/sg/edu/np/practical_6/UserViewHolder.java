package sg.edu.np.practical_6;

import android.content.Intent;
import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder{
    TextView uName, uDesc;
    ImageView uPic;
    public UserViewHolder(View itemView){
        super(itemView);
        uName = itemView.findViewById(R.id.userName);
        uDesc = itemView.findViewById(R.id.userDesc);
        uPic = itemView.findViewById(R.id.userPic);
    }


}
