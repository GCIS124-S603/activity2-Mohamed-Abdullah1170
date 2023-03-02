import java.io.File; 
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


 //This is a helper class which creates an instance of one channel on a TV Set, given the description, image path and audio path for the channel


public class Channel{
    private String description; 
    //Setting variable for the description of the image
    private Image image; 
    //Setting variable for the image
    private MediaPlayer mediaPlayer; 
    //Setting variable for the media player
    private double volume = 1; 
    //Setting the volume to 1

    //Constructor of the Channel class which creates an instance by using the new keyword to invoke it
    public Channel(String description, String imagePath, String audioPath){ 
        this.description = description;

        this.image= new Image(imagePath);

        this.mediaPlayer = new MediaPlayer(new Media(new File(audioPath).toURI().toString()));
    }
    public Image getImage(){ //Getter for image
        return image;}

    public String getDescription(){ //Getter for description
        return description;
    }

    public void start(){ //This will play the audio
        mediaPlayer.play();
    }
    
    public void stop(){ //This method will stop the audio of the channel
        mediaPlayer.stop(); //Accessing the stop method in mediaPlayer class
    }

    public double getVolume(){ //Getter for volume
        return this.volume;
    }

    public void setVolume(double volume){ //Setter for volume 
        mediaPlayer.setVolume(volume);
        this.volume = volume;
    }

    public void increaseVolume(){ //Increase volume by 0.1 if not at max(1)
        if (volume<1.0){ 
            mediaPlayer.setVolume(volume+0.1);
            
            this.volume = volume+0.1;
        }
    }

    public void decreaseVolume(){ //Decrease volume by 0.1 if not at 0
        if (volume>0.0){
            mediaPlayer.setVolume(volume-0.1);

            this.volume = volume-0.1;
        }
    }
}