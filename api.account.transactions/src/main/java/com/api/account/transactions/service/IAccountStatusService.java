package com.api.account.transactions.service;


import com.api.account.transactions.model.dto.AccountStatusDto;
import com.api.account.transactions.model.dto.AccountStatusReportDto;
import com.api.account.transactions.model.entity.Transaction;
import java.time.LocalDate;

public interface IAccountStatusService {
    AccountStatusDto generateReportAccountStatus(Long clientId, LocalDate startDate, LocalDate endDate);



}
