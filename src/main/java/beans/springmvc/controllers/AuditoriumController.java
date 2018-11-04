package beans.springmvc.controllers;

import beans.models.Auditorium;
import beans.services.AuditoriumService;
import beans.springmvc.views.PdfView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class AuditoriumController {

    Logger log = LoggerFactory.getLogger(AuditoriumController.class);


    @Autowired
    private AuditoriumService auditoriumService;


    @RequestMapping(value = "/auditorias", method = RequestMethod.GET)
    public String auditioriumMain(@ModelAttribute("model") ModelMap model) {
        log.info("Show all auditorias");
        model.addAttribute("auditoriumList", auditoriumService.getAuditoriums());
        return "auditorium_list";
    }

    @RequestMapping(value = "/auditorium/{name}", method = RequestMethod.GET)
    public String getAuditoriumByName(@ModelAttribute("model") ModelMap model, @PathVariable final String name) {
        log.info("Show auditorium '{}'", name);
        Auditorium auditorium = auditoriumService.getByName(name);
        if(auditorium != null) {
            model.addAttribute("auditoriumList", Arrays.asList(auditorium));
        }
        return "auditorium_list";
    }

    // TODO: pdf test!
    @RequestMapping(value = "/pdf_test", method = RequestMethod.GET, headers = "Accept=application/pdf")
    @ResponseBody
    public ModelAndView getPdfList(@ModelAttribute("model") ModelMap model) {
        model.addAttribute("wordList", Arrays.asList("fsfsgdfs","sfgsgs","sgsgsg","sgsgsgf"));
        return new ModelAndView(new PdfView(), model);
    }
}