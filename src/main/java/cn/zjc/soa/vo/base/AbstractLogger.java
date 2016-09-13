package cn.zjc.soa.vo.base;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

public class AbstractLogger {

    private static Map<Class<?>, org.slf4j.Logger> loggerMap = new HashMap<Class<?>, org.slf4j.Logger>();

    private org.slf4j.Logger logger;

    private String sign;

    private String systemId;

    private long serialNumber;

    private String businessType;

    private String businessSubType;

    private String returnCode;

    private long takeTime;

    private String clientCode;

    private String userCode;

    private String details;

    public AbstractLogger(Class<?> c) {
        org.slf4j.Logger loggerFromMap = loggerMap.get(c);
        if (null == loggerFromMap) {
            logger = LoggerFactory.getLogger(c);
            loggerMap.put(c, logger);
        } else {
            this.logger = loggerFromMap;
        }
        sign = AbstractContants.SIGN;
    }

    public void info(String infoStr) {
        this.details = infoStr.trim();
        logger.info(this.sign + AbstractContants.SEPARATOR + this.systemId + AbstractContants.SEPARATOR
                + this.serialNumber + AbstractContants.SEPARATOR + this.businessType + AbstractContants.SEPARATOR
                + this.businessSubType + AbstractContants.SEPARATOR + this.returnCode + AbstractContants.SEPARATOR
                + this.takeTime + AbstractContants.SEPARATOR + this.clientCode + AbstractContants.SEPARATOR
                + this.userCode + AbstractContants.SEPARATOR + this.details);
    }

    public void warn(String infoStr) {
        this.details = infoStr.trim();
        logger.info(this.sign + AbstractContants.SEPARATOR + this.systemId + AbstractContants.SEPARATOR
                + this.serialNumber + AbstractContants.SEPARATOR + this.businessType + AbstractContants.SEPARATOR
                + this.businessSubType + AbstractContants.SEPARATOR + this.returnCode + AbstractContants.SEPARATOR
                + this.takeTime + AbstractContants.SEPARATOR + this.clientCode + AbstractContants.SEPARATOR
                + this.userCode + AbstractContants.SEPARATOR + this.details);
    }

    public void error(String errorStr, Exception ex) {
        this.details = errorStr.trim();
        logger.error(this.sign + AbstractContants.SEPARATOR + this.systemId + AbstractContants.SEPARATOR
                + this.serialNumber + AbstractContants.SEPARATOR + this.businessType + AbstractContants.SEPARATOR
                + this.businessSubType + AbstractContants.SEPARATOR + this.returnCode + AbstractContants.SEPARATOR
                + this.takeTime + AbstractContants.SEPARATOR + this.clientCode + AbstractContants.SEPARATOR
                + this.userCode + AbstractContants.SEPARATOR + this.details, ex);
    }

    public void error(String errorStr) {
        this.details = errorStr.trim();
        logger.error(this.sign + AbstractContants.SEPARATOR + this.systemId + AbstractContants.SEPARATOR
                + this.serialNumber + AbstractContants.SEPARATOR + this.businessType + AbstractContants.SEPARATOR
                + this.businessSubType + AbstractContants.SEPARATOR + this.returnCode + AbstractContants.SEPARATOR
                + this.takeTime + AbstractContants.SEPARATOR + this.clientCode + AbstractContants.SEPARATOR
                + this.userCode + AbstractContants.SEPARATOR + this.details);
    }

    public void debug(String debugStr) {
        this.details = debugStr.trim();
        logger.debug(this.sign + AbstractContants.SEPARATOR + this.systemId + AbstractContants.SEPARATOR
                + this.serialNumber + AbstractContants.SEPARATOR + this.businessType + AbstractContants.SEPARATOR
                + this.businessSubType + AbstractContants.SEPARATOR + this.returnCode + AbstractContants.SEPARATOR
                + this.takeTime + AbstractContants.SEPARATOR + this.clientCode + AbstractContants.SEPARATOR
                + this.userCode + AbstractContants.SEPARATOR + this.details);
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessSubType() {
        return businessSubType;
    }

    public void setBusinessSubType(String businessSubType) {
        this.businessSubType = businessSubType;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public long getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(long takeTime) {
        this.takeTime = takeTime;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

}
