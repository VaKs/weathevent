package Fragment.Adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import EventbriteAPI.Models.Description;
import EventbriteAPI.Models.End;
import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Logo;
import EventbriteAPI.Models.Name;
import EventbriteAPI.Models.Start;
import EventbriteAPI.service.EventBriteDownloadImage;
import Fragment.ExploreFragmentList;
import Fragment.FavouriteEventsFragment;
import weathevent.weathevent.R;

public class FavouriteEventAdapter extends RecyclerView.Adapter<FavouriteEventAdapter.EventViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the events in a list
    private EventsList eventList;
    private FavouriteEventsFragment fragmentList;

    //getting the context and event list with constructor
    public FavouriteEventAdapter(Context mCtx, EventsList eventList, FavouriteEventsFragment fragmentList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
        this.fragmentList = fragmentList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.favourite_listitem, null);
        //EventViewHolder holder= new EventViewHolder(view);
        return new FavouriteEventAdapter.EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        //getting the event of the specified position
        Event event = eventList.getEvent(position);
        Name name = event.getName();
        Logo eventLogo = event.getLogo();
        //binding the data with the viewholder views
        String eventLogoURL = eventLogo.getUrl();
        String eventName = name.getText();
        holder.textViewTitle.setText(eventName);
        Log.i("OOOOOO",holder.textViewTitle.getText().toString());

        try {
            new EventBriteDownloadImage((ImageView) holder.imageView)
                    .execute(eventLogoURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        holder.btn_details.setTag(event.getResourceUri());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewTitle;
        ImageView imageView;
        Button btn_details;

        public EventViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textView_fav);
            imageView = itemView.findViewById(R.id.iv_event);
            btn_details = itemView.findViewById(R.id.button_fav);
            btn_details.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v.getId() == btn_details.getId()) {
                fragmentList.goToPreview(btn_details.getTag().toString());
            }
        }
    }
}