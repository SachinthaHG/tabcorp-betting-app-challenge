package au.com.tabcorp.controllers;

import au.com.tabcorp.core.models.Bet;
import au.com.tabcorp.core.services.BetStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the endpoint to handle HTTP requests related to bets
 */
@Controller
@RequestMapping("bets")
public class BetController {
    @Autowired
    private BetStoreService betStoreService;

    /**
     * accepts a HTTP POST request with a bet list sent from client side
     *
     * @param betList list of bets in JSON format
     * @return list of strings in JSON format
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<String>> saveBets(@RequestBody List<Bet> betList) {
        List<String> response = new ArrayList<>();
        if (betStoreService.saveBets(betList)) {
            response.add("Bets saved");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.add("Best list is null, empty or has duplicate PropNumbers");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}
