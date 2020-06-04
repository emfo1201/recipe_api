package android.recipeapi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private ArrayList<RecipeItem> recipeList;
    private RequestQueue requestQueue;
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_CREATOR = "creatorName";
    public static final String EXTRA_LIKES = "likeCount";
    public static final String EXTRA_SOURCE = "source";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        final String image = "https://spoonacular.com/recipeImages/";
        String imageUrl = "https://api.spoonacular.com/recipes/search?query=cheese&apiKey=";
        
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, imageUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String creatorName = hit.getString("title");
                                String source = hit.getString("sourceUrl");
                                String imageUrl = image + hit.getString("image");
                                int likeCount = hit.getInt("readyInMinutes");

                                recipeList.add(new RecipeItem(imageUrl, creatorName, source, likeCount));
                            }
                            recipeAdapter = new RecipeAdapter(MainActivity.this, recipeList);
                            recyclerView.setAdapter(recipeAdapter);
                            recipeAdapter.setOnItemClickListener(MainActivity.this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, RecipeActivity.class);
        RecipeItem clickedItem = recipeList.get(position);

        intent.putExtra(EXTRA_URL, clickedItem.getImageUrl());
        intent.putExtra(EXTRA_CREATOR, clickedItem.getTitle());
        intent.putExtra(EXTRA_LIKES, clickedItem.getTime());
        intent.putExtra(EXTRA_SOURCE, clickedItem.getSource());

        startActivity(intent);
    }
}