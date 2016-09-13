package cn.zjc.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OutOfMemoryDemo {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(OutOfMemoryDemo.class);
        StringBuffer buffer = new StringBuffer();

        String rsp = "<Response><Status>0</Status><PolicySyncs><PolicyRes><air_com_dcd>3U</air_com_dcd><create_date>2015-05-1316:00:53</create_date><discard_desc>退票，跨月改期收回代理费;提供的行程单类型：支持证件类型：所有证件;</discard_desc><earliest_ticket_days></earliest_ticket_days><flight_no>8738/8734/8736/8793/8742/8746</flight_no><fly_type_did>1</fly_type_did><from_city_dcd>CAN</from_city_dcd><invalid_work_time>00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59</invalid_work_time><is_allow_exchange>Y</is_allow_exchange><is_allow_view>Y</is_allow_view><is_auto_ticket>Y</is_auto_ticket><is_chg_pnr>N</is_chg_pnr><is_mon_policy>N</is_mon_policy><is_national>N</is_national><is_shared>N</is_shared><is_support_inf>N</is_support_inf><is_team>N</is_team><is_valid>Y</is_valid><latest_ticket_days></latest_ticket_days><match_cabin>Y/T/H/M/G/S/L/Q/E/V/R/N</match_cabin><match_flight_type_did>1</match_flight_type_did><match_rtn_flight_type_did></match_rtn_flight_type_did><optr_date>2015-05-1919:10:43</optr_date><optr_stat_dcd>U</optr_stat_dcd><policy_id>2377369</policy_id><policy_type_did>1</policy_type_did><psgr_multi_type_did>1</psgr_multi_type_did><rebate_cashback></rebate_cashback><rebate_point>4.2</rebate_point><receipt_type_did>1</receipt_type_did><rtn_mon_rebate_value></rtn_mon_rebate_value><rtn_rebate_point></rtn_rebate_point><sale_end_date>2015-08-31</sale_end_date><sale_start_date>2015-05-13</sale_start_date><support_cert_type_did>2</support_cert_type_did><suspend_stat_did>3</suspend_stat_did><ticket_office_no>CAN830</ticket_office_no><ticket_type_codes>BSP</ticket_type_codes><to_city_dcd>CTU/CKG</to_city_dcd><travel_end_date>2015-08-31</travel_end_date><travel_start_date>2015-07-01</travel_start_date><weekday_limit>1/2/3/4/5/6/7</weekday_limit><work_time>00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59,00:00-23:59</work_time></PolicyRes></PolicySyncs></Response>";
        for (int i = 0; i < 1000000; i++) {
            buffer.append(rsp);
            // rsp += rsp;
        }
        logger.info("全取参数XML" + buffer);
    }
}
