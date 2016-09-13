package cn.zjc.util;

import org.junit.Test;

public class SqlFormatUtils {
    public static String sql = "select agencyauto0_.agency_auto_invalidate_rule_id as agency1_0_, agencyauto0_.agency_id as agency2_0_, agencyauto0_.airline as airline0_, agencyauto0_.seat_codes as seat4_0_, agencyauto0_.seat_level as seat5_0_, agencyauto0_.invalidate_rule_type as invalidate6_0_, agencyauto0_.invalidate_rule_status as invalidate7_0_, agencyauto0_.limit_condition as limit8_0_, agencyauto0_.gmt_started as gmt9_0_, agencyauto0_.gmt_finished as gmt10_0_, agencyauto0_.except_gmt_started as except11_0_, agencyauto0_.except_gmt_finished as except12_0_, agencyauto0_.not_onduty_gmt_started as not13_0_, agencyauto0_.not_onduty_gmt_finished as not14_0_, agencyauto0_.creator_cn as creator15_0_, agencyauto0_.gmt_created as gmt16_0_, agencyauto0_.modifier_cn as modifier17_0_, agencyauto0_.gmt_modified as gmt18_0_, agencyauto0_.b2b_airline_account_id as b19_0_, agencyauto0_.b2b_airline_account as b20_0_ from agency_auto_invalidate_rule agencyauto0_ where agencyauto0_.agency_id=17";

    @Test
    public void format() {
        String formatSql = sql.replaceAll("\\,", ",\n").replaceAll("select ", "select\n ")
                .replaceAll(" from ", "\nfrom ").replaceAll(" where ", "\nwhere ").replaceAll(" and ", "\n and ")
                .replaceAll(" group by ", "\ngroup by ").replaceAll(" order by ", "\norder by ");

        System.out.println(formatSql);
    }
}
