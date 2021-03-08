package prog12;

import java.util.List;

public interface SearchEngine {
    /* Gather info from all web pages reachable from URLs in startingURLs. */
    void gather (Browser browser, List<String> startingURLs);

    /* Search for up to numResults pages containing all keyWords and
     * return them in an array in order of decreasing importance
     * (number of references). */
    String[] search (List<String> keyWords, int numResults);
}

