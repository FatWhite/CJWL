package cjwl.cjb.org.entity;

/**
 * 物流信息
 * Created by jimbai on 2018/3/21.
 */

public class LogisticsInfo {

    private GoodsInfo goodsInfo;
    private PersonInfo senderInfo;
    private PersonInfo receiverInfo;

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public PersonInfo getSenderInfo() {
        return senderInfo;
    }

    public void setSenderInfo(PersonInfo senderInfo) {
        this.senderInfo = senderInfo;
    }

    public PersonInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(PersonInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }
}
