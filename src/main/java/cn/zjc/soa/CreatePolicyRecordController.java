package cn.zjc.soa;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zjc.soa.vo.reply.CreateNormalPolicyUsingMd5Reply;
import cn.zjc.soa.vo.reply.CreateNormalPolicyUsingMd5Vo;
import cn.zjc.soa.vo.request.CreateNormalPolicyUsingMd5Request;

@RequestMapping("/createPolicyRecord")
public class CreatePolicyRecordController extends AbstractServer {
    private static final Logger logger = LoggerFactory.getLogger(CreatePolicyRecordController.class);

    @RequestMapping(value = "/createPolicyRecord/createNormalPolicyUsingMd5", method = RequestMethod.POST)
    public void createNormalPolicyUsingMd5(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long serialNumber = 0;
        try {
            CreateNormalPolicyUsingMd5Request req = getPolicyRequestFromRequest(request,
                    CreateNormalPolicyUsingMd5Request.class);
            serialNumber = req.getSerialNumber();
            CreateNormalPolicyUsingMd5Vo vo = null;
            CreateNormalPolicyUsingMd5Reply reply = new CreateNormalPolicyUsingMd5Reply();
            reply.setCreateNormalPolicyUsingMd5Vo(vo);
            reply.setReturnCode("SYS_SUCCESS");
            returnZipResult(response, reply);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("4SerialNumber:" + serialNumber + " json=" + this.getJsonFromRequest(request) + "当前时间："
                    + System.currentTimeMillis() + "exception:" + ExceptionUtils.getStackTrace(e));
            CreateNormalPolicyUsingMd5Reply reply = new CreateNormalPolicyUsingMd5Reply();
            returnZipResult(response, reply);
        }
    }
}
