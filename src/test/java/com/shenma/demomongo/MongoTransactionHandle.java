package com.shenma.demomongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.client.ClientSession;

/**
 * 编程式事务控制
 */
public class MongoTransactionHandle {


    private static ThreadLocal<ClientSession> clientSessionLocal = new ThreadLocal<ClientSession>();

    /**
     * @Title: startTransaction
     * @Description: 开启事务
     * @return void    返回类型
     * @author chen shihua
     * @throws
     */
    public static void startTransaction(MongoClient mongoClient) {
        ClientSession clientSession = mongoClient.startSession();
        while (true) {
            try {
                clientSession.startTransaction();
                break;
            } catch (Exception e) {
                clientSession.abortTransaction();
            }
        }
        MongoTransactionHandle.setClientSession(clientSession);
        System.out.println("======================开启事务=====================");
    }

    /**
     * @Title: commitTransaction
     * @Description: 提交事务
     * @return void    返回类型
     * @author chen shihua
     * @throws
     */
    public static void commitTransaction() {
        ClientSession clientSession = MongoTransactionHandle.getClientSession();
        while (true) {
            try {
                if(clientSession != null) {
                    clientSession.commitTransaction();
                    System.out.println("======================提交事务=====================");
                }
                break;
            } catch (MongoException e) {
                // can retry commit
                if (e.hasErrorLabel(MongoException.UNKNOWN_TRANSACTION_COMMIT_RESULT_LABEL)) {
                    System.out.println("UnknownTransactionCommitResult, retrying commit operation ...");
                    continue;
                } else {
                    System.out.println("Exception during commit ...");
                    throw e;
                }
            }
        }
    }

    /**
     * @Title: abortTransaction
     * @Description: 中止事务
     * @return void    返回类型
     * @author chen shihua
     * @throws
     */
    public static void abortTransaction() {
        ClientSession clientSession = MongoTransactionHandle.getClientSession();
        if(clientSession != null) {
            clientSession.abortTransaction();
            clientSession = null;
            MongoTransactionHandle.setClientSession(clientSession);
            System.out.println("======================中止事务=====================");
        }
//		throw new XueShuException("异常");
    }

    public static  ClientSession getClientSession() {
        return clientSessionLocal.get();
    }

    public static void setClientSession(ClientSession clientSession) {
        clientSessionLocal.set(clientSession);
    }
}