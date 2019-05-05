package com.trimble.ttm.toris.account.controller;

import com.trimble.ttm.toris.account.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.bazaarvoice.jolt.Chainr;
import com.bazaarvoice.jolt.JsonUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@RestController
@RefreshScope
public class AccountController {

    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Value("${message: Default Hello}")
    private String msg;

    @Value("${account.tcx.spec: Default Spec}")
    private String accSpec;

    @RequestMapping(value = "/testAccount", method = RequestMethod.GET)
    public String testAccount() {
        String inputText = "{ \"_id\": \"5caa8eb7787cc624fc081914\", \"account_name\": \"LIGHTNING TRANS INC.\", \"account_address\": { \"address1\": \"5400 Penn Ave\", \"address2\": \"\", \"city\": \"\", \"state\": \"\", \"zip\": \"\", \"country\": \"\" }, \"account_type\": [ \"carrier\", \"broker\" ], \"account_status\": \"active\", \"carrier\": { \"scac\": \"\", \"dot_number\": \"\", \"mc_number\": \"\" }, \"shipper\": { \"duns\": \"\" }, \"broker\": { \"unique_number\": \"\" }, \"visibility_provider\": { \"unique_id\": \"\" }, \"maintenance_provider\": { \"unique_id\": \"\" }, \"external_reference\": { \"netsuite_id\": \"\", \"gemalto_id\": \"\", \"peoplenet_id\": \"\", \"tor_id\": \"\" }, \"tags\": [ \"tag1\", \"tag2\", \"\" ], \"parent_id\": \"\", \"siblings\": [ \"\" ], \"objCat\": \"customers\" }";

        String transformText = null;
        Object specObj = accSpec;
        Chainr chainr = Chainr.fromSpec(JsonUtils.jsonToObject(accSpec));

        Object responseJSON = JsonUtils.jsonToObject(inputText);
        Object transformedOutput = chainr.transform(responseJSON);
        transformText = JsonUtils.toJsonString(transformedOutput);
        System.out.println("transformText : "+transformText);
        return "<h2>Remote message</h2> "+msg+ "<h2> Remote config spec: </h2>" +accSpec+ "<h2> Transformed result: </h2>"+transformText;


    }

    @RequestMapping(value = "/accounts", method = RequestMethod.POST, produces = "application/json")
    public Account createAccount(@RequestBody Account account) {
        logger.info(String.format("Account.add(%s)", account));
        //return repository.save(account);
        return account;
    }

    /*@RequestMapping(value = "/accounts/{number}", method = RequestMethod.GET)
    public Account findByNumber(@PathVariable("number") String number) {
        logger.info(String.format("Account.findByNumber(%s)", number));
        //return repository.findByNumber(number);
        Account account = new Account();
        return account;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.PUT)
    public Account update(@RequestBody Account account) {
        logger.info(String.format("Account.update(%s)", account));
        //return repository.save(account);
        return account;
    }*/

}
