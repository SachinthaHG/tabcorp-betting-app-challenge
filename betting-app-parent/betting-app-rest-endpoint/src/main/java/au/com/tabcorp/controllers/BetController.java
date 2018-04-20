package au.com.tabcorp.controllers;

import au.com.tabcorp.core.models.Bet;
import au.com.tabcorp.core.services.BetStoreService;
import au.com.tabcorp.models.BetAppResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("bets")
public class BetController {
    @Autowired
    private BetStoreService betStoreService;
    private static final Logger logger = Logger.getLogger(BetController.class);

    @RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<BetAppResponse<String>> saveBets(@RequestBody List<Bet> betList) {
        if (betStoreService.saveBets(betList)) {
            logger.info("Bests saved successfully");

            BetAppResponse<String> response = new BetAppResponse<>();
            List<String> messages = new ArrayList<>();
            messages.add("Bets saved");

            response.setResults(messages);

            return new ResponseEntity<BetAppResponse<String>>(response, HttpStatus.CREATED);
        } else {
            logger.info("Best list is either null of empty");

            BetAppResponse<String> response = new BetAppResponse<>();
            List<String> messages = new ArrayList<>();
            messages.add("Bets list is either null or empty");

            response.setResults(messages);

            return new ResponseEntity<BetAppResponse<String>>(response, HttpStatus.CONFLICT);
        }
    }
}
