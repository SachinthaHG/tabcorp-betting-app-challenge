package au.com.tabcorp.controllers;

import au.com.tabcorp.core.models.*;
import au.com.tabcorp.core.services.BetReportService;
import au.com.tabcorp.utils.AppSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * This is the endpoint to handle HTTP requests related to report generation
 */
@Controller
@RequestMapping("reports")
public class ReportController {
    @Autowired
    private BetReportService betReportService;

    @Autowired
    private AppSecurity appSecurity;

    /**
     * accepts a HTTP GET request to generate investment per bet type report
     *
     * @return investment per bet type report in JSON format
     */
    @RequestMapping(value = "/investment-per-bet-type", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Report<List<InvestmentPerBetType>>> getInvestmentPerBetTypeReport(@RequestParam(value = "access_token") String accessToken) {
        if (appSecurity.validateAccessToken(accessToken)) {
            return new ResponseEntity<>(betReportService.generateInvestmentPerBetTypeReport(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * accepts a HTTP GET request to generate investment per customer report
     *
     * @return investment per customer report in JSON format
     */
    @RequestMapping(value = "/investment-per-customer", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Report<List<InvestmentPerCustomer>>> getInvestmentPerCustomerReport(@RequestParam(value = "access_token") String accessToken) {
        if (appSecurity.validateAccessToken(accessToken)) {
            return new ResponseEntity<>(betReportService.generateInvestmentPerCustomerReport(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * accepts a HTTP GET request to generate bets sold per bet type report
     *
     * @return bets sold per bet type report in JSON format
     */
    @RequestMapping(value = "/bets-sold-per-bet-type", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Report<List<BetsSoldPerBetType>>> getBetsSoldPerBetType(@RequestParam(value = "access_token") String accessToken) {
        if (appSecurity.validateAccessToken(accessToken)) {
            return new ResponseEntity<>(betReportService.generateBetsSoldPerBetTypeReport(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * accepts a HTTP GET request to generate bets sold per hour report
     *
     * @return bets sold per hour report in JSON format
     */
    @RequestMapping(value = "/bets-sold-per-hour", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Report<List<BetsSoldPerHour>>> getBetsSoldPerHour(@RequestParam(value = "access_token") String accessToken) {
        if (appSecurity.validateAccessToken(accessToken)) {
            return new ResponseEntity<>(betReportService.generateBetsSoldPerHourReport(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
