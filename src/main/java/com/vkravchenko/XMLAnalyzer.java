package com.vkravchenko;

import com.vkravchenko.pojo.MatchResult;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class XMLAnalyzer {

    public static MatchResult findSimilarElement(Document document, Element originalElement) {

        Elements possibleElements = document.body().getElementsByTag(originalElement.tagName());
        List<MatchResult> matchResults = new ArrayList<>(possibleElements.size());

        for (Element element : possibleElements) {
            int score = 0;
            List<String> decisions = new ArrayList<>();
            score = score + checkElementTextMatch(originalElement, element, decisions);
            score = score + checkElementsAttributeMatch(originalElement, element, decisions);

            matchResults.add(new MatchResult(element, score, decisions));
        }

        return findFindMatchWithHighestScore(matchResults);

    }

    private static int checkElementTextMatch(Element originalElement, Element element, List<String> decisions) {
        if (originalElement.hasText() && element.hasText()
            && originalElement.text().equals(element.text())) {
            decisions.add(String.format("Element's text match. Text value is '%s'", originalElement.text()));
            return 1;
        }
        return 0;
    }

    private static int checkElementsAttributeMatch(Element originalElement, Element element, List<String> decisions) {
        Attributes originalAttributes = originalElement.attributes();
        int score = 0;
        for (Attribute originalAttribute : originalAttributes) {
            String attributeKey = originalAttribute.getKey();
            if (element.hasAttr(attributeKey) &&
                    originalAttribute.getValue().equals(element.attr(attributeKey))) {
                decisions.add(String.format("Attribute '%s' match. Attribute value is '%s'", attributeKey, originalAttribute.getValue()));
                score++;
            }
        }
        return score;
    }

    private static MatchResult findFindMatchWithHighestScore(List<MatchResult> matchResults) {
        return Collections.max(matchResults, Comparator.comparing(MatchResult::getScore));
    }
}
