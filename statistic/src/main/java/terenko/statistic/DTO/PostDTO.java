package terenko.statistic.DTO;

import org.springframework.stereotype.Component;
@Component

public class PostDTO {
    String id;
    String slug;
    String likes_count;
    String views_count;
    String bookmarks_count;
    String reposts_count;
    String comments_count;
    String mentions_count;


    public PostDTO(String id, String slug, String likes_count, String views_count, String bookmarks_count, String reposts_count, String comments_count) {
        this.id = id;
        this.slug = slug;
        this.likes_count = likes_count;
        this.views_count = views_count;
        this.bookmarks_count = bookmarks_count;
        this.reposts_count = reposts_count;
        this.comments_count = comments_count;
    }


    public PostDTO() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLikes_count() {
        return likes_count;
    }

    public void setLikes_count(String likes_count) {
        this.likes_count = likes_count;
    }

    public String getViews_count() {
        return views_count;
    }

    public void setViews_count(String views_count) {
        this.views_count = views_count;
    }

    public String getBookmarks_count() {
        return bookmarks_count;
    }

    public void setBookmarks_count(String bookmarks_count) {
        this.bookmarks_count = bookmarks_count;
    }

    public String getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(String reposts_count) {
        this.reposts_count = reposts_count;
    }

    public String getComments_count() {
        return comments_count;
    }

    public void setComments_count(String comments_count) {
        this.comments_count = comments_count;
    }

    public String getMentions_count() {
        return mentions_count;
    }

    public String getId() {
        return id;
    }

    public String getSlug() {
        return slug;
    }

    public void setMentions_count(String mentions_count) {
        this.mentions_count = mentions_count;
    }
}
