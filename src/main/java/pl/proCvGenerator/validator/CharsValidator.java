package pl.proCvGenerator.validator;

import org.apache.commons.lang3.StringUtils;
import pl.proCvGenerator.exception.TooMuchCharsException;

public class CharsValidator {

    public static final String TOO_MUCH_CHARS = "Too much chars. Maximum is: ";

    public static int[] calculateWhiteSpaces(String text) {
        int spc = StringUtils.countMatches(text, " ");
        int dot = StringUtils.countMatches(text, '.');
        int comma = StringUtils.countMatches(text, ',');
        int count = spc + dot + comma;
        int[] spaces = new int[count + 1];
        int index = 1;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ' || text.charAt(i) == '.' || text.charAt(i) == ',') {
                spaces[index] = i;
                index++;
            }
        }
        return spaces;
    }

    public static int calculateLinesForSentence(String text, int maxChars) throws TooMuchCharsException {
        int counter = 0;
        int[] whiteSpaces = calculateWhiteSpaces(text);
        String actualSentence = "";

        for (int i = 0; i < whiteSpaces.length; i++) {
            String copy = text;
            if (i != whiteSpaces.length - 1) {
                actualSentence += copy.substring(whiteSpaces[i], whiteSpaces[i + 1]);
            } else {
                actualSentence += copy.substring(whiteSpaces[i]);
            }

            if (actualSentence.length() > maxChars) {
                counter++;
                if (i == whiteSpaces.length - 1) {
                    actualSentence = copy.substring(whiteSpaces[i] );
                    if (actualSentence.length() > 0) {
                        counter++;
                    }
                    if (actualSentence.length() > maxChars){
                        throw new TooMuchCharsException(TOO_MUCH_CHARS + maxChars);
                    }
                } else {
                    actualSentence = copy.substring(whiteSpaces[i] +1, whiteSpaces[i + 1]);
                    if (actualSentence.length() > maxChars){
                        throw new TooMuchCharsException(TOO_MUCH_CHARS + maxChars);
                    }
                }
            } else if (i == whiteSpaces.length - 1) {
                actualSentence = copy.substring(whiteSpaces[i]);
                if (actualSentence.length() > 0) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
