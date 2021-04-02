package com.aca.config;

import java.sql.PreparedStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import liquibase.change.custom.CustomTaskChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;

/**
 * 系统初始化
 *
 * @author lansheng
 */
public class ChangePwd implements CustomTaskChange {
    /** log */
    private Log log = LogFactory.getLog(this.getClass());

    @Override
    public String getConfirmationMessage() {
        return null;
    }

    @Override
    public void setUp() throws SetupException {

    }

    @Override
    public void setFileOpener(ResourceAccessor resourceAccessor) {

    }

    @Override
    public ValidationErrors validate(Database database) {
        return null;
    }

    @Override
    public void execute(Database database) throws CustomChangeException {
        JdbcConnection conn = (JdbcConnection) database.getConnection();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        try (PreparedStatement prepareStatement =
            conn.prepareStatement("update tbl_user set passwd = ? where id = 1");) {
            prepareStatement.setString(1, passwordEncoder.encode("admin"));
            prepareStatement.execute();
            conn.commit();
        } catch (Exception e) {
            log.warn("", e);
        }

        try (PreparedStatement prepareStatement =
            conn.prepareStatement("update tbl_user set passwd = ? where id = 2");) {
            prepareStatement.setString(1, passwordEncoder.encode("user"));
            prepareStatement.execute();
            conn.commit();
        } catch (Exception e) {
            log.warn("", e);
        }

        try (PreparedStatement prepareStatement =
            conn.prepareStatement("update tbl_user set passwd = ? where id = 3");) {
            prepareStatement.setString(1, passwordEncoder.encode("guest"));
            prepareStatement.execute();
            conn.commit();
        } catch (Exception e) {
            log.warn("", e);
        }
    }
}
