package org.sean.boggle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seansinnott on 2/25/17.
 */
@Controller
public class BaseController {

    @RequestMapping("/")
    String home() {
        return "base-page";
    }

}
