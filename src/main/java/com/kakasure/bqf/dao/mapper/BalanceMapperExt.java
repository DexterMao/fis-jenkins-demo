
package com.kakasure.bqf.dao.mapper;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.model.Balance;

@Repository
public interface BalanceMapperExt extends BalanceMapper {

	public void editBalance(Balance balance);
}