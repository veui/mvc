package com.arttu.mvc.dao.daoImpl;

import com.arttu.mvc.dao.SpecialtyDao;
import com.arttu.mvc.dao.enums.SpecialtyQueries;
import com.arttu.mvc.model.Item;
import com.arttu.mvc.model.ItemSpecialtyWrapper;
import com.arttu.mvc.model.Specialty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SpecialtyDaoImpl implements SpecialtyDao {

    private static final Logger LOGGER = LogManager.getLogger(SpecialtyDaoImpl.class);
    private DataSource dataSource;

    @Autowired
    public SpecialtyDaoImpl(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource must not be null");
        }
        this.dataSource = dataSource;
    }

    @Override
    public void add(Specialty specialty) {
        LOGGER.info("Add method started to work");
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (SpecialtyQueries.SQL_INSERT.getValue())) {
                statement.setString(1, specialty.getTitle());
                statement.setInt(2, specialty.getDepartmentId());
                statement.setInt(3, specialty.getParentId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Specialty specialty) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (SpecialtyQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, specialty.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (SpecialtyQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean editSpecialty(Specialty specialty) {
        LOGGER.info("editSpecialty method started to work");
        boolean isOk;
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (SpecialtyQueries.SQL_UPDATE.getValue())) {
                statement.setString(1, specialty.getTitle());
                statement.setInt(2, specialty.getDepartmentId());
                statement.setInt(3, specialty.getParentId());
                statement.setInt(4, specialty.getId());
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
    public List<Item> findAttachedItems(int id) {
        LOGGER.info("Find attached items started to work");
        List<Item> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(SpecialtyQueries.SQL_FIND_ITEMS.getValue())) {
                statement.setInt(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Item item = new Item();
                        item.setId(resultSet.getInt(1));
                        item.setItem(resultSet.getString(2));
                        item.setPrice(resultSet.getFloat(3));
                        item.setSpecialtyId(resultSet.getInt(4));
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
    public List<ItemSpecialtyWrapper> hierarchicalQuery() {
        List<ItemSpecialtyWrapper> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(SpecialtyQueries.SQL_HIERARCHICAL_QUERY.getValue())) {
                try(ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        ItemSpecialtyWrapper wrapper = new ItemSpecialtyWrapper();
                        wrapper.setId(set.getInt("item_id"));
                        wrapper.setItem(set.getString("item"));
                        wrapper.setPrice(set.getFloat("price"));
                        wrapper.setSpecialtyId(set.getInt("specialty_id"));
                        wrapper.setSpecId(set.getInt("specialty_id"));
                        wrapper.setTitle(set.getString("title"));
                        wrapper.setDepartmentId(set.getInt("department_id"));
                        wrapper.setParentId(set.getInt("parent_id"));
                        result.add(wrapper);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public List<ItemSpecialtyWrapper> hierarchicalQueryId(int id) {
        List<ItemSpecialtyWrapper> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(
                    SpecialtyQueries.SQL_HIERARCHICAL_SELECTED_QUERY.getValue())) {
                statement.setInt(1, id);
                try(ResultSet set = statement.executeQuery()) {
                    while (set.next()) {
                        ItemSpecialtyWrapper wrapper = new ItemSpecialtyWrapper();
                        wrapper.setId(set.getInt("item_id"));
                        wrapper.setItem(set.getString("item"));
                        wrapper.setPrice(set.getFloat("price"));
                        wrapper.setSpecialtyId(set.getInt("specialty_id"));
                        wrapper.setSpecId(set.getInt("specialty_id"));
                        wrapper.setTitle(set.getString("title"));
                        wrapper.setDepartmentId(set.getInt("department_id"));
                        wrapper.setParentId(set.getInt("parent_id"));
                        result.add(wrapper);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public void edit(Specialty specialty) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (SpecialtyQueries.SQL_UPDATE.getValue())) {
                statement.setString(1, specialty.getTitle());
                statement.setInt(2, specialty.getDepartmentId());
                statement.setInt(3, specialty.getId());
                statement.setInt(4, specialty.getParentId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Specialty> findAll() {
        List<Specialty> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery(SpecialtyQueries.SQL_FIND_ALL.getValue())) {
                    while (resultSet.next()) {
                        Specialty specialty = new Specialty();
                        specialty.setId(resultSet.getInt("specialty_id"));
                        specialty.setTitle(resultSet.getString("title"));
                        specialty.setDepartmentId(resultSet.getInt("department_id"));
                        specialty.setParentId(resultSet.getInt("parent_id"));
                        result.add(specialty);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public Specialty findById(int id) {
        Specialty specialty = new Specialty();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement preparedStatement =
                        connection.prepareStatement(SpecialtyQueries.SQL_FIND_BY_ID.getValue())) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        specialty.setId(resultSet.getInt(1));
                        specialty.setTitle(resultSet.getString(2));
                        specialty.setDepartmentId(resultSet.getInt(3));
                        specialty.setParentId(resultSet.getInt(4));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return specialty;
    }
}
