package com.ringcentral.qa.reporter.logger.storage;

import com.ringcentral.qa.common.utils.CollectionUtils;
import org.testng.TestNGException;

import java.sql.*;
import java.util.*;

/**
 * <code>H2DBStorage</code> represents connection to embedded database. H2 database is multithreading-safe.
 * So you can use different instances of <code>H2DBStorage</code> in different threads despite they are connected to the same database.
 *
 * @author Anton Gnutov
 */
public class H2DBStorage implements LogStorage, Iterable<String> {
    private static final String DRIVER = "org.h2.Driver";
    private static final String USER = "sa";
    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD = "";

    private static final int MAX_MESSAGE_SIZE = 1000000;

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS LOGS";
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS LOGS(ID IDENTITY, UUID VARCHAR(255), CONTEXT VARCHAR(255), MESSAGE VARCHAR(" + MAX_MESSAGE_SIZE + "))";
    private static final String CREATE_UUID_INDEX = "CREATE INDEX IF NOT EXISTS UUID_INDEX ON LOGS (UUID)";
    private static final String CREATE_CONTEXT_INDEX = "CREATE INDEX IF NOT EXISTS CONTEXT_INDEX ON LOGS (CONTEXT)";
    private static final String CLEAR_TABLE = "DELETE FROM LOGS";
    private static final String INSERT_INTO_TABLE = "INSERT INTO LOGS(UUID, CONTEXT, MESSAGE) VALUES (?, ?, ?)";
    private static final String SELECT_BY_UUID = "SELECT MESSAGE FROM LOGS WHERE UUID = ? ORDER BY ID";
    private static final String SELECT_BY_CONTEXTS = "SELECT MESSAGE FROM LOGS WHERE CONTEXT IN %s ORDER BY ID";
    private static final String SELECT_CONTEXTS = "SELECT DISTINCT CONTEXT FROM LOGS WHERE UUID = ?";
    private static final String SELECT_ALL = "SELECT MESSAGE FROM LOGS ORDER BY ID";

    public static final int BUFFER_SIZE = 100;
    private final String url;
    private final boolean useContext;

    private Connection connection;

    public H2DBStorage(String url) {
        this(url, false);
    }

    public H2DBStorage(String url, boolean useContext) {
        this.url = url;
        this.useContext = useContext;
    }

    public List<String> get(String key) {
        ResultSet resultSet = null; //NOSONAR
        List<String> result = new ArrayList<>();

        if (useContext) {
            List<String> contexts = new ArrayList<>();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CONTEXTS)) {
                preparedStatement.setString(1, key);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    if (resultSet.getString(1) != null) {
                        contexts.add(resultSet.getString(1));
                    }
                }
            } catch (SQLException e) {
                // don't know what to do with exception
                return Collections.emptyList();
            } finally {
                close(resultSet);
            }

            final String params = CollectionUtils.toString(contexts, "('", "', '", "')");
            final String statement = String.format(SELECT_BY_CONTEXTS, params);
            try (PreparedStatement preparedStatement = connection.prepareStatement(statement)) {
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    result.add(resultSet.getString(1));
                }
            } catch (SQLException e) {
                // don't know what to do with exception
                return Collections.emptyList();
            } finally {
                close(resultSet);
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_UUID)) {
                preparedStatement.setString(1, key);
                resultSet = preparedStatement.executeQuery();

                while(resultSet.next()) {
                    result.add(resultSet.getString(1));
                }
            } catch (SQLException e) {
                // don't know what to do with exception
                return Collections.emptyList();
            } finally {
                close(resultSet);
            }
        }

        return result;
    }

    public Iterable<String> getAll() {
        return this;
    }

    public Iterator<String> iterator() {
        try {
            final Statement statement = connection.createStatement(); //NOSONAR
            final ResultSet resultSet = statement.executeQuery(SELECT_ALL); //NOSONAR

            return new Iterator<String>() {
                private int index = 0;
                private List<String> buffer = new ArrayList<>(BUFFER_SIZE);

                @Override
                public boolean hasNext() {
                    try {
                        if (index >= buffer.size()) {
                            readBuffer(resultSet);
                            index = 0;
                        }

                        boolean hasNext = index < buffer.size();

                        if (!hasNext) {
                            close(resultSet);
                            close(statement);
                        }
                        return hasNext;
                    } catch (SQLException e) {
                        // why could it happen?
                        close(resultSet);
                        close(statement);
                        return false;
                    }
                }

                @Override
                public String next() {
                    return buffer.get(index++);
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }

                private void readBuffer(ResultSet resultSet) throws SQLException {
                    buffer.clear();
                    for (int i = 0; i < BUFFER_SIZE && resultSet.next(); i++) {
                        buffer.add(resultSet.getString(1));
                    }
                }
            };
        } catch(SQLException e) {
            // what can we do here?
            return null;
        }
    }

    @Override
    @SuppressWarnings("findbugs:DMI_EMPTY_DB_PASSWORD")
    public void initialize() {
        Statement statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, USER, PASSWORD);
            connection.setAutoCommit(true);

            statement = connection.createStatement();
            statement.execute(DROP_TABLE);
            statement.execute(CREATE_TABLE);
            statement.execute(CREATE_UUID_INDEX);
            if (useContext) {
                statement.execute(CREATE_CONTEXT_INDEX);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new TestNGException(e);
        } finally {
            close(statement);
        }
    }

    public void put(String key, String context, String message) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_TABLE)) {
            preparedStatement.setString(1, key);
            preparedStatement.setString(2, context);
            preparedStatement.setString(3, message.length() <= MAX_MESSAGE_SIZE ? message : message.substring(0, MAX_MESSAGE_SIZE));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CLEAR_TABLE);
        } catch (SQLException e) {
            // do nothing
        }
    }

    public void close() {
        close(connection);
    }

    private void close(AutoCloseable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e) {
                // do nothing
            }
        }
    }
}
