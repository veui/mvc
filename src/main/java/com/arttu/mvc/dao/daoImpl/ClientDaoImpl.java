package com.arttu.mvc.dao.daoImpl;

import com.arttu.mvc.dao.ClientDao;
import com.arttu.mvc.dao.enums.ClientQueries;
import com.arttu.mvc.model.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDaoImpl implements ClientDao {

    private static final Logger LOGGER = LogManager.getLogger(ClientDaoImpl.class);

    private DataSource dataSource;

    @Autowired
    public ClientDaoImpl(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("DataSource must not be null");
        }
        this.dataSource = dataSource;
    }


    @Override
    public void add(Client client) {
        LOGGER.info("Method add started to work");
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(ClientQueries.SQL_INSERT.getValue())) {
                statement.setString(1, client.getUsername());
                statement.setString(2, client.getPassword());
                statement.setString(3, client.getLastName());
                statement.setString(4, client.getFirstName());
                statement.setString(5, client.getEmail());
                statement.setInt(6, client.getPhone());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void delete(Client client) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(ClientQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, client.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void edit(Client client) {
        LOGGER.info("Method edit started to work " + client.toString());
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(ClientQueries.SQL_UPDATE.getValue())) {
                statement.setString(1, client.getUsername());
                statement.setString(2, client.getPassword());
                statement.setString(3, client.getFirstName());
                statement.setString(4, client.getLastName());
                statement.setString(5, client.getEmail());
                statement.setInt(6, client.getPhone());
                statement.setInt(7, client.getId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public List<Client> findAll() {
        List<Client> result = new ArrayList<>();
        try(Connection connection = dataSource.getConnection()) {
            try(Statement statement = connection.createStatement()) {
                try(ResultSet resultSet = statement.executeQuery(ClientQueries.SQL_FIND_ALL.getValue())) {
                    while (resultSet.next()) {
                        Client client = new Client();
                        client.setId(resultSet.getInt(1));
                        client.setUsername(resultSet.getString(2));
                        client.setPassword(resultSet.getString(3));
                        client.setFirstName(resultSet.getString(4));
                        client.setLastName(resultSet.getString(5));
                        client.setEmail(resultSet.getString(6));
                        client.setPhone(resultSet.getInt(7));
                        result.add(client);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return result;
    }

    @Override
    public Client findById(int id) {
        Client client = null;
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement preparedStatement =
                        connection.prepareStatement(ClientQueries.SQL_FIND_BY_ID.getValue())) {
                preparedStatement.setInt(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        client = new Client();
                        client.setId(resultSet.getInt(1));
                        client.setId(resultSet.getInt(1));
                        client.setUsername(resultSet.getString(2));
                        client.setPassword(resultSet.getString(3));
                        client.setFirstName(resultSet.getString(4));
                        client.setLastName(resultSet.getString(5));
                        client.setEmail(resultSet.getString(6));
                        client.setPhone(resultSet.getInt(7));
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return client;
    }

    @Override
    public void deleteById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            try(PreparedStatement statement = connection.prepareStatement(ClientQueries.SQL_DELETE.getValue())) {
                statement.setInt(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
