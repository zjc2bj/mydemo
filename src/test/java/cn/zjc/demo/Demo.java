package cn.zjc.demo;

import org.junit.Test;

public class Demo {
    public static int status = 0;

    public static void main(String[] args) {
        // if (Integer.parseInt("0") == +status) {
        // System.out.println("true");
        // }
        while (true) {
            System.out.println("........");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private static void test2() {
        String str1 = "<patcontent><![CDATA[ PAT:A 01 fd00 FARE:CNY1700.00 TAX:CNY50.00 YQ:CNY120.00 TOTAL:1870.00 SFC:01 ]]></patcontent>";
        String str2 = "<patcontent><![CDATA[ PAT:A 01 fd00 FARE:CNY1700.00 TAX:CNY50.00 YQ:CNY120.00 TOTAL:1870.00 SFC:01 ]]></patcontent>";

        String str3 = "xmlParam=<?xml version=\"1.0\" encoding=\"utf-8\"?><request><service></service><pid>0008600281211222769</pid><username>motb</username><sign></sign><params><pnrcontent><![CDATA[ 1.张文静 JQF4ZT                                                               2.  CA1351 Y   SU29JUN  PEKCAN HK1   0805 1115          E T3--                  3.CTU/T CTU/T 028-87033444/LONG XIANG TOUR SERVICE CO. LTD/TIAN ZHENG ABCDEFG   4.TL/0605/29JUN/CTU212                                                          5.SSR FOID CA HK1 NI41272519860224523/P1                                        6.SSR ADTK 1E BY CTU08NOV13/2103 OR CXL CA ALL SEGS                             7.OSI CA CTCT13693602415                                                        8.RMK CA/MX58TX                                                                 9.CTU212                                                                                                                                                        ]]></pnrcontent>";
        String str4 = "xmlParam=<?xml version=\"1.0\" encoding=\"utf-8\"?><request><service></service><pid>0008600281211222769</pid><username>motb</username><sign></sign><params><pnrcontent><![CDATA[ 1.张文静 JQF4ZT                                                               2.  CA1351 Y   SU29JUN  PEKCAN HK1   0805 1115          E T3--                  3.CTU/T CTU/T 028-87033444/LONG XIANG TOUR SERVICE CO. LTD/TIAN ZHENG ABCDEFG   4.TL/0605/29JUN/CTU212                                                          5.SSR FOID CA HK1 NI41272519860224523/P1                                        6.SSR ADTK 1E BY CTU08NOV13/2103 OR CXL CA ALL SEGS                             7.OSI CA CTCT13693602415                                                        8.RMK CA/MX58TX                                                                 9.CTU212                                                                                                                                                        ]]></pnrcontent>";
        System.out.println(str1.equals(str2));
        System.out.println(str3.equals(str4));
    }

    @Test
    public void excute() {
        String str = "<pnrcontent><![CDATA[1.陈静 HGCRC6                                                                  2.  CA153  Y   WE30APR  PEKDLC HK1   0840 0940          E T3--                 3.CTU/T CTU/T 028-87033444/LONG XIANG TOUR SERVICE CO. LTD/TIAN ZHENG ABCDEFG  4.TL/0000/30APR/CTU212                                                         5.SSR FOID CA HK1 NI130922198505010001/P1                                      6.SSR ADTK 1E BY CTU28MAR14/1702 OR CXL CA ALL SEGS                            7.OSI CA CTCT13000000000                                                       8.RMK TJ AUTH PEK470                                                           9.RMK CA/NTD4P6                                                               10.RMK TLWBINSD                                                                11.CTU212]]></pnrcontent><bigpnr>NTD4P6</bigpnr><benefitid>1606713</benefitid><linker>jyc</linker><linktel>13000000000</linktel><splitbenefitid></splitbenefitid><patcontent><![CDATA[PAT:A 01 fd00 FARE:CNY710.00 TAX:CNY50.00 YQ:CNY70.00 TOTAL:830.00 SFC:01]]></patcontent>motb96E79218965EB72C92A549DD5A3301122014-03-14ba82f3e3011d44c69f35e0850b7941260008600281211222769";
        String getMD5 = MD5Encryption.GetMD5(str);
        System.out.println(getMD5);
    }

}
