package Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import weathevent.weathevent.R;
import weathevent.weathevent.WeatheventActivity;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the events in a list
    private EventsList eventList;
    private ExploreFragmentList fragmentList;

    //getting the context and event list with constructor
    public EventAdapter(Context mCtx, EventsList eventList, ExploreFragmentList fragmentList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
        this.fragmentList = fragmentList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.explore_listitem, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        //getting the event of the specified position
        Event event = eventList.getEvent(position);
        Name name = event.getName();
        Description description = event.getDescription();
        Start start = event.getStart();
        End end = event.getEnd();
        Boolean isFree = event.isFree();
        Logo eventLogo = event.getLogo();
        //binding the data with the viewholder views
        String eventLogoURL = eventLogo.getUrl();
        String eventName = name.getText();
        String eventDescription = description.getText();
        String eventStart = start.getLocal();
        String eventEnd = end.getLocal();
        holder.textViewTitle.setText(eventName);
        holder.textViewShortDesc.setText(eventDescription);
        Date dateStart = new Date();
        Date dateEnd = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            dateStart = format.parse(eventStart);
            dateEnd = format.parse(eventEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String fullData = dateStart.getDay() + "." + dateStart.getMonth() + " " + dateStart.getHours() + ":" + dateStart.getMinutes();

        if (isFree == true) {
            holder.textViewPrice.setText(String.valueOf("Free"));
        } else {
            holder.textViewPrice.setText(String.valueOf("Consult the price"));
        }
        holder.textViewRating.setText(dateStart.toLocaleString());

        try {
            new EventBriteDownloadImage((ImageView) holder.imageView)
                    .execute(eventLogoURL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        holder.button_read_more.setTag(event.getResourceUri());
        Log.i("*********** ResourceUri",event.getResourceUri());

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle;
        TextView textViewShortDesc;
        TextView textViewRating;
        TextView textViewPrice;
        ImageView imageView;
        Button button_read_more;

        public EventViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            button_read_more = itemView.findViewById(R.id.button_read_more);
            button_read_more.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (v.getId() == button_read_more.getId()) {
                fragmentList.goToPreview(button_read_more.getTag().toString());
            }
        }
    }
}