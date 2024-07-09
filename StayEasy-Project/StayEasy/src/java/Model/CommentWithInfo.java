package Model;

public class CommentWithInfo {
    private Comment comment;
    private String username;
    private String houseName;

    public CommentWithInfo(Comment comment, String username, String houseName) {
        this.comment = comment;
        this.username = username;
        this.houseName = houseName;
    }


    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
