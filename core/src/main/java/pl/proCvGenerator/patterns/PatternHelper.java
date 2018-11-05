package pl.proCvGenerator.patterns;

import pl.proCvGenerator.dto.Education;
import pl.proCvGenerator.dto.Employment;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PatternHelper {

    public List<Education> sortEducationList(List<Education> educationList){
        return educationList
                .stream()
                .sorted(Comparator.comparing(Education::getEndDate).reversed())
                .collect(Collectors.toList());
    }

    public List<Employment> sortEmploymentList(List<Employment> employmentList){
        return employmentList
                .stream()
                .sorted(Comparator.comparing(Employment::getEndDate).reversed())
                .collect(Collectors.toList());
    }
}
