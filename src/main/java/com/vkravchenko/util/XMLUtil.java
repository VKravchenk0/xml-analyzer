package com.vkravchenko.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class XMLUtil {

    private final static String DEFAULT_CHARSET = "UTF-8";

    public static Element findElementById(Document doc, String targetElementId) {
        return doc.body().getElementById(targetElementId);
    }

    public static Document getDocumentFromFile(String fileName) {
        File file = FileUtil.openFile(fileName);
        if (file == null) {
            return null;
        }
        return parseFile(file);
    }

    private static Document parseFile(File file) {

        try {
            return Jsoup.parse(file, DEFAULT_CHARSET);
        } catch (IOException e) {
            System.err.println(String.format("Cannot parse file %s", file.getAbsolutePath()));
            e.printStackTrace();
            System.exit(0);
        }

        // will not be executed
        return null;
    }

    public static String buildPath(Element element) {
        Elements parentElements = element.parents();
        StringBuilder resultPath = new StringBuilder();
        for (int i = parentElements.size() - 1; i >= 0; i--) {
            Element ancestor = parentElements.get(i);
            resultPath.append(printElementTag(ancestor)).append(" > ");
        }
        resultPath.append(printElementTag(element));

        return resultPath.toString();
    }

    private static String printElementTag(Element element) {
        return String.format("%s[%s] ", element.tagName(), element.elementSiblingIndex());
    }

}
