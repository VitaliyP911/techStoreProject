package com.epam.service.impl;

import com.epam.dao.CrudDaoImpl;
import com.epam.dao.impl.HistoryDaoImpl;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.entity.History;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.HistoryService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class HistoryServiceImpl implements HistoryService {
    private CrudDaoImpl<History> historyCrudDao;

    public HistoryServiceImpl() {
        historyCrudDao = new HistoryDaoImpl();
    }

    @Override
    public boolean addPurchaseToHistory(List<History> historyList) {
        historyList.stream().forEach(i -> historyCrudDao.save(i));
        return true;
    }

    @Override
    public boolean clearHistory(User user) {
        return historyCrudDao.deleteListByFiled(String.valueOf(user.getId()));
    }

    @Override
    public Timestamp currentTimeGeneration() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    @Override
    public List<History> getHistory(User user) {
        return historyCrudDao.getListByField(String.valueOf(user.getId()));
    }
}
