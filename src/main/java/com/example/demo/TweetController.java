package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TweetController {
    String consumerKey = "NfLVNQmhwoLblvqJpr5uxavIh"; // The application's consumer key
    String consumerSecret = "Rsv6AxupsOYtPe06OcVegPstC4OEVBP7dW2yoE4XwxMH96fhDm"; // The application's consumer secret
    String accessToken = "932683336484573184-wqoI2gO1VKVzrkwzcHZCw30qKWwCaGo"; // The access token granted after OAuth authorization
    String accessTokenSecret = "p1vsfmim535c6K3sNKtDHGXaxAFJJEBPvwHIKiqR0l9Ha"; // The access token secret granted after OAuth authorization
    Twitter twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);

    @RequestMapping("/")
    public String home() {
        return "searchPage";
    }


    @RequestMapping(value = "/postSearch", method = RequestMethod.POST)
    public String postSearch(HttpServletRequest request,
                             RedirectAttributes redirectAttributes) {
        String search = request.getParameter("search");
        if (search.toLowerCase().contains("śmieci")) {

            redirectAttributes.addFlashAttribute("error", "Spróbuj wpisać Spring!");
            return "redirect:/";
        }
        redirectAttributes.addAttribute("search", search);
        return "redirect:result";
    }


    @RequestMapping("/result")
    public String hello(@RequestParam(defaultValue = "MasterSpringMVC4") String search,
                        Model model) {

        SearchResults searchResults = twitter.searchOperations().search(search);
        List<Tweet> tweets = searchResults.getTweets();
        model.addAttribute("tweets", tweets);
        model.addAttribute("search", search);
        return "resultPage";
    }

}