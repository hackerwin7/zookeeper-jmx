import org.apache.zookeeper.server.DataTreeMXBean;

import javax.management.AttributeChangeNotification;
import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.Notification;
import javax.management.NotificationListener;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by hp on 15-1-22.
 */
public class SimpleJMXClient {

    private static String host = "";
    private static int port = 9999;

    //notification class
    public static class ClientListener implements NotificationListener {
        public void handleNotification(Notification noti, Object handback) {
            print("received a notification:");
            print("\tclass name:" + noti.getClass().getName());
            print("\tsource:" + noti.getSource());
            print("\ttype:" + noti.getType());
            print("\tmessage:" + noti.getMessage());
            if(noti instanceof AttributeChangeNotification) {
                AttributeChangeNotification acn = (AttributeChangeNotification) noti;
                print("\tattribute name:" + acn.getAttributeName());
                print("\tattribute type:" + acn.getAttributeType());
                print("\tnew value:" + acn.getNewValue());
                print("\told value:" + acn.getOldValue());
            }
        }
    }

    //client connect to server
    public static void main(String[] args) throws Exception {
        //jmx connection
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi");
        JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
        //jmx listener
        ClientListener listener = new ClientListener();
        //get MBeanServer by jmx connection
        MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

        //get data from MBeanServer
        String domains[] = mbsc.getDomains();
        Arrays.sort(domains);
        for(String domain : domains) {
            print("\tDomain = " + domain);
        }

        print("\tdefault domain = " + mbsc.getDefaultDomain());

        print("\tMBean count = " + mbsc.getMBeanCount());

        Set<ObjectName> names = new TreeSet<ObjectName>(mbsc.queryNames(null, null));
        for(ObjectName name : names) {
            print("\tobject name = " + name);
        }

        ObjectName mbeanName = new ObjectName("org.apache.ZooKeeperService:name0=StandaloneServer_port-1,name1=InMemoryDataTree");
        DataTreeMXBean mbeanProxy = JMX.newMBeanProxy(mbsc, mbeanName, DataTreeMXBean.class, true);

        //add listener to MBeanServerConnection
        //mbsc.addNotificationListener(mbeanName, listener, null, null);

        print("\tnum connections = " + mbeanProxy.getLastZxid());
        print("\tpackage send = " + mbeanProxy.getNodeCount());

        jmxc.close();
    }

    private static void print(String str) {
        System.out.println(str);
    }

}
