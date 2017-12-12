package com.example.demo.search;

import com.example.demo.profile.ProfileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SearchController {
    private SearchService searchService;

    ProfileForm profileForm;

    @Autowired
    public SearchController(SearchService searchService){
        this.searchService = searchService;
    }

    @RequestMapping("/search/{searchType}")
    public ModelAndView search(@PathVariable String searchType,
                               @MatrixVariable List<String> keywords){
        List<Tweet> tweets = searchService.search(searchType, keywords);
        ModelAndView modelAndView = new ModelAndView("resultPage");
        modelAndView.addObject("tweets", tweets);
        modelAndView.addObject("serach", String.join(",", keywords));

        return modelAndView;
    }
 /*   @RequestMapping(value = "/profile", params = {"searchPreferences"}, method = RequestMethod.POST)
    public String searchPreferences(@Valid ProfileForm profileForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/profilePage";
        }


        return "redirect:/search/mixed;keywords=" + String.join(",",
                profileForm.getTastes());
    }*/
}
