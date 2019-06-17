package pl.proCvGenerator.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.proCvGenerator.exception.PdfException;
import pl.proCvGenerator.exception.TooMuchCharsException;
import pl.proCvGenerator.exception.UserNotFoundException;
import pl.proCvGenerator.facade.WebFacade;
import pl.proCvGenerator.model.CvContentDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@Controller
@CrossOrigin(origins = "*")
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private WebFacade facade;

    @GetMapping(path = "/")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/patterns")
    public String patterns() {
        return "patterns";
    }

    @GetMapping(path = "/pattern")
    public String pattern(@RequestParam(name = "pattern") String pattern, Model model) throws UserNotFoundException {
        model.addAttribute("cvContentDto", facade.init());
        model.addAttribute("pattern", pattern);
        return "content";
    }

    @PostMapping(path = "/generate")
    public void generate(String pattern, CvContentDto cvContentDto, HttpServletResponse response) throws TooMuchCharsException, PdfException {
        LOGGER.info("generating pattern " + pattern + " for user: ");
        facade.generatePdf(pattern, cvContentDto, response);
    }

    @PostMapping(path = "/save")
    public String save(String pattern, CvContentDto cvContentDto) {
        facade.persist(cvContentDto);
        return "redirect:/pattern?pattern=" + pattern;
    }

    @ExceptionHandler({TooMuchCharsException.class, PdfException.class})
    public String handleException(Model model, HttpServletRequest request, Exception e) {
        LOGGER.error("exception has been thrown: " + e);
        model.addAttribute("pattern", request.getParameter("pattern"));
        model.addAttribute("e", e.getMessage());

        return "error";
    }
}
