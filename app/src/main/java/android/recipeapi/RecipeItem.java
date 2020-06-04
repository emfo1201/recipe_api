package android.recipeapi;

public class RecipeItem {
    private String imageUrl;
    private String title;
    private String source;
    private int time;

    public RecipeItem(String imageUrl, String title, String source, int time) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.source = source;
        this.time = time;
    }

    // Get functions to get info about the different recipes
    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSource() {
        return source;
    }

    public int getTime() {
        return time;
    }
}
