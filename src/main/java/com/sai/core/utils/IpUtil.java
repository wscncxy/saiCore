package com.sai.core.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class IpUtil {

	public static final String SERVER_LOCAL = "local";
	public static final String SERVER_TEST = "test";
	public static final String SERVER_REMOTE = "remote";
	public static volatile String localip = null;// 本地IP，如果没有配置外网IP则返回它

	public static String getServerType() {
		String ip = getServerIp();
		if (StringUtil.startsWith(ip, "192.168.3")) {
			return SERVER_TEST;
		} else if (StringUtil.startsWith(ip, "192.168.10")) {
			return SERVER_REMOTE;
		}
		return SERVER_LOCAL;
	}

	public static String getServerIp() {
		if (StringUtil.isBlank(localip)) {
			try {
				String os = System.getProperty("os.name");
				if (os.toLowerCase().startsWith("win")) {
					InetAddress addr = InetAddress.getLocalHost();
					localip = addr.getHostAddress().toString();
				} else {
					Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
					InetAddress ip = null;
					boolean finded = false;// 是否找到外网IP
					while (netInterfaces.hasMoreElements() && !finded) {
						NetworkInterface ni = (NetworkInterface) netInterfaces.nextElement();
						Enumeration address = ni.getInetAddresses();
						while (address.hasMoreElements()) {
							ip = (InetAddress) address.nextElement();
							if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
									&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
								localip = ip.getHostAddress();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("当前IP：" + localip);
		}
		return localip;
	}
}
