package time_tracking.utils.extractors;

import time_tracking.model.Entity_With_Id;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This interface defines class to extract entity from result set
 * @param <E>
 */
public interface ResultSetExtactor <E extends Entity_With_Id> {
    E extract(ResultSet set) throws SQLException;
}
