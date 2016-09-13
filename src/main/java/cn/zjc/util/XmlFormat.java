package cn.zjc.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XmlFormat {

    public static String xmlPrettyFormat(String input) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StreamResult xmlOutput = new StreamResult(new StringWriter());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "yes");
            // transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            return "xml格式化出错，可能是xml格式不对，错误信息：" + e.getMessage();
        }
    }

    public static String xmlNoFormat(String input) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StreamResult xmlOutput = new StreamResult(new StringWriter());

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "false");
            // transformer.setOutputProperty(OutputKeys.MEDIA_TYPE, "yes");
            // transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            return "xml格式化出错，可能是xml格式不对，错误信息：" + e.getMessage();
        }
    }

    public static void main(String[] args) {
        String xmlReq = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><request><airwayRebate></airwayRebate><asmsAutoEtdz></asmsAutoEtdz><bigPnrNo>NL80FY</bigPnrNo><businessNo>ZHAJIE</businessNo><buyAmount>null</buyAmount><buyBusinessNo>swordflys@gmail.com</buyBusinessNo><buyPayment>易商旅</buyPayment><cardId>222324196005250912</cardId><cardType>NI</cardType><deductionAmount></deductionAmount><discount>0.0080</discount><etdzOrderNo></etdzOrderNo><etdzScn>1711.29</etdzScn><flightNo>CA4186</flightNo><fromDatetime>2014-05-22 12:20</fromDatetime><gaiqianRetirement>按客规</gaiqianRetirement><ifAutoEtdz>1</ifAutoEtdz><ifChangeOrder>0</ifChangeOrder><ifChangePnr>0</ifChangePnr><newBigPnrNo></newBigPnrNo><newPnrNO></newPnrNO><orderNo>112014052040153155</orderNo><orderStatus>161</orderStatus><passenger>侯耀天</passenger><passengerMobile></passengerMobile><passengerType>1</passengerType><payment>财付通</payment><paymentTransaction>1205005501201405200601330208</paymentTransaction><planeType>null</planeType><platformFee>6.87</platformFee><pnrNo>JQEGN0</pnrNo><policyId>43400635</policyId><productType>1</productType><realFlightNo></realFlightNo><rebate>0.056</rebate><remark></remark><saleAmount>1711.29</saleAmount><scny>1640.0</scny><seatClass>H</seatClass><subSeatClass></subSeatClass><tax>120.0</tax><terminal></terminal><ticketNo>999-2340181919</ticketNo><ticketType></ticketType><toDatetime>2014-05-22 15:55</toDatetime><tofromterminal></tofromterminal><travelRange>SHECTU</travelRange><travelType>1</travelType><yq>50.0</yq></request>";
        String format = xmlPrettyFormat(xmlReq);
        System.out.println(format);
    }

}
