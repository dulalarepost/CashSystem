package com.jerome.dao;

import com.jerome.common.OrderStatus;
import com.jerome.entity.Order;
import com.jerome.entity.OrderItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-10
 * Time:15:45
 */
public class OrderDao extends BaseDao {
    public boolean commitOrder(Order order) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //boolean effect = false;

        try {
            connection = this.getConnection(false);
            //insert into Order
            String insertOrderSql = "insert into `order`" +
                    "(id, account_id, create_time, finish_time, " +
                    "actual_amount, total_money, order_status, " +
                    "account_name) values (?,?,now(),now(),?,?,?,?)";

            String insertOrderItemSql = "insert into order_item (order_id,goods_id,goods_name,goods_introduce," +
                    "goods_num,goods_unit,goods_price,goods_discount) values (?,?,?,?,?,?,?,?)";

            preparedStatement = connection.prepareStatement(insertOrderSql);
            preparedStatement.setString(1, order.getId());
            preparedStatement.setInt(2, order.getAccountId());
            preparedStatement.setInt(3, order.getActualMoney());
            preparedStatement.setInt(4, order.getTotalMoney());
            preparedStatement.setInt(5, order.getOrderStatus().getFlag());
            preparedStatement.setString(6, order.getAccountName());


            if (preparedStatement.executeUpdate() == 0) {
                throw new RuntimeException("插入订单失败");
            }

            //插入订单项
            preparedStatement = connection.prepareStatement(insertOrderItemSql);

            for (OrderItem orderItem : order.orderItemList) {
                preparedStatement.setString(1, orderItem.getOrderId());
                preparedStatement.setInt(2, orderItem.getGoodsId());
                preparedStatement.setString(3, orderItem.getGoodsName());
                preparedStatement.setString(4, orderItem.getGoodsIntroduce());
                preparedStatement.setInt(5, orderItem.getGoodsNum());
                preparedStatement.setString(6, orderItem.getGoodsUnit());
                preparedStatement.setInt(7, orderItem.getGoodsPrice());
                preparedStatement.setInt(8, orderItem.getGoodsDiscount());
                //将每一项preparedSatement缓存好
                preparedStatement.addBatch();
            }
            int[] effects = preparedStatement.executeBatch();
            for (int i : effects) {
                if (i == 0) {
                    throw new RuntimeException("插入订单明细数据失败");
                }
            }
            //手动提交事务
            connection.commit();
        } catch (SQLException e) {

            e.printStackTrace();

            if (connection != null) {
                try {
                    connection.rollback();//回滚//只要涉及多表插入和批量插入一定要进行回滚
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            this.closeResource(null, preparedStatement, connection);
        }

        return true;
    }


    //查询订单
    public List<Order> queryOrderByAccount(Integer accountId) {
        List<Order> orderList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = this.getConnection(false);
            String sql = this.getSql("@query_order_by_account");
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, accountId);

            resultSet = preparedStatement.executeQuery();

            Order order = null;
            while (resultSet.next()) {

                if (order == null) {
                    order = new Order();
                    this.extractOrder(order, resultSet);
                    orderList.add(order);
                }


                String orderId = resultSet.getString("order_id");//拿到当前的order_id
                //当订单信息不同的时候生成一个订单，订单对象只有有一个，因为其中包含了很多的订单信息
                //如果给每一个订单信息都生成一个订单是不合理的
                if (!orderId.equals(order.getId())) {
                    order = new Order();
                    this.extractOrder(order, resultSet);
                    orderList.add(order);
                }
                //往当前订单添加具体的订单项
                OrderItem orderItem = this.extractOrderItem(resultSet);

                order.getOrderItemList().add(orderItem);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return orderList;
    }

    public void extractOrder(Order order, ResultSet resultSet) throws SQLException {
        order.setId(resultSet.getString("order_id"));
        order.setAccountId(resultSet.getInt("account_id"));
        order.setCreateTime(resultSet.getTimestamp("create_time").toLocalDateTime());
        Timestamp finishTime = resultSet.getTimestamp("finish_time");
        if (finishTime != null) {
            order.setFinishTime(finishTime.toLocalDateTime());

        }
        order.setActualMoney(resultSet.getInt("actual_amount"));
        order.setTotalMoney(resultSet.getInt("total_money"));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.getInt("order_status")));

        order.setAccountName(resultSet.getString("account_name"));

    }

    public OrderItem extractOrderItem(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(resultSet.getInt("item_id"));//TODO
        orderItem.setGoodsId(resultSet.getInt("goods_id"));
        orderItem.setGoodsName(resultSet.getString("goods_name"));
        orderItem.setGoodsIntroduce(resultSet.getString("goods_introduce"));
        orderItem.setGoodsNum(resultSet.getInt("goods_num"));
        orderItem.setGoodsUnit(resultSet.getString("goods_unit"));
        orderItem.setGoodsPrice(resultSet.getInt("goods_price"));
        orderItem.setGoodsDiscount(resultSet.getInt("goods_discount"));
        return orderItem;
    }
}
