package ra.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "pictures")
public class Pictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate createdDate;
    private int evaluate;
    private String author;
    private String feedback;
    private int likes;

    public Pictures() {
    }

    public Pictures(int id, int evaluate, String author, String feedback, int likes) {
        this.id = id;
        this.evaluate = evaluate;
        this.author = author;
        this.feedback = feedback;
        this.likes = likes;
    }

    public Pictures(int evaluate, String author, String feedback,LocalDate createdDate) {
        this.evaluate = evaluate;
        this.author = author;
        this.feedback = feedback;
        this.createdDate= createdDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(int evaluate) {
        this.evaluate = evaluate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
