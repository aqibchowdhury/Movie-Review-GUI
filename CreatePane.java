package application;

// Assignment #: Arizona State University CSE205
//         Name: Aqib Chowdhury
//    StudentID: 1214879900
//      Lecture: Mon/Wed 4:35 - 5:50
//  Description: CreatePane generates a pane where a user can enter
//  a movie information and create a list of available movies.

//Importing necessary javafx classes
import java.util.ArrayList;
import javafx.scene.layout.HBox;
//import all other necessary javafx classes here
//----
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

public class CreatePane extends HBox
{
    private ArrayList<Movie> movieList;
    
    //The relationship between CreatePane and ReviewPane is Aggregation
    //declaring necessary GUI variables
    private ReviewPane reviewPane;
    private Label title;
    private Label length;
    private Label year;
    private TextField field1;
    private TextField field2;
    private TextField field3;
    private Button button1;
    private TextArea area1;
    private Label errorMessage;

    //constructor
    public CreatePane(ArrayList<Movie> list, ReviewPane rePane)
     {
        this.movieList = list;
        this.reviewPane = rePane;

        //Step #1: initialize each instance variable and set up the layout
        
        //Initializing instance variables
        title = new Label("Title");
        length = new Label("Length");
        year = new Label("Year");
        field1 = new TextField();
        field2 = new TextField();
        field3 = new TextField();
        button1 = new Button("Create a Movie");
        area1 = new TextArea("No Movies");
        errorMessage = new Label();
        errorMessage.setTextFill(Color.RED);
        
        		
        //create a GridPane hold those labels & text fields
        //consider using .setPadding() or setHgap(), setVgap()
        //to control the spacing and gap, etc.
        
        //Creating a gridpane with desired spacing
        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.TOP_LEFT);
        pane1.setPadding(new Insets(20,20,20,20));
        pane1.setHgap(5);
        pane1.setVgap(5);
        
        //You might need to create a sub pane to hold the button
        //----
 
        //Set up the layout for the left half of the CreatePane.
        
        //Adding the labels and textfields to the gridpane
        pane1.add(errorMessage,0,0);
        pane1.add(title, 0, 1);
        pane1.add(length, 0, 2);
        pane1.add(year, 0, 3);
        pane1.add(field1, 1, 1);
        pane1.add(field2, 1, 2);
        pane1.add(field3, 1, 3);
        pane1.add(button1, 1, 4);
    
        //the right half of this pane is simply a TextArea object
        //Note: a ScrollPane will be added to it automatically when there are no
        //enough space
        
        
        //Add the left half and right half to the CreatePane
        //Note: CreatePane extends from HBox
        
        //Creating a new HBox pane and adding the grid pane to the left and the text area to the right
        HBox mainPane = new HBox();
        mainPane.setSpacing(0);
        mainPane.getChildren().addAll(pane1, area1);
        
        //Step #3: register source object with event handler
        
        //Registering button with buttonhandler
        ButtonHandler handler = new ButtonHandler();
        button1.setOnAction(handler);
        this.getChildren().add(mainPane);
        
    } //end of constructor

    //Step 2: Create a ButtonHandler class
    //ButtonHandler listens to see if the button "Create a Movie" is pushed or not,
    //When the event occurs, it get a movie's Title, Year, and Length
    //information from the relevant text fields, then create a new movie and add it inside
    //the movieList. Meanwhile it will display the movie's information inside the text area.
    //It also does error checking in case any of the textfields are empty or non-numeric string is typed
    private class ButtonHandler implements EventHandler<ActionEvent>
     {
        //Override the abstract method handle()
        public void handle(ActionEvent event)
         {
            //declare any necessary local variables here
            //---
     
            //when a text field is empty and the button is pushed, an error message appears
            if (field1.getText().trim().isEmpty() || field2.getText().trim().isEmpty() || field3.getText().trim().isEmpty())
                {
            		errorMessage.setText("Please fill all fields");
                }
            else	//for all other cases
            	{
                    //----
                    //at the end, don't forget to update the new arrayList
                    //information on the ListView of the ReviewPane
                    //----
                    
                    //Also somewhere you will need to use try & catch block to catch
                    //the NumberFormatException
            	
            	//if all textfields are properly entered, the inputs will be set to the movie list and will be 
            	//added to the text area
            	try {
            		if (area1.getText().equalsIgnoreCase("No Movies")) {
            			area1.setText("");
            		}
            		//setting new variables equal to the textfield inputs
            		String titleInput = field1.getText();
            		int lengthInput = Integer.parseInt(field2.getText());
            		int yearInput = Integer.parseInt(field3.getText());
            		//creating a new movie object
            		Movie movie = new Movie();
            		//setting textfield inputs for movie object
            		movie.setMovieTitle(titleInput);
            		movie.setLength(lengthInput);
            		movie.setYear(yearInput);
            		//adding movie object to movie list and updating moview list
            		movieList.add(movie);
            		reviewPane.updateMovieList(movie);
            		//display the movie to the text area on the right
            		area1.appendText(movie.toString());
            		//clearing textfields after button is pressed
            		field1.clear();
            		field2.clear();
            		field3.clear();
            		errorMessage.setText("Movie added");
            	}
            	//catch statement to block NumberFormatException
            	catch(NumberFormatException e) {
            		errorMessage.setText("Invalid input");
            	}
                }
         } //end of handle() method
   } //end of ButtonHandler class
}