package com.arttu.mvc.dao.daoImpl;

import com.arttu.mvc.dao.ItemDao;
import com.arttu.mvc.dao.enums.ItemQueries;
import com.arttu.mvc.model.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {

    private static final Logger LOGGER = LogManager.getLogger(ItemDaoImpl.class);
    private DataSource dataSource;

    @Autowired
    public ItemDaoImpl(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource must not be null");
        }
        this.dataSource = dataSource;
    }

    @Override
    public void add(Item item) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(ItemQueries.SQL_INSERT.getValue())) {
                statement.setString(1, item.getItem());
                statement.setFloat(2, item.getPrice());
                statement.setInt(3, item.getSpecialtyId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Item item)  {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(ItemQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void edit(Item item) {
        LOGGER.info("Method edit started to work " + item.toString());
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (ItemQueries.SQL_UPDATE.getValue())) {
                statement.setString(1, item.getItem());
                statement.setFloat(2, item.getPrice());
                statement.setInt(3, item.getSpecialtyId());
                statement.setInt(4, item.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet set = statement.executeQuery(ItemQueries.SQL_FIND_ALL.getValue())) {
                    while (set.next()) {
                        Item item = new Item();
                        item.setId(set.getInt("item_id"));
                        item.setItem(set.getString("item"));
                        item.setPrice(set.getFloat("price"));
                        item.setSpecialtyId(set.getInt("specialty_id"));
                        result.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = new Item();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement =
                        connection.prepareStatement(ItemQueries.SQL_FIND_BY_ID.getValue())) {
                statement.setInt(1, id);
                try(ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        result.setId(set.getInt(1));
                        result.setItem(set.getString(2));
                        result.setPrice(set.getFloat(3));
                        result.setSpecialtyId(set.getInt(4));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public void deleteById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(ItemQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean editItem(Item item) {
        boolean isOk;
        LOGGER.info("Method editItem started to work " + item.toString());
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (ItemQueries.SQL_UPDATE.getValue())) {
                statement.setString(1, item.getItem());
                statement.setFloat(2, item.getPrice());
                statement.setInt(3, item.getSpecialtyId());
                statement.setInt(4, item.getId());
                statement.executeUpdate();
                isOk = true;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            isOk = false;
        }
        return isOk;
    }

    @Override
    public Item findByTitle(String title) {
        Item result = new Item();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(
                    ItemQueries.SQL_FIND_BY_TITLE.getValue())) {
                statement.setString(1, title);
                try(ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        result.setId(rs.getInt(1));
                        result.setItem(rs.getString(2));
                        result.setPrice(rs.getFloat(3));
                        result.setSpecialtyId(rs.getInt(4));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public List<Item> hierarchicalItem() {
        List<Item> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet set = statement.executeQuery(ItemQueries.SQL_HIERARCHICAL_PRIOR_ITEM.getValue())) {
                    while (set.next()) {
                        Item item = new Item();
                        item.setId(set.getInt("item_id"));
                        item.setItem(set.getString("item"));
                        item.setPrice(set.getFloat("price"));
                        item.setSpecialtyId(set.getInt("specialty_id"));
                        result.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public List<Item> hierarchicalSpec() {
        List<Item> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet set = statement.executeQuery(ItemQueries.SQL_HIERARCHICAL_PRIOR_SPECIALTY.getValue())) {
                    while (set.next()) {
                        Item item = new Item();
                        item.setId(set.getInt("item_id"));
                        item.setItem(set.getString("item"));
                        item.setPrice(set.getFloat("price"));
                        item.setSpecialtyId(set.getInt("specialty_id"));
                        result.add(item);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }
}
