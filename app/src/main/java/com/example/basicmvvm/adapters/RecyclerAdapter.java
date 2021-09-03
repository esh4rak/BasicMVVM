package com.example.basicmvvm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.basicmvvm.R;
import com.example.basicmvvm.models.NicePlace;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;




public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerAdapter.PlaceViewHolder>{

    private ArrayList<NicePlace> mNicePlaces;
    private OnItemClickListener mListener;
    private Context mContext;



    public void setOnItemClickListener(OnItemClickListener listener){

        mListener = listener;

    }


    public RecyclerAdapter(Context context, ArrayList<NicePlace>nicePlaces){

        mNicePlaces = nicePlaces;
        mContext = context;

    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new PlaceViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {

        NicePlace currentItem = mNicePlaces.get(position);

        holder.placeName.setText(currentItem.getTitle());



        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(currentItem.getImageUrl())
                .into(holder.placeImage);

    }

    @Override
    public int getItemCount() {
        return mNicePlaces.size();
    }


    public static class PlaceViewHolder extends RecyclerView.ViewHolder{

        TextView placeName;
        CircleImageView placeImage;
        CardView cardView;

        public PlaceViewHolder(@NonNull final View itemView , final OnItemClickListener listener) {
            super(itemView);

            placeImage = itemView.findViewById(R.id.image);
            placeName = itemView.findViewById(R.id.imageName);
            cardView = itemView.findViewById(R.id.item_card);



            cardView.setOnClickListener(v -> {
                if(listener != null)
                {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(pos ,v);
                    }
                }
            });


        }
    }

    public interface OnItemClickListener {

        void onItemClick(int position, View v);

    }
}
