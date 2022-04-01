package com.tanvir.dollartouch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.tanvir.dollartouch.DataModel.Category;
import com.tanvir.dollartouch.DataModel.CategoryList;
import com.tanvir.dollartouch.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{

    private Context context;
    private CategoryList categoryList;
    private  OnItemClickListner listner;

    public CategoryAdapter(Context context, CategoryList categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.quiz_item_card,parent,false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       Category category=categoryList.getCategorys().get(position);

       holder.titleTv.setText(""+category.getTitle());
       holder.descriptionTv.setText(""+category.getDescription());
        holder.imageView.setImageResource(category.getImage());


    }

    @Override
    public int getItemCount() {
        return categoryList.getCategorys().size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        TextView titleTv,descriptionTv;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv=itemView.findViewById(R.id.ci_titleTv);
            descriptionTv=itemView.findViewById(R.id.ci_descriptionTv);
            imageView=itemView.findViewById(R.id.quizItemImageViewId);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(listner!=null){
                int position=getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listner.onItemClick(position);
                }
            }
        }

    }
    public interface  OnItemClickListner{
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner=listner;
    }


}
