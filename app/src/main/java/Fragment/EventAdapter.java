package Fragment;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import EventbriteAPI.Models.Description;
import EventbriteAPI.Models.Event;
import EventbriteAPI.Models.EventsList;
import EventbriteAPI.Models.Name;
import EventbriteAPI.Models.Ticket;
import weathevent.weathevent.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the events in a list
    private EventsList eventList;

    //getting the context and event list with constructor
    public EventAdapter(Context mCtx, EventsList eventList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.fragment_explore_list, null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        //getting the event of the specified position
        Event event = eventList.getEvent(position);
        Name name = event.getName();
        Description description = event.getDescription();
        Ticket ticket = event.getTicket();
        //binding the data with the viewholder views
        holder.textViewTitle.setText(name.getText());
        holder.textViewShortDesc.setText(description.getText());
        if(ticket.getFree() == true){
            holder.textViewPrice.setText(String.valueOf("Free"));
        }
        if(ticket.getDonation() == true){
            holder.textViewPrice.setText(String.valueOf("Charity"));
        }

        //holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(event.getImage()));

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    class EventViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        ImageView imageView;

        public EventViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

