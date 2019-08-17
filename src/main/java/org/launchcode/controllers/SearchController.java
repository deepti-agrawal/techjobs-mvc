package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    @RequestMapping(value="/results")
    public String processSearchRequest(Model model,@RequestParam String searchType, @RequestParam String searchTerm){
        ArrayList<HashMap<String, String>> jobs =  new ArrayList();
        if(searchType.equalsIgnoreCase("all") && searchTerm != "")
            jobs = JobData.findByValue(searchTerm);
        else if(searchType.equalsIgnoreCase("all"))
            jobs = JobData.findAll();
        else
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        model.addAttribute("size",(jobs.size()+" Result(s)"));
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs",jobs);
        model.addAttribute("searchTerm",searchTerm);
        return "search";
    }
}
