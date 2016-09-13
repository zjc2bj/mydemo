package cn.zjc.jmx.remote;

import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class JmxService {
    private static final String C3P0_REGISTRY_OBJNAME = "com.mchange.v2.c3p0:type=C3P0Registry";

    private static final String C3P0_REGISTRY_OPERNAME = "getPooledDataSourcesIdentity";

    private String username = "card_monitor";

    private String password = "card_monitor";

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // sign 参数类型的说明
    public Object invokeMBeanMethod(String url, String operName, Object[] params, String[] signs) throws Exception {
        JMXConnector connector = null;
        try {
            JMXServiceURL serviceURL = new JMXServiceURL(url);
            if (username == null || username.length() == 0) {
                // 不需要权限认证的连接器
                connector = JMXConnectorFactory.connect(serviceURL);
            } else {
                Map<String, Object> environment = new HashMap<String, Object>();
                environment.put(JMXConnector.CREDENTIALS, new String[] { username, password });
                connector = JMXConnectorFactory.connect(serviceURL, environment);
            }
            // 得到MBean服务连接
            MBeanServerConnection connection = connector.getMBeanServerConnection();
            // 调用JMX功能方法,得到返回值
            Object pdsId = connection.invoke(new ObjectName(C3P0_REGISTRY_OBJNAME), C3P0_REGISTRY_OPERNAME,
                    new String[] { null }, new String[] { "java.lang.String" });
            String objName = "com.mchange.v2.c3p0:type=PooledDataSource[" + pdsId + "]";
            Object res = connection.invoke(new ObjectName(objName), operName, params, signs);
            return res;
        } finally {
            if (connector != null)
                connector.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "service:jmx:rmi:///jndi/rmi://127.0.0.1:9083/jmxrmi";
        String operName = "getMaxPoolSize";
        // String operName = "allIdentityTokens";
        Object[] params = null;
        String[] signs = null;

        JmxService jmxService = new JmxService();
        Object res = jmxService.invokeMBeanMethod(url, operName, params, signs);
        System.out.println(res);
    }
}
