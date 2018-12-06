package pl.proCvGenerator.validator;

import org.apache.commons.lang3.StringUtils;
import pl.proCvGenerator.exception.TooMuchCharsException;

public class TextValidator {

    private static final String TOO_MUCH_CHARS = "Too much chars. Maximum is: ";

    public static int calculateLinesForSentence(String text, int max) throws TooMuchCharsException {
        int counter = 1;
        int[] whiteSpaces = calculateWhiteSpaces(text);
        String actualSentence = "";

        for (int i = 0; i < whiteSpaces.length; i++) {
            String copy = text;
            if (i == 0) {
                if (whiteSpaces.length == 1) {
                    actualSentence = copy;
                } else {
                    actualSentence = copy.substring(i, whiteSpaces[i + 1]);
                }
            } else if (i == whiteSpaces.length - 1) {
                actualSentence += copy.substring(whiteSpaces[i]);
            } else {
                actualSentence += copy.substring(whiteSpaces[i], whiteSpaces[i + 1]);
            }

            if (whiteSpaces.length == 1 && actualSentence.length() > max) {
                throw new TooMuchCharsException(TOO_MUCH_CHARS + max);
            }

            if (actualSentence.length() > max) {
                if (i == whiteSpaces.length - 1) {
                    actualSentence = copy.substring(whiteSpaces[i] + 1);
                    if (actualSentence.length() > max) {
                        throw new TooMuchCharsException(TOO_MUCH_CHARS + max);
                    }
                } else {
                    actualSentence = copy.substring(whiteSpaces[i] + 1, whiteSpaces[i + 1]);
                    if (actualSentence.length() > max) {
                        throw new TooMuchCharsException(TOO_MUCH_CHARS + max);
                    }
                }
                counter++;
            }
        }
        return counter;
    }

    public static int[] calculateWhiteSpaces(String text) {
        int spc = StringUtils.countMatches(text, " ");
        int[] spaces = new int[spc + 1];
        int index = 1;

        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                spaces[index] = i;
                index++;
            }
        }
        return spaces;
    }
}
