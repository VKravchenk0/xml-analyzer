package com.vkravchenko;

import com.vkravchenko.pojo.MatchResult;
import com.vkravchenko.util.XMLUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class App {

    public static void main(String[] args) {

        if (args.length < 2) {
            printErrorAndExit("Error: wrong number of arguments");
        }

        String originalFileName = args[0];
        String diffFileName = args[1];
        String targetElementId = args.length > 2 ? args[2] : "make-everything-ok-button";

        Document originalDocument = XMLUtil.getDocumentFromFile(originalFileName);
        if (originalDocument == null) {
            printErrorAndExit(String.format("Cannot find file %s", originalFileName));
        }

        Element originalElement = XMLUtil.findElementById(originalDocument, targetElementId);
        if (originalElement == null) {
            System.out.println(String.format("Cannot find element with id %s", targetElementId));
            System.exit(0);
        }

        Document modifiedDocument = XMLUtil.getDocumentFromFile(diffFileName);
        if (modifiedDocument == null) {
            printErrorAndExit(String.format("Cannot find file %s", diffFileName));
        }

        MatchResult searchResult = XMLAnalyzer.findSimilarElement(modifiedDocument, originalElement);

        printResult(searchResult);

    }

    private static void printErrorAndExit(String errorMessage) {
        System.err.println(errorMessage);
        System.err.println("Usage:");
        System.err.println("java -jar <original_file_name> <changed_file_name>");
        System.err.println("java -jar <original_file_name> <changed_file_name> <target_element_id>");
        System.exit(0);
    }

    private static void printResult(MatchResult searchResult) {
        System.out.println("Element found: ");
        System.out.println(XMLUtil.buildPath(searchResult.getElement()));
        System.out.println();
        System.out.println("This element was chosen because of the following matches:");
        System.out.println(String.join("\n", searchResult.getDecisions()));
    }

}
