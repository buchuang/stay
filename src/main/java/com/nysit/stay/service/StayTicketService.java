package com.nysit.stay.service;

import java.util.List;

import com.nysit.stay.domain.StayTicket;
import com.nysit.stay.domain.User;
import com.nysit.stay.utils.Datas;

public interface StayTicketService {

	int getCount(Integer integer);

	List<User> findStayByPage(Integer page, Integer rows, Integer integer);

	void insertStayTicket(StayTicket st);

	void deleteStayTicket(Datas ds);

	void editStayTicket(StayTicket st);

	void editStayTicketStatus(StayTicket st);

	StayTicket findBtByPiid(String piid);

	void updateAuthStatus(StayTicket st);

	int getYslxdCount(Integer id);

	List<User> findYslxdByPage(Integer page, Integer rows, Integer id);

	int getAllSpCount();

	List<StayTicket> findAllSpByPage(Integer page, Integer rows);

	List<StayTicket> findAllSp();

	List<StayTicket> findSuccessSp();

	List<StayTicket> findErrorSp();

	int getCountByQuery(String queryValue);

	List<User> findStayByQuery(Integer page, Integer rows, String queryValue);

	List<User> findAllZdls();

}