package pl.proCvGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.proCvGenerator.facade.WebFacade;
import pl.proCvGenerator.model.CvContentDto;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MainController {

    @Autowired
    private WebFacade facade;

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/pattern")
    public String pattern(@RequestParam(name = "pattern") String pattern, Model model) {
        model.addAttribute("pattern", pattern);
        return "content";
    }

    @PostMapping(path = "/generate")
    public void generate(String pattern, CvContentDto cvContentDto, HttpServletResponse response) {
        facade.generatePdf("simplePattern", cvContentDto, response);
    }

    @ModelAttribute(name = "cvContentDto")
    public CvContentDto init() {
        return facade.init();
    }
}
