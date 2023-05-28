package com.onetoy.onetoy.service;

import com.onetoy.onetoy.domain.Account;
import com.onetoy.onetoy.repository.AccountRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final AccountRepository accountRepository;

    private Account useAccount;

    @Autowired
    public MainService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account checkAccount(Long uid) {
        this.useAccount = this.accountRepository.findOneAccountByUid(uid);
        return useAccount;
    }

}
