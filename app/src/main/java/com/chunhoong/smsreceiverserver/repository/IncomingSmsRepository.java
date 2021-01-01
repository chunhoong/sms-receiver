package com.chunhoong.smsreceiverserver.repository;

import com.chunhoong.smsreceiverserver.core.Database;
import com.chunhoong.smsreceiverserver.core.exceptions.PersistenceException;
import org.jvnet.hk2.annotations.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class IncomingSmsRepository {

    private static final String SAVE_INCOMING_SMS_QUERY = "INSERT INTO INCOMING_SMS (CONTENT, SENDER) VALUES (?, ?)";

    public void saveIncomingSms(IncomingSmsEntity incomingSmsEntity) {
        try (Connection connection = Database.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_INCOMING_SMS_QUERY);
            preparedStatement.setString(1, incomingSmsEntity.getContent());
            preparedStatement.setString(2, incomingSmsEntity.getSender());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new PersistenceException(e);
        }
    }

}
