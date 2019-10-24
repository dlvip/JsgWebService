package com.old.time.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GoodsEntity {

    public GoodsEntity(){

    }

    public GoodsEntity(String goodsId, String goodsPic) {
        this.goodsId = goodsId;
        this.goodsPic = goodsPic;

    }

    @Id
    @GeneratedValue
    private Integer id;

    private String goodsId;

    private String goodsPic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }
}
