package ra.model;

import javax.persistence.*;

@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String kindOfMusic;
    private String file;

    public Song() {
    }

    public Song(int id, String name, String kindOfMusic, String file) {
        this.id = id;
        this.name = name;
        this.kindOfMusic = kindOfMusic;
        this.file = file;
    }

    public Song(String name, String kind, String imgUrl) {
        this.name = name;
        this.kindOfMusic = kindOfMusic;
        this.file = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKindOfMusic() {
        return kindOfMusic;
    }

    public void setKindOfMusic(String kindOfMusic) {
        this.kindOfMusic = kindOfMusic;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
