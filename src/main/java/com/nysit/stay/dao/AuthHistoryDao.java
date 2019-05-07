package com.nysit.stay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.nysit.stay.domain.AppHistory;

@Repository
@Mapper
public interface AuthHistoryDao {

	void addHistory(AppHistory ah);

	List<AppHistory> findHistoryById(Integer id);

	int getSplsCount();

	List<AppHistory> findSplsByPage(Map<String, Object> map);

}
