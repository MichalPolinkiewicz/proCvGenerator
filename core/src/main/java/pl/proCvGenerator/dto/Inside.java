package pl.proCvGenerator.dto;

public class Inside {

    private String text;
    InsideInside insideInside;

    public Inside() {
    }

    public Inside(String text, InsideInside insideInside) {
        this.text = text;
        this.insideInside = insideInside;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InsideInside getInsideInside() {
        return insideInside;
    }

    public void setInsideInside(InsideInside insideInside) {
        this.insideInside = insideInside;
    }
}
