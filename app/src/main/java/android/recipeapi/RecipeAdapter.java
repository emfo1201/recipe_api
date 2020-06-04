package android.recipeapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
    private Context context;
    private ArrayList<RecipeItem> recipeList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public RecipeAdapter(Context context, ArrayList<RecipeItem> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.recipe_item, viewGroup, false);
        return new RecipeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        RecipeItem currentItem = recipeList.get(i);
        String image = currentItem.getImageUrl();
        String creatorName = currentItem.getTitle();
        String time = context.getResources().getString(R.string.cooking_time);
        String minutes = context.getResources().getString(R.string.cooking_minutes);
        int likeCount = currentItem.getTime();
        String setTimeText = time + " " + likeCount + " " + minutes;

        recipeViewHolder.textViewCreator.setText(creatorName);
        recipeViewHolder.textViewLikes.setText(setTimeText);
        Picasso.with(context).load(image).fit().centerInside().into(recipeViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textViewCreator;
        public TextView textViewLikes;

        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_recipe);
            textViewCreator = itemView.findViewById(R.id.textView_name);
            textViewLikes = itemView.findViewById(R.id.textView_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
