package work.gaigeshen.tripartite.wangdian.openapi.response.warehouse;

import work.gaigeshen.tripartite.wangdian.openapi.response.AbstractWangdianResponse;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author gaigeshen
 */
public class WarehouseQueryResponse extends AbstractWangdianResponse {

    public Integer total_count;

    public Collection<Warehouse> warehouses;


    public static class Warehouse {

        public Integer warehouse_type;

        public String warehouse_no;

        public String name;

        public String province;

        public String city;

        public String district;

        public String address;

        public String contact;

        public String mobile;

        public Integer is_defect;

        public String remark;

        public BigDecimal coordinates_x;

        public BigDecimal coordinates_y;

        public Integer warehouse_id;

        public Integer match_warehouse_id;

        public String is_disabled;

        public String created;

        public String modified;
    }

}
