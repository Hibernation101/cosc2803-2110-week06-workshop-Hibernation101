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
public class Index implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add the body
        html = html + "<body>";

        // Add HTML for the logo.png image
        html = html + "<img src='logo.png' height='200px'/>";

        // Add HTML for the list of pages
        html = html + "<h1>Homepage</h1>" +
               "<p>Links to queries on different web pages</p>" +
               "<ul>";

        // Link for each page
        html = html + "<li> <a href='movies.html'>List all Movies</a> </li>";
        html = html + "<li> <a href='moviestype.html'>Get Movies by Type</a> </li>";
        html = html + "<li> <a href='directormovies.html'>Get Movies by Director</a> </li>";

        // Finish the List HTML
        html = html + "</ul>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
