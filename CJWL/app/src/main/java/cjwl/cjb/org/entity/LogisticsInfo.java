package cjwl.cjb.org.entity;

/**
 * 物流信息
 * Created by jimbai on 2018/3/21.
 */

public class LogisticsInfo {

    public static final int BEFORETRANS=0;
    public static final int TRANSING=1;
    public static final int ENDTRANS=2;
    public static final int NOPAY=3;
    public static final int PAYED=4;

    private int status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
