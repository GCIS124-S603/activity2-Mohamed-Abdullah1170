// Faiza Fatima, Prisha Modi, Iman Akbar, Mohamed Najumudeen
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TvSet extends Channel implements EventHandler<ActionEvent>{

    Channel[] channels = new Channel[10]; 
    //Creating array

    private int channelNumber = 0; 
    //indeximg

    private Label descriptionLabel; 
    //Setting the variable for description of the channel which will be displayed at the top

    Button[] buttons = new Button[13]; 
    //Creating array for buttons

    private ImageView centerImage; 
    //Display at center

    BorderPane bp = new BorderPane(); 
    //Border pane layout

    public TvSet(){
        //Constructor which will create 10 channels when invoked using new keyword
        channels[0] = new Channel("TOY STORY 3", "images/channel_1.png", "src/audios/audio_1.mp3");
        channels[1] = new Channel("THE OFFICE", "images/channel_2.png", "src/audios/audio_2.mp3");
        channels[2] = new Channel("MISSION IMPOSSIBLE", "images/channel_3.png","src/audios/audio_3.mp3");
        channels[3] = new Channel("THUNDERCATS", "images/channel_4.png","src/audios/audio_4.mp3");
        channels[4] = new Channel("TOP GUN", "images/channel_5.png","src/audios/audio_5.mp3");
        channels[5] = new Channel("JAMES BOND", "images/channel_6.png","src/audios/audio_6.mp3");
        channels[6] = new Channel("SCOOBY DOO", "images/channel_7.png","src/audios/audio_7.mp3");
        channels[7] = new Channel("DOCTOR WHO", "images/channel_8.png","src/audios/audio_8.mp3");
        channels[8] = new Channel("DREAM HOUSE", "images/channel_9.png","src/audios/audio_9.mp3");
        channels[9] = new Channel("CONCENTRATION", "images/channel_10.png", "src/audios/audio_10.mp3");
    }

    @Override
    public void start(Stage stage) throws Exception { //Implementing the start method in the Application class
        
        descriptionLabel = makeLabel((channelNumber+1) + " - " + channels[channelNumber].getDescription()); //Setting the label to description of the current channel

        descriptionLabel.setMaxWidth(Double.MAX_VALUE); //To access the entire top of the Border Pane
        
        centerImage = new ImageView(channels[channelNumber].getImage()); //Getting image to assign at center

        centerImage.setFitHeight(480); //Setting height

        centerImage.setFitWidth(620); //Setting width

        
        for (int i=0; i<10; i++){ //Buttons for channels
            buttons[i] = makeButton(Integer.toString(i+1), Color.BLACK);
        }
        
        Image media1 = new Image("images/volume_increase.png"); 

        ImageView volImage1 = new ImageView(media1);

        volImage1.setFitHeight(50); //Setting height

        volImage1.setFitWidth(50); // Setting width

        buttons[10] = makeButton(" ", volImage1); //Button for volume increase
        
        Image media2 = new Image("images/volume_decrease.png");

        ImageView volImage2 = new ImageView(media2);

        volImage2.setFitHeight(50); //Setting height

        volImage2.setFitWidth(50); //Setting width

        buttons[11] = makeButton(" ", volImage2); //Button for volume decrease
        
        int volumeLabel = (int) channels[channelNumber].getVolume()*10; //This formula shows the number to be displayed as volume ranging from 0 - 1

        buttons[12] = makeButton(Integer.toString(volumeLabel), Color.RED); //Creating a button which will display the current volume
        
        buttons[12].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY))); //Setting background for the button
        
        GridPane gp = new GridPane(); //Creating a Grid Pane layout to assign buttons into rows and columns
        int z = 0; 
        for(int i=0; i<3; i++){ //Loop for row
            for (int j=0; j<3; j++){ //Loop for column
                gp.add(buttons[z] , j, i); //Adding button for each position
                z++; //Incrementing
            }
        }
        //Adding other buttons according to their rows and columns
        gp.add(buttons[9], 1, 3);
        gp.add(buttons[10], 0, 4);
        gp.add(buttons[11], 1, 4);
        gp.add(buttons[12], 2, 4);
        
        channels[channelNumber].start(); //Calling the start method from the Channel class to play the media player
        
        bp.setTop(descriptionLabel);

        bp.setCenter(centerImage);

        bp.setRight(gp);
        
        Scene scene = new Scene(bp); //Creating a scene

        stage.setTitle("TV Shows"); //Title of stage

        stage.setScene(scene); //Scene of stage

        stage.show(); //Displaying the stage
    }  

    public Label makeLabel(String text){ //Making label
        Label label = new Label(text);
        label.setFont(new Font("Veranda", 30));
        label.setTextFill(Color.RED);
        label.setBackground(new Background (new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        return label;
    }

    public Button makeButton(String text, Color font){ //Making button for channel numbers
        Button button = new Button(text);
        button.setOnAction(this);
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        button.setFont(new Font("Veranda", 30));
        button.setTextFill(font);
        return button;
    }

    public Button makeButton(String text, ImageView image){ 
        Button button = makeButton(text, Color.WHITE);
        button.setGraphic(image); //Setting the icon image to the button
        return button;
    }
    
    @Override
    public void handle(ActionEvent event){ //What happens once a button is clicked
        for (int i=0; i<13; i++){ //Going through all the buttons in the array
            if (event.getSource() == buttons[i]){
                if (i == 10){ //If button 10 is clicked(volume increase);the following code runs
                    if(channels[channelNumber].getVolume()<1.0){ //Check if volume is less than the max
                        channels[channelNumber].increaseVolume(); //Increase the volume
                        int volumeLabel = (int) (channels[channelNumber].getVolume()*10); //Calculate new volume
                        buttons[12].setText(Integer.toString(volumeLabel));
                    }
                }

                else if (i == 11){ //If button 11 is clicked(volume decrease);the following code runs
                    if (channels[channelNumber].getVolume()>0.0){ //Check if volume is more than 0(minimum)
                        channels[channelNumber].decreaseVolume(); //Calculate new volume
                        int volumeLabel = (int) (channels[channelNumber].getVolume()*10); //Calculate new volume
                        buttons[12].setText(Integer.toString(volumeLabel)); //Display new volume
                    }
                }

                else{ //If any of the channel number buttons is clicked
                    channels[channelNumber].stop(); //Stop audio
                    channelNumber = i; //Change the i to new channel
                    descriptionLabel.setText((channelNumber+1) + " - " + channels[channelNumber].getDescription()); //Modify the the channel displayed at the top
                    centerImage = new ImageView(channels[channelNumber].getImage()); 
                    centerImage.setFitWidth(620);
                    centerImage.setFitHeight(480);
                    bp.setCenter(centerImage); //Modify the image of the new channel to be displayed at the center
                    channels[channelNumber].start(); //Start the audio of the new channel
                }
            }
        }
        
    }
    public static void main(String[] args) { 
        launch (args);
    }
}