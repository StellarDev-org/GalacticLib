package org.stellardev.galacticlib.database.types;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;
import org.bukkit.scheduler.BukkitRunnable;
import org.stellardev.galacticlib.database.Database;
import org.stellardev.galacticlib.entity.Conf;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class MySQL extends Database {

    private HikariDataSource hikariDataSource;

    private String username, password;

    public MySQL(String dbName, String username, String password) {
        super(dbName);

        this.username = username;
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return this.hikariDataSource.getConnection();
    }

    @Override
    public void openConnection() throws Exception {
        if(Conf.get().sqlDatabaseIp.isEmpty()) {
            throw new Exception("Database IP is not set in the galacticlib_conf.");
        }

        if(this.databaseName == null || this.databaseName.isEmpty()) {
            throw new Exception("Database name is not set.");
        }

        if(this.username == null || this.password == null) {
            throw new Exception("Database username and/or password is not set.");
        }

        if(this.hikariDataSource != null && !this.hikariDataSource.isClosed()) {
            throw new Exception("The connection is already connected and stable");
        }

        String connectionURL = "jdbc:mysql://" + Conf.get().sqlDatabaseIp + "/" + this.databaseName;
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(connectionURL);
        hikariConfig.setUsername(this.username);
        hikariConfig.setPassword(this.password);

        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
        hikariConfig.addDataSourceProperty("useLocalSessionState", true);
        hikariConfig.addDataSourceProperty("rewriteBatchedStatements", true);
        hikariConfig.addDataSourceProperty("cacheResultSetMetadata", true);
        hikariConfig.addDataSourceProperty("cacheServerConfiguration", true);
        hikariConfig.addDataSourceProperty("elideSetAutoCommits", true);
        hikariConfig.addDataSourceProperty("maintainTimeStats", false);

        hikariConfig.addDataSourceProperty("leakDetectionThreshold", 10000);

        hikariConfig.setMaximumPoolSize(50);
        hikariConfig.setMinimumIdle(5);

        String poolName = this.getClass().getSimpleName();

        hikariConfig.setPoolName(poolName);

        this.hikariDataSource = new HikariDataSource(hikariConfig);

        getActivePlugin().log("Connected to database '" + this.databaseName + "'");

        this.username = null;
        this.password = null;

        long delay = Conf.get().sqlLogPoolDelayTick;

        new BukkitRunnable() {
            public void run() {
                if(hikariDataSource == null || hikariDataSource.isClosed()) {
                    this.cancel();
                    return;
                }

                if(!Conf.get().sqlLogPool) return;

                HikariPoolMXBean hikariPool = hikariDataSource.getHikariPoolMXBean();

                getActivePlugin().log(poolName + ": Active Connections=" + hikariPool.getActiveConnections() + ",Idle Connections=" + hikariPool.getIdleConnections() + ",Threads Awaiting Connection=" + hikariPool.getThreadsAwaitingConnection() + ",Total Connections=" + hikariPool.getTotalConnections());
            }
        }.runTaskTimerAsynchronously(getActivePlugin(), delay, delay);
    }

    @Override
    public void closeConnection() throws Exception {
        this.hikariDataSource.close();
    }

    public String getPushOrUpdateRow(String table) {
        return "INSERT INTO " + table + getPushRowKeys(false) + " VALUES " + getPushRowValues() + " ON DUPLICATE KEY UPDATE " + getPushRowKeys(true);
    }
}
