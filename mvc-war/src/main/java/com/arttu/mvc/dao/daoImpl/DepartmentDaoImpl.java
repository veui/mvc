package com.arttu.mvc.dao.daoImpl;

import com.arttu.mvc.dao.DepartmentDao;
import com.arttu.mvc.dao.enums.DepartmentQueries;
import com.arttu.mvc.model.Department;
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
public class DepartmentDaoImpl implements DepartmentDao {

    private static final Logger LOGGER = LogManager.getLogger(DepartmentDaoImpl.class);
    private DataSource dataSource;

    @Autowired
    public DepartmentDaoImpl(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource must not be null");
        }
        this.dataSource = dataSource;
    }

    @Override
    public void add(Department department) {
        LOGGER.info("Add method started to work");
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (DepartmentQueries.SQL_INSERT.getValue())) {
                statement.setString(1, department.getTitle());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Department department) {
        LOGGER.info("Method delete department started to work");
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(DepartmentQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, department.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteById(int id) {
        LOGGER.info("Method delete department started to work");
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(DepartmentQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Specialty> findAttachedSpecialties(int id) {
        LOGGER.info("Method find attached specialties started to work");
        List<Specialty> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement
                    (DepartmentQueries.SQL_FIND_SPECIALTIES.getValue())) {
                statement.setInt(1, id);
                try(ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Specialty specialty = new Specialty();
                        specialty.setId(resultSet.getInt("SPECIALTY_ID"));
                        specialty.setTitle(resultSet.getString("TITLE"));
                        specialty.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
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
    public Department findByTitle(String title) {
        LOGGER.info("Method find department by title  started to work");
        Department department = new Department();
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement preparedStatement =
                        connection.prepareStatement(DepartmentQueries.SQL_FIND_BY_TITLE.getValue())) {
                preparedStatement.setString(1, title);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        department.setId(resultSet.getInt(1));
                        department.setTitle(resultSet.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return department;
    }

    @Override
    public void edit(Department department) {
        LOGGER.info("Method edit department started to work");
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(DepartmentQueries.SQL_UPDATE.getValue())) {
                statement.setString(1, department.getTitle());
                statement.setInt(2, department.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Department> findAll() {
        LOGGER.info("Method find all departments started to work");
        List<Department> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery(DepartmentQueries.SQL_FIND_ALL.getValue())) {
                    while (resultSet.next()) {
                        Department department = new Department();
                        department.setId(resultSet.getInt(1));
                        department.setTitle(resultSet.getString(2));
                        result.add(department);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public Department findById(int id) {
        LOGGER.info("Method find by id department started to work");
        Department department = null;
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement preparedStatement =
                        connection.prepareStatement(DepartmentQueries.SQL_FIND_BY_ID.getValue())) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        department = new Department();
                        department.setId(resultSet.getInt(1));
                        department.setTitle(resultSet.getString(2));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return department;
    }
}
