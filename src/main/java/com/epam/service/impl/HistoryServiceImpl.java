package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.HistoryDaoImpl;
import com.epam.entity.History;
import com.epam.entity.User;
import com.epam.service.HistoryService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    private CrudDaoImpl<History> historyCrudDao;
    /**
     * Default constructor.
     */
    public HistoryServiceImpl() {
        historyCrudDao = new HistoryDaoImpl();
    }
    /**
     * Method for add the purchase to the story.
     *
     * @param historyList
     * @return true if the purchase is added to the history
     */
    @Override
    public boolean addPurchaseToHistory(List<History> historyList) {
        try{
            historyList.stream().forEach(i -> historyCrudDao.save(i));
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }
    /**
     * Method for clearing user history.
     *
     * @param user
     * @return true if cleared history
     */
    @Override
    public boolean clearHistory(User user) {
        return historyCrudDao.deleteListByFiled(String.valueOf(user.getId()));
    }
    /**
     * Method getting the time at the moment.
     *
     * @return the time at the moment.
     */
    @Override
    public Timestamp currentTimeGeneration() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
    /**
     * Method getting user history.
     *
     * @param user
     * @return history list
     */
    @Override
    public List<History> getHistory(User user) {
        return historyCrudDao.getListByField(String.valueOf(user.getId()));
    }
}
