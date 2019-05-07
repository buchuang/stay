package com.nysit.stay.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import org.springframework.stereotype.Repository;

import com.nysit.stay.domain.StayTicket;
import com.nysit.stay.domain.User;
import com.nysit.stay.utils.Datas;

@Repository
@Mapper
public interface StayTicketDao {

	int getCount(Integer userid);

	List<User> findStayByPage(Map<String, Object> map);

	void insertStayTicket(StayTicket st);

	void deleteStayTicket(Datas ds);

	void editStayTicket(StayTicket st);

	void editStayTicketStatus(StayTicket st);

	StayTicket findBtByPiid(String piid);

	void updateAuthStatus(StayTicket st);

	List<User> findYslxdByPage(Map<String, Object> map);

	int getYslxdCount(Integer userid);

	int getAllSpCount();

	List<StayTicket> findAllSpByPage(Map<String, Object> map);

	List<StayTicket> findAllSp();

	List<StayTicket> findSuccessSp();

	List<StayTicket> findErrorSp();

	int getCountByQuery(Map<String, Object> map);

	List<User> findStayByQuery(Map<String, Object> map);

	List<User> findAllZdls();

}
