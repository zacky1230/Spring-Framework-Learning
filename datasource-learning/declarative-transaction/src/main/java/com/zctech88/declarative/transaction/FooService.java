package com.zctech88.declarative.transaction;

/**
 * @author <a href="mailto:gy1zc3@gmail.com">zacky</a>
 * @since 11/24/19 13:03
 */
public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;
}
