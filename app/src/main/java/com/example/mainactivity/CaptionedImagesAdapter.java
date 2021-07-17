package com.example.mainactivity;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

//CaptionedImagesAdapter.ViewHolder this is just the type and not the object. for object x.new ViewHolder();
// x is object of adapter
public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>{
/* recyclerView.adapter has viewHolder in generic, since CaptionedImagesAdapter.ViewHolder is a static,it is
    before this class gets loaded, so, we can use it as a argument. */
    private String[] captions;
    private int[] imageIds;
    private Listener listener;
// the reason we need (static) nested class and not (non-static ) is because, we don't want the inner class to make changes
    interface Listener{
        void onClick(int position);
    }

    //ViewHolder is also in recycler view.
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private  CardView cardView; // outer class can access private members of inner class see stack.
        //viewHolder is a abstract class

        public ViewHolder (CardView v){
            super(v); // this is a class super and not a method super.also, this sets variable itemView in the
            //super code
            cardView = v;
        }
    }

    /* here we have a static nested class instead of a static class outside captioned images adapter because we
don't want any other class to use ViewHolder.as we only need it in this class.
 */
    public CaptionedImagesAdapter(String[] captions,  int[] imagesIds){
        this.captions = captions;
        this.imageIds = imagesIds;
    }

    @Override
    public int getItemCount(){
        return captions.length;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        CardView cv =(CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_captioned_image,parent,false);
        return new ViewHolder(cv);
    } //recyclerView calls this repeatedly when the recycler view is first constructed to build a set of viewHolders
//LayoutInflater.from(parent.getContext()) -layoutInflater object and get context with recycle view
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        final CardView cardView= holder.cardView; /* this is stored in method in a static class.so we are not
        violating access specifier rules. position start with 0 and not unique id */
        ImageView imageView= cardView.findViewById(R.id.info_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable);
        imageView.setContentDescription(captions[position]);
        TextView textView= cardView.findViewById(R.id.info_text);
        textView.setText(captions[position]);
        cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              if (listener != null){
                  listener.onClick(position);
              }
            }
        });
    }
}

