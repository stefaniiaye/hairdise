package pja.edu.pl.s27591.hairadise.DTOs;

import jakarta.validation.constraints.NotNull;

public class FeedbackDTO {
    @NotNull
    private int rating;
    private String comment;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
