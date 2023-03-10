package work.gaigeshen.tripartite.wangdian.openapi.response.stock;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class StockTransferQueryResponse extends AbstractWangdianResponse {

    public Integer total_count;

    public Collection<Transfer> transfer_list;


    public static class Transfer {

        public String transfer_no;

        public Integer type;

        public Integer mode;

        public String from_warehouse_no;

        public String to_warehouse_no;

        public Integer from_warehouse_id;

        public Integer to_warehouse_id;

        public Integer status;

        public String outer_no;

        public BigDecimal goods_count;

        public BigDecimal goods_type_count;

        public BigDecimal goods_in_count;

        public BigDecimal goods_out_count;

        public String remark;

        public Collection<Sku> details_list;
    }


    public static class Sku {

        public String goods_no;

        public String spec_no;

        public String from_position;

        public String to_position;

        public BigDecimal num;

        public BigDecimal out_num;

        public BigDecimal in_num;

        public String remark;
    }
}
