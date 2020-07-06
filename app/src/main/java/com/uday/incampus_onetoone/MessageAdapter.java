package com.uday.incampus_onetoone;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter {

    private static final int  TYPE_MESSAGE_SENT = 0;
    private static final int TYPE_MESSAGE_RECIEVED = 1;
    private static final int TYPE_IMAGE_SENT = 2;
    private static final int TYPE_IMAGE_RECEIVED = 3;

    private LayoutInflater inflater;
    private List<JSONObject> messages = new ArrayList<>();

    public MessageAdapter(LayoutInflater inflater)
    {
        this.inflater = inflater;
    }

    public class SentMessageHolder extends RecyclerView.ViewHolder{
        TextView messageText;
        public SentMessageHolder(@NonNull View itemView) {
            super(itemView);
            messageText = itemView.findViewById(R.id.senttext);
        }
    }

//    public class SentImageHolder extends RecyclerView.ViewHolder{
//
//        ImageView imageView;
//        public SentImageHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.imageView);
//        }
//    }

    public class ReceivedMessageHolder extends RecyclerView.ViewHolder{

        TextView recievedText;
        TextView nameView;
        public ReceivedMessageHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameText);
            recievedText = itemView.findViewById(R.id.recievedmessage);
        }
    }

    @Override
    public int getItemViewType(int position) {

        JSONObject jsonObject = messages.get(position);
        try {
            if (jsonObject.getString("name").equals(UserDetails.name)){
                if (jsonObject.has("message")){
                    return TYPE_MESSAGE_SENT;
                }
                else{
                    return TYPE_IMAGE_SENT;
                }
            }
            else{

                if (jsonObject.has("message")){
                    return TYPE_MESSAGE_RECIEVED;
                }
                else{
                    return TYPE_IMAGE_RECEIVED;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return -1;
    }

//    public class RecievedImageHolder extends RecyclerView.ViewHolder{
//        TextView nameView;
//        ImageView imageView;
//        public RecievedImageHolder(@NonNull View itemView) {
//            super(itemView);
//            nameView = itemView.findViewById(R.id.nameText);
//            imageView = itemView.findViewById(R.id.imageView);
//        }
//    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        switch (viewType){
//            case TYPE_IMAGE_RECEIVED:
//                view =inflater.inflate(R.layout.item_received_image,parent,false);
//                return new RecievedImageHolder(view);
//            case TYPE_IMAGE_SENT:
//                view = inflater.inflate(R.layout.item_sent_image,parent,false);
//                return new SentImageHolder(view);
            case TYPE_MESSAGE_RECIEVED:
                view = inflater.inflate(R.layout.item_recived_message,parent,false);
                return new ReceivedMessageHolder(view);
            case TYPE_MESSAGE_SENT:
                view = inflater.inflate(R.layout.item_sent_message,parent,false);
                return new SentMessageHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        JSONObject jsonObject = messages.get(position);
        try {
            if (jsonObject.has("message")) {
                if (jsonObject.get("name").equals(UserDetails.getName())) {
                    if (jsonObject.has("message")) {

                        SentMessageHolder messageHolder = (SentMessageHolder) holder;
                        messageHolder.messageText.setText(jsonObject.getString("message"));
                    }
//                else{
//                    SentImageHolder imageHolder = (SentImageHolder) holder;
//                    Bitmap bitmap = getBitmapFromString(jsonObject.getString("image"));
//                    imageHolder.imageView.setImageBitmap(bitmap);
//
//                }
                } else {
                if (jsonObject.has("message"))
                    {
                        ReceivedMessageHolder messageHolder = (ReceivedMessageHolder) holder;
                        messageHolder.recievedText.setText(jsonObject.getString("message"));
                        messageHolder.nameView.setText(jsonObject.getString("name"));
                    }
//                else{
//                    RecievedImageHolder imageHolder = (RecievedImageHolder) holder;
//                    Bitmap bitmap = getBitmapFromString(jsonObject.getString("image"));
//                    imageHolder.imageView.setImageBitmap(bitmap);
//                    imageHolder.nameView.setText(jsonObject.getString("name"));
//                }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private Bitmap getBitmapFromString(String image) {
        byte[] bytes = Base64.decode(image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes,0,bytes.length);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
    public void addItem(JSONObject jsonObject){
        messages.add(jsonObject);
        notifyDataSetChanged();
    }
    public void addItems(JSONArray array){
        for(int i=0;i<array.length();i++){
            try {
                messages.add(array.getJSONObject(i));
                notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void removeItems(){
        messages.clear();
    }
}
