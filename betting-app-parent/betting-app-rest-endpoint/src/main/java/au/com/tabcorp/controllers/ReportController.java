package au.com.tabcorp.controllers;

import au.com.tabcorp.core.models.*;
import au.com.tabcorp.core.services.BetReportService;
import au.com.tabcorp.core.services.BetStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("reports")
public class ReportController {
    @Autowired
    private BetStoreService betStoreService;

    @Autowired
    private BetReportService betReportService;

    @RequestMapping(value = "/investment-per-bet-type", method = RequestMethod.GET, produces = "application/json")
   // @ResponseBody
    public ResponseEntity<Report<List<InvestmentPerBetType>>> getInvestmentPerBetTypeReport() {
        return new ResponseEntity<Report<List<InvestmentPerBetType>>>(
                betReportService.generateInvestmentPerBetTypeReport(), HttpStatus.OK);
    }

    @RequestMapping(value = "/investment-per-customer", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Report<List<InvestmentPerCustomer>>> getInvestmentPerCustomerReport() {
        return new ResponseEntity<Report<List<InvestmentPerCustomer>>>(
                betReportService.generateInvestmentPerCustomerReport(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bets-sold-per-bet-type", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Report<List<BetsSoldPerBetType>>> getBetsSoldPerBetType() {

        return new ResponseEntity<Report<List<BetsSoldPerBetType>>>(
                betReportService.generateBetsSoldPerBetTypeReport(), HttpStatus.OK);
    }

    @RequestMapping(value = "/bets-sold-per-hour", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Report<List<BetsSoldPerHour>>> getBetsSoldPerHour() {
        return new ResponseEntity<Report<List<BetsSoldPerHour>>>(
                betReportService.generateBetsSoldPerHourReport(), HttpStatus.OK);
    }
}
