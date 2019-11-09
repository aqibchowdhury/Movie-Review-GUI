package application;

// Assignment #: Arizona State University CSE205
//         Name: Aqib Chowdhury
//    StudentID: 1214879900
//      Lecture: Mon/Wed 4:35 - 5:50
//  Description: ReviewPane displays a list of available movies
//  from which a user can select to review.

//Importing necessary javafx classes
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;	//**Need to import to handle event
import javafx.event.EventHandler;	//**Need to import to handle event
import java.util.ArrayList;
//import all other necessary javafx classes here
//----
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.TilePane;
import javafx.scene.control.Button;

public class ReviewPane extends VBox
{
   private ArrayList<Movie> movieList;

   //A ListView to display movies created
   private ListView<Movie> movieListView;

   //declaring all necessary GUI variables here
   private Label label1;
   private RadioButton poorButton;
   private RadioButton fairButton;
   private RadioButton averageButton;
   private RadioButton goodButton;
   private RadioButton excellentButton;
   private ToggleGroup group1;
   private TilePane buttonPane;
   private VBox mainPane;
   private Button button1;
   private Movie selected;
   private ObservableList<Movie> outputMovies;
   private ListView listViewMovies;
   
   //constructor
   public ReviewPane(ArrayList<Movie> list)
   {
       //initialize instance variables
       this.movieList = list;

       //set up the layout
       //Initializing instance variables for label and RadioButtons
       label1 = new Label("Choose a movie to give a review, and select a rating");
       
       poorButton = new RadioButton("1 Poor");
       fairButton = new RadioButton("2 Fair");
       averageButton = new RadioButton("3 Average");
       goodButton = new RadioButton("4 Good");
       excellentButton = new RadioButton("5 Excellent");
       
       //Adding user inputted movies to movieList to be selected for movie reviews
       outputMovies = FXCollections.observableArrayList(this.movieList);
       listViewMovies = new ListView(outputMovies);
       listViewMovies.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
      
      //Grouping all RadioButtons together in a ToggleGroup 
       group1 = new ToggleGroup();
       poorButton.setToggleGroup(group1);
       fairButton.setToggleGroup(group1);
       averageButton.setToggleGroup(group1);
       goodButton.setToggleGroup(group1);
       excellentButton.setToggleGroup(group1);
       
       //adding RadioButtons to a tile pane
       buttonPane = new TilePane();
       buttonPane.setPrefColumns(5);
       buttonPane.getChildren().addAll(poorButton, fairButton, averageButton, goodButton, excellentButton);
       
       //creating submit review button
       button1 = new Button("Submit Review");
       
       //ReviewPane is a VBox - add the components here
       
       //Creating a new VBox pane to add the label, movieList, RadioButtons, and submit review button
       mainPane = new VBox();
       mainPane.getChildren().addAll(label1, listViewMovies, buttonPane, button1);
       
       
       //Step #3: Register the button with its handler class
       
       //Registering the sumbit review button with the RatingHandler
       RatingHandler handler = new RatingHandler();
       button1.setOnAction(handler);
       
       this.getChildren().add(mainPane);
       
       
   } //end of constructor

 //This method refresh the ListView whenever there's new movie added in CreatePane
 //you will need to update the underline ObservableList object in order for ListView
 //object to show the updated movie list
 public void updateMovieList(Movie newMovie)
 {
     outputMovies.setAll(movieList);
 }

 //Step 2: Create a RatingHandler class
 private class RatingHandler implements EventHandler<ActionEvent>
    {
        //Override the abstact method handle()
        public void handle(ActionEvent event)
        {
            //When "Submit Review" button is pressed and a movie is selected from
            //the list view's average rating is updated by adding a additional
            //rating specified by a selected radio button
        	
        	//Adding the selected movie to a new variable
            selected = (Movie)listViewMovies.getSelectionModel().getSelectedItem();
            //If the poor radio button is selected, the selected movie will earn a review of 1
        	if (poorButton.isSelected())
            {
                selected.addRating(1.0);
                
            }
        	//If the fair radio button is selected, the selected movie will earn a review of 2
        	else if (fairButton.isSelected())
            {
                selected.addRating(2.0);
            }
        	//If the average radio button is selected, the selected movie will earn a review of 3
        	else if (averageButton.isSelected())
            {
                selected.addRating(3.0);
            }
        	//If the good radio button is selected, the selected movie will earn a review of 4
        	else if (goodButton.isSelected())
            {
                selected.addRating(4.0);
            }
        	//If the excellent radio button is selected, the selected movie will earn a review of 5
        	else if (excellentButton.isSelected())
            {
                selected.addRating(5.0);
            }
        	outputMovies.setAll(movieList);
        	
        }
    } //end of RatingHandler
} //end of ReviewPane class