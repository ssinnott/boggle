package org.sean.boggle.controller;

import org.sean.boggle.domain.Board;
import org.sean.boggle.domain.Solution;
import org.sean.boggle.services.BoggleSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by seansinnott on 2/25/17.
 */
@Controller
public class BaseController {

    private BoggleSolverService boggleSolverService;

    @Autowired
    public BaseController(BoggleSolverService boggleSolverService){
        this.boggleSolverService = boggleSolverService;
    }

    @RequestMapping("/")
    public String home() {
        return "base-page";
    }

    @RequestMapping("/rest/v1/solve")
    public @ResponseBody
    Solution solve(@RequestBody Board board){
        return boggleSolverService.findSolutions(board);
    }

}
