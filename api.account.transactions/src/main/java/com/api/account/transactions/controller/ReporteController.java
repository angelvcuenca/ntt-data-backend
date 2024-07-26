package com.api.account.transactions.controller;

import com.api.account.transactions.model.dto.AccountStatusDto;
import com.api.account.transactions.model.dto.AccountStatusReportDto;
import com.api.account.transactions.service.impl.IAccountStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController

public class ReporteController {

    @Autowired
    private IAccountStatusServiceImpl accountStatusService;

    @GetMapping("/reportes")
    public AccountStatusDto generateReportAccountStatus(
            @RequestParam("clientId") Long clientId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return accountStatusService.generateReportAccountStatus(clientId, startDate, endDate);
    }




}
