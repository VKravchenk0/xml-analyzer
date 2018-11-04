package com.vkravchenko.pojo;

import org.jsoup.nodes.Element;

import java.util.List;

public class MatchResult {

    private Element element;
    private int score;
    private List<String> decisions;

    public MatchResult(Element element, int score, List<String> decisions) {
        this.element = element;
        this.score = score;
        this.decisions = decisions;
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getDecisions() {
        return decisions;
    }

    public void setDecisions(List<String> decisions) {
        this.decisions = decisions;
    }

}
