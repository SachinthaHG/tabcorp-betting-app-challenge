package au.com.tabcorp.controllers;

import au.com.tabcorp.core.models.Bet;
import au.com.tabcorp.core.services.BetStoreService;
import au.com.tabcorp.utils.AppSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AppSecurity appSecurity;

    /**
     * accepts a HTTP POST request with a bet list sent from client side
     *
     * @param betList list of bets in JSON format
     * @return list of strings in JSON format
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<String>> saveBets(@RequestBody List<Bet> betList, @RequestParam(value = "access_token") String accessToken) {
        List<String> response = new ArrayList<>();

        if (appSecurity.validateAccessToken(accessToken)) {
            if (betStoreService.saveBets(betList)) {
                response.add("Bets saved");
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                response.add("Best list is null, empty or has duplicate PropNumbers");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
        } else {
            response.add("Unauthorized");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
