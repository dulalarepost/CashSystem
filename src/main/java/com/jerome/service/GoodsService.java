package com.jerome.service;

import com.jerome.dao.GoodsDao;
import com.jerome.entity.Goods;

import java.util.List;

/**
 * Created with InteIIiJ IDEA.
 * Description:
 * User:
 * Date:2019-08-06
 * Time:15:45
 */
public class GoodsService {
    public GoodsDao goodsDao;

    public GoodsService() {
        this.goodsDao = new GoodsDao();
    }

    public List<Goods> queryAllGoods() {
        return this.goodsDao.queryAllGoods();
    }

    //上架商品
    public boolean putAwayGoods(Goods goods) {
        return this.goodsDao.putAwayGoods(goods);
    }

    //通过goodsId拿到对应货物
    public Goods getGoods(int goodsId) {
        return this.goodsDao.getGoods(goodsId);
    }

    //更新商品
    public boolean modifyGoods(Goods goods) {
        return this.goodsDao.modifyGoods(goods);
    }
    public boolean soldOutGoods(int goodsId){
        return this.goodsDao.soldOutGoods(goodsId);
    }
    public boolean updateAfterPay(Goods goods,int goodsNum){
        return this.goodsDao.updateAfterPay(goods,goodsNum);
    }
}
