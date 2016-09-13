package cn.zjc.soa;

import cn.zjc.soa.vo.reply.CreateNormalPolicyUsingMd5Reply;
import cn.zjc.soa.vo.reply.FindPolicyRecordExtendReply;
import cn.zjc.soa.vo.reply.GetInsertPrAllValidReply;
import cn.zjc.soa.vo.reply.PublishPolicyByExcelImportTransferVoReply;
import cn.zjc.soa.vo.reply.UpdateUnsuspended4SystemReply;
import cn.zjc.soa.vo.request.CreateNormalPolicyUsingMd5Request;
import cn.zjc.soa.vo.request.FindPolicyRecordExtendRequest;
import cn.zjc.soa.vo.request.GetInsertPrDynamicValidRequest;
import cn.zjc.soa.vo.request.UpdateUnsuspended4SystemRequest;

public class PolicyClient extends AbstractPolicyClient {
    private static final String publish_policy_by_excel_vo = "/policy/createPolicyRecord/createByExcelVo.in";

    private static final String get_InsertPrDynamicValid = "/policy/aboutPrAllValid/getInsertPrDynamicValid.in";

    private static final String find_PolicyRecordExtend_by_gzip = "/policy/policyRecordExtendList/findPolicyRecordExtendByGZIP.in";

    private static final String create_normal_policy_using_md5 = "/policy/createPolicyRecord/createNormalPolicyUsingMd5.in";

    private static final String update_Unsuspended4System = "/policy/updatePolicyRecord/updateUnsuspended4System.in";

    /**
     * 接口中心同步政策时获取政策数据
     */
    public static GetInsertPrAllValidReply getInsertPrDynamicValid(GetInsertPrDynamicValidRequest req) {
        return getReplyFromPolicyByKryo(req, GetInsertPrAllValidReply.class, get_InsertPrDynamicValid, null, null,
                TIME_OUT_600);
    }

    /**
     * 获取policy_record_extend表list数据，GZIP
     */
    public static FindPolicyRecordExtendReply findPolicyRecordExtendByGZIP(FindPolicyRecordExtendRequest req) {
        return getReplyFromPolicyByGZIP(req, FindPolicyRecordExtendReply.class, find_PolicyRecordExtend_by_gzip, null,
                null, TIME_OUT_30);
    }

    /**
     * crm政策挂起解挂
     */
    public static UpdateUnsuspended4SystemReply updateUnsuspended4System(UpdateUnsuspended4SystemRequest req) {
        return getReplyFromPolicy(req, UpdateUnsuspended4SystemReply.class, update_Unsuspended4System, null, null,
                TIME_OUT_30);
    }

    /**
     * 创建普通政策，md5去重复
     */
    public static CreateNormalPolicyUsingMd5Reply createNormalPolicyUsingMd5(CreateNormalPolicyUsingMd5Request req) {
        return getReplyFromPolicy(req, CreateNormalPolicyUsingMd5Reply.class, create_normal_policy_using_md5, null,
                null, TIME_OUT_60);
    }

    /**
     * 批量导入创建普通政策
     */
    public static PublishPolicyByExcelImportTransferVoReply publishPolicyByExcelImportTransferVo(
            CreateNormalPolicyUsingMd5Request req) {
        return getReplyFromPolicyByStream(req, PublishPolicyByExcelImportTransferVoReply.class,
                publish_policy_by_excel_vo, (String) null, (Integer) null, TIME_OUT_600);
    }

}
