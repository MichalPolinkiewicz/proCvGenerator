package pl.proCvGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.proCvGenerator.dto.CvContent;
import pl.proCvGenerator.dto.PersonalInfo;
import pl.proCvGenerator.facade.CvCreatorFacade;
import pl.proCvGenerator.validator.PatternValidator;

import java.util.ArrayList;

@Controller
public class MainController {

    @Autowired
    private CvCreatorFacade creatorFacade;

    @Autowired
    private PatternValidator patternValidator;

    @GetMapping(path = "/")
    public String index() {

        return "index";
    }

    @GetMapping(path = "/pattern")
    public String pattern(@RequestParam(name = "pattern") String pattern, Model model) {
        System.out.println("Pattern from index.html= " + pattern);

        model.addAttribute("cvContent", new CvContent(new PersonalInfo(), new ArrayList<>(), new ArrayList<>()));
        model.addAttribute("pattern", pattern);

        return "content";
    }

    @PostMapping(path = "/generate")
    public String generate(String pattern, CvContent cvContent) {
        System.out.println(cvContent.getPersonalInfo().getName());
        System.out.println("Generate: " + pattern);

        return "ok";
    }

//    @PostMapping(path = "/example")
//    public String example(Example example, String hidden){
//        System.out.println(example.getName() + " " + example.getSurname() + " " + hidden);
//        System.out.println(example.getInside().getText());
//        System.out.println(example.getInside().getInsideInside().getNumber());
//
//        return "ok";
//    }
}
