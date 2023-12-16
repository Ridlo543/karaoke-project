package Model;
import java.io.Serializable;

public class Song implements Serializable{
    
    
    private String atist;
    private String title;
    private String album;
    private String path;
    private String year;
    private long lengthInSeconds;
    private byte[] imageData;
    private String summary;
    private int id;
    private String fileName;

    
    
    public void setFileName(String name) {
        this.fileName = name;
    }

    public String getFileName() {
        return fileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    

    public String getAtist() {
        return atist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getYear() {
        return year;
    }

    public long getLengthInSeconds() {
        return lengthInSeconds;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setAtist(String atist) {
        this.atist = atist;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setLengthInSeconds(long lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getSummary() {
        
        if(summary==null){
            summary = atist + " " +album+ " " + year;
            return summary;
        }
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Song{" + "atist=" + atist + ", title=" + title + ", album=" + album + ", path=" + path + ", year=" + year + ", lengthInSeconds=" + lengthInSeconds + ", imageData=" + imageData + ", summary=" + summary + ", id=" + id + ", name=" + fileName + '}';
    }
    
    
}
