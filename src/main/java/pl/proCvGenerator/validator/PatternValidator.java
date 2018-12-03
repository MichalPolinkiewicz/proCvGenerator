package pl.proCvGenerator.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.proCvGenerator.patterns.Pattern;

import java.util.Map;

public class PatternValidator {

    @Autowired
    private Map<String, Pattern> patterns;

    public Pattern choosePattern(String patternId) { return patterns.get(patternId); }
}
