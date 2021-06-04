package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

public class DirectorMovies implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/directormovies.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Movies</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add the body
        html = html + "<body>";

        // Add HTML for the movies list
        html = html + "<h1>Movies by Type</h1>";

        /* Add HTML for the web form
         * We are giving two ways here
         *  - one for a text box
         *  - one for a drop down
         * 
         * Whitespace is used to help us understand the HTML!
         * 
         * IMPORTANT! the action speicifes the URL for POST!
         */
        html = html + "<form action='/directormovies.html' method='post'>";
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='directormovies_drop'>Select the type Movie Type (Dropdown):</label>";
        html = html + "      <select id='directormovies_drop' name='directormovies_drop'>";
        html = html + "         <option>Allen, Woody</option>";
        html = html + "         <option>Hitchcock, Alfred</option>";
        html = html + "         <option>De Mille, Cecil B</option>";
        html = html + "         <option>Kramer, Stanley</option>";
        html = html + "         <option>Kubrick, Stanley</option>";
        html = html + "         <option>Preminger, Otto</option>";
        html = html + "         <option>Ford, John</option>";
        html = html + "         <option>Fellini, Federico</option>";
        html = html + "      </select>";
        html = html + "   </div>";
        html = html + "   <div class='form-group'>";
        html = html + "      <label for='directormovies_textbox'>Select the type Movie Type (Textbox)</label>";
        html = html + "      <input class='form-control' id='directormovies_textbox' name='directormovies_textbox'>";
        html = html + "   </div>";
        html = html + "   <button type='submit' class='btn btn-primary'>Submit</button>";
        html = html + "</form>";

        /* Get the Form Data
         *  from the drop down list
         * Need to be Careful!!
         *  If the form is not filled in, then the form will return null!
        */
        String directormovies_drop = context.formParam("directormovies_drop");
        if (directormovies_drop == null) {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for dropbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputMovies(directormovies_drop);
        }

        String directormovies_textbox = context.formParam("directormovies_textbox");
        if (directormovies_textbox == null || directormovies_textbox == "") {
            // If NULL, nothing to show, therefore we make some "no results" HTML
            html = html + "<h2><i>No Results to show for textbox</i></h2>";
        } else {
            // If NOT NULL, then lookup the movie by type!
            html = html + outputMovies(directormovies_textbox);
        }

        // Add HTML for link back to the homepage
        html = html + "<p>Return to Homepage: ";
        html = html + "<a href='/'>Link to Homepage</a>";
        html = html + "</p>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";

        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

    public String outputMovies(String director) {
        String html = "";
        html = html + "<h2>" + director + " Movies</h2>";

        // Look up movies from JDBC
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<String> movies = jdbc.getMoviesByDirectors(director);
        
        // Add HTML for the movies list
        html = html + "<ul>";
        for (String movie : movies) {
            html = html + "<li>" + movie + "</li>";
        }
        html = html + "</ul>";

        return html;
    }

}
