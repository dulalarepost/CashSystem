package com.jerome.dao;

import com.jerome.common.AccountStatus;
import com.jerome.common.AccountType;
import com.jerome.entity.Account;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-04
 * Time:16:30
 */
public class AccountDao extends BaseDao {

    //操作数据库
    public Account login(String username, String password) {
        Connection connection = null;//返回连接对象
        PreparedStatement preparedStatement = null;//预处理命令
        ResultSet resultSet = null;//执行成功用resultSet接受结果的集合

        Account account = null;

        try {
            //拿到连接
            connection = this.getConnection(true);
            String sql = "select id,username,password,name,account_type,account_status " +
                    "from account where username=?and password=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            //TODO
            //DigestUtils.md5Hex：加密密码
            preparedStatement.setString(2, DigestUtils.md5Hex(password));
            resultSet = preparedStatement.executeQuery();

            //检查sql语句是否写错
/*            ResultSetMetaData md = preparedStatement.getMetaData();
            int cnt = md.getColumnCount();*/


            //返回结果集到resultSet
            if (resultSet.next()) {
                //解析resultSet，拿到对应的account

                account = this.extractAccount(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }


        return account;
    }

    private Account extractAccount(ResultSet resultSet) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setUsername(resultSet.getString("username"));
        account.setPassword(resultSet.getString("password"));
        account.setName(resultSet.getString("name"));
        account.setAccountType(AccountType.valueOf(resultSet.getInt("account_type")));
        account.setAccountStatus(AccountStatus.valueOf(resultSet.getInt("account_status")));
        return account;
    }

    //注册
    public boolean register(Account account) {
        Connection connection = null;//返回连接对象
        PreparedStatement preparedStatement = null;//预处理命令
        ResultSet resultSet = null;//执行成功用resultSet接受结果的集合
        boolean effect = false;

        try {
            connection = this.getConnection(true);
            String sql = "insert into account (username,password,name,account_type,account_status)values(?,?,?,?,?)";
            //Statement.RETURN_GENERATED_KEYS可以获取插入这条语句的自增值
            preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, DigestUtils.md5Hex(account.getPassword()));
            preparedStatement.setString(3, account.getName());
            preparedStatement.setInt(4, account.getAccountType().getFlag());
            preparedStatement.setInt(5, account.getAccountStatus().getFlag());
            //更新数据库
            effect = (preparedStatement.executeUpdate() == 1);
            resultSet = preparedStatement.getGeneratedKeys();//获取自增主键
            //if (effect) {
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                account.setId(id);

            }
            return effect;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return effect;
    }

    //产看账户
    public List<Account> queryAllAccount() {
        List<Account> accountList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection(true);
            String sql = "select id,username,password,name,account_type,account_status from account";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                accountList.add(extractAccount(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeResource(resultSet, preparedStatement, connection);
        }
        return accountList;
    }

    //修改密码
    public boolean updatePassword(String password,int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean effect = false;

        try {
            connection = this.getConnection(true);
            String sql = "update account set password=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,DigestUtils.md5Hex(password));
            preparedStatement.setInt(2,id);
            if(preparedStatement.executeUpdate() == 1){
                effect = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effect;

    }

    //设置账号启停信息
    public boolean updateAccountStatus(int id){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean effect = false;
        try {
            connection = this.getConnection(true);
            String sql = "update account set account_status=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,2);
            preparedStatement.setInt(2,id);
          if(preparedStatement.executeUpdate() == 1){
              effect = true;
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return effect;
    }

}
