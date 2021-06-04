package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class MoviesList implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/movies.html";

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

        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();

        // Next we will ask this *class* for the movies
        ArrayList<String> movies = jdbc.getMovies();

        // Add HTML for the movies list
        html = html + "<h1>Movies</h1>" + "<ul>";

        // Finally we can print out all of the movies
        for (String movie : movies) {
            html = html + "<li>" + movie + "</li>";
        }

        // Finish the List HTML
        html = html + "</ul>";

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

}
