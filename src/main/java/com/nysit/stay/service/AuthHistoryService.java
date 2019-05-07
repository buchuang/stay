package com.nysit.stay.service;

import java.util.List;

import com.nysit.stay.domain.AppHistory;

public interface AuthHistoryService {

	void addHistory(AppHistory ah);

	List<AppHistory> findHistoryById(Integer id);

	int getSplsCount(Integer id);

	List<AppHistory> findSplsByPage(Integer page, Integer rows, Integer id);

}