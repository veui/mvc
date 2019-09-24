package com.arttu.mvc.dao.daoImpl;

import com.arttu.mvc.dao.OrderDao;
import com.arttu.mvc.dao.enums.OrderQueries;
import com.arttu.mvc.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    private static final Logger LOGGER = LogManager.getLogger(OrderDaoImpl.class);
    private DataSource dataSource;

    @Autowired
    public OrderDaoImpl(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource must not be null");
        }
        this.dataSource = dataSource;
    }

    @Override
    public void add(Order order) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(OrderQueries.SQL_INSERT.getValue())) {
                statement.setDate(1, order.getDate());
                statement.setInt(2, order.getAmount());
                statement.setFloat(3, order.getCost());
                statement.setInt(4, order.getClientId());
                statement.setInt(5, order.getItemId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Order order) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(OrderQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, order.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void edit(Order order) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(OrderQueries.SQL_UPDATE.getValue())) {
                statement.setDate(1, order.getDate());
                statement.setInt(2, order.getAmount());
                statement.setFloat(3, order.getCost());
                statement.setInt(4, order.getClientId());
                statement.setInt(5, order.getItemId());
                statement.setInt(6, order.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                ResultSet set = statement.executeQuery(OrderQueries.SQL_FIND_ALL.getValue());
                while (set.next()) {
                    Order order = new Order();
                    order.setId(set.getInt("order_id"));
                    order.setDate(set.getDate("order_date"));
                    order.setAmount(set.getInt("amount"));
                    order.setCost(set.getFloat("cost"));
                    order.setClientId(set.getInt("client_id"));
                    order.setItemId(set.getInt("item_id"));
                    result.add(order);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public Order findById(int id) {
        Order order = new Order();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(OrderQueries.SQL_FIND_BY_ID.getValue())) {
                statement.setInt(1, id);
                try(ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        order.setId(set.getInt("order_id"));
                        order.setDate(set.getDate("order_date"));
                        order.setAmount(set.getInt("amount"));
                        order.setCost(set.getFloat("cost"));
                        order.setClientId(set.getInt("client_id"));
                        order.setItemId(set.getInt("item_id"));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return order;
    }

    @Override
    public void deleteById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(OrderQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean editOrder(Order order) {
        LOGGER.info("editOrder started to work");
        boolean isOk;
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(OrderQueries.SQL_UPDATE.getValue())) {
                statement.setDate(1, order.getDate());
                statement.setInt(2, order.getAmount());
                statement.setFloat(3, order.getCost());
                statement.setInt(4, order.getClientId());
                statement.setInt(5, order.getItemId());
                statement.setInt(6, order.getId());
                isOk = true;
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            isOk = false;
        }
        return isOk;
    }
}
