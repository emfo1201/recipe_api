package android.recipeapi;

public class RecipeItem {
    private String imageUrl;
    private String creator;
    private String source;
    private int likes;

    public RecipeItem(String imageUrl, String creator, String source, int likes) {
        this.imageUrl = imageUrl;
        this.creator = creator;
        this.source = source;
        this.likes = likes;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCreator() {
        return creator;
    }

    public String getSource() {
        return source;
    }

    public int getLikes() {
        return likes;
    }
}
