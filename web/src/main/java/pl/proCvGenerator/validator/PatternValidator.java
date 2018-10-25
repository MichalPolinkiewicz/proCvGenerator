package pl.proCvGenerator.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.patterns.Pattern;

import java.util.Map;

public class PatternValidator {

    @Autowired
    private Map<String, Pattern> patterns;

    public Pattern choosePattern(String patternId) {
        Pattern pattern = null;

        for (Map.Entry<String, Pattern> e : patterns.entrySet()) {
            if (e.getKey().equals(patternId)) {
                pattern = e.getValue();
            }
        }
        return pattern;
    }
}
