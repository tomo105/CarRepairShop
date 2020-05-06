package com.carrepairshop.springframework.agh.dao;

import com.carrepairshop.springframework.agh.history.History;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository("mysqlHistory")
public class MySqlHistoryImpl implements HistoryDao {

    private final Logger LOGGER = LoggerFactory.getLogger(MySqlHistoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static class HistoryRowMapper implements RowMapper<History> {

        @Override
        public History mapRow(ResultSet resultSet, int i) throws SQLException {
            History history = new History();
            history.setId(resultSet.getInt("id"));
            history.setDate(resultSet.getString("date"));
            history.setTime(resultSet.getString("time"));
            history.setDescription(resultSet.getString("description"));
            history.setType(resultSet.getString("type"));
            return history;
        }
    }

    private static final String getHistory = "SELECT id, date, time, description, type FROM HISTORY ";

    @Override
    public Collection<History> getAllHistory() {
        return jdbcTemplate.query(getHistory, new HistoryRowMapper());
    }

}
