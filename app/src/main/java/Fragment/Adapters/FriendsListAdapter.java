package Fragment.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import Database.StorageManager;
import Database.StorageManagerImplFirebaseRoom;
import POJO.User;
import weathevent.weathevent.R;

/**
 * Created by Sergio Vacas on 24/04/2018.
 */

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.FriendViewHolder>{
    List<User> friends;
    Context context;

    public FriendsListAdapter(Context context,List<User> friends) {
        this.friends=friends;
        this.context=context;
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.friends_listitem,parent,false);
        FriendViewHolder holder= new FriendViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, final int position) {
        final User friend = friends.get(position);
        holder.name.setText(friend.getSurname()+", "+friend.getName());
        holder.email.setText(friend.getEmail());
        holder.eventsCounter.setText(""+friend.getFavoriteEventIdsList().size());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final StorageManager storageManager = StorageManagerImplFirebaseRoom.getInstance(context);
                final User currentUser = storageManager.getCurrentUser();
                currentUser.removeFriend(friend.getEmail());

                friends.remove(position);
                FriendsListAdapter.this.notifyDataSetChanged();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        storageManager.updateUser(currentUser);
                    }
                }).start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public static class FriendViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView email;
        TextView eventsCounter;
        ImageView remove;

        public FriendViewHolder(View itemView){
            super(itemView);
            this.name=itemView.findViewById(R.id.tv_name);
            this.email=itemView.findViewById(R.id.tv_email);
            this.eventsCounter=itemView.findViewById(R.id.tv_eventsCounter);
            this.remove=itemView.findViewById(R.id.iv_delete);
        }
    }
}
