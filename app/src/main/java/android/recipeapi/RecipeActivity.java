package android.recipeapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.recipeapi.MainActivity.EXTRA_CREATOR;
import static android.recipeapi.MainActivity.EXTRA_LIKES;
import static android.recipeapi.MainActivity.EXTRA_SOURCE;
import static android.recipeapi.MainActivity.EXTRA_URL;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String creatorName = intent.getStringExtra(EXTRA_CREATOR);
        String source = intent.getStringExtra(EXTRA_SOURCE);
        int likeCount = intent.getIntExtra(EXTRA_LIKES, 0);

        ImageView imageView = findViewById(R.id.recipe_image);
        TextView textViewCreator = findViewById(R.id.recipe_creator);
        TextView textViewLikes = findViewById(R.id.recipe_likes);
        TextView textViewSource = findViewById(R.id.recipe_source);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewCreator.setText(creatorName);
        textViewSource.setText(source);
        Linkify.addLinks(textViewSource , Linkify.WEB_URLS);
        textViewLikes.setText("Cooking time: " + likeCount + " minutes");
    }
}
