package com.truly.alipayDemo.config;

import java.io.FileWriter;
import java.io.IOException;

public class AliPayConfig {

		//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
		public static String app_id = "2021000121626939";

		// 商户私钥，您的PKCS8格式RSA2私钥
		public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC2FxXZUL1QTn2Nh5CEshygNXlGm646t3rDUUwr5dpaKqvtyxqj+hKcBgSROeIQ6BffovEx68HQW+aNv96vRM3SkFeb2sbfKw+dYU1TJm8+jnkHXt8y0RyM+9gYIe71p0YZRWMP9VOGxkK0g1hDLgCJKNHrnvJPtK+LYM7v8x3fy5sS9AzoTkBuP2jejliIKIqjZpemCWm4U6LBuTF+Duwc5rs77QsfTKEnX6enXs0GDoU+bFx/brXoFWqaQvzVwkMoQGncGQpRIHG1gdxol1SBdNrrUKlnkIrPaj/BacQwkIIVBoSTw/+/jQszCucSX20Yr9uNmK7ULCYWihnTvjLBAgMBAAECggEAQjAWAw40IlLFlIJoZ7OOcDW/xYilRjaXaxPMClDck9Xa9O7U2jH2Pbx6QIe3wsl6lBHYdnrp0mD1ZyUNPsxVvGo3TjGyBEDUxjIuN0FLQoPrPmCf5QrZw+1vWSZMhAKvmlP8dDqKh/zdFlx/4rt/+ltaIrl9/rcA8DraPaIzg7sTFuh+U3YXfHqteqr7eFlVMbMcKb0DeLFBhxwzwoWMwpb0uV031NGFVFCSYOv2Sx47Am68KpEURorW0PaCEXB9ewA4J8PsXgChncVu6OHUv8FnzAM5jC+GR9nVWIv67TqoH6Bqnmm2O8HXfj8xvDoWD+bc2ohQpGYJU7CxD780gQKBgQDtGvV4zHV1O0mRi2Q3oOczasrq/oUzCi4yMoK1BfWY34ims9AHEtiZJZMr1r12c3DWTJ8uuTYPYp+CZy2Ery+8kOh4mk/N7qT/AG48LvfigBL74gepiB3ZbBq0B1AZWx+RRhE9tR53LSY2DK5daw8DIhf562UWe4cRX0mt4F7iuQKBgQDEmcvXqTM5meMkk6jIo81p7iHrZ28YP+x1xznO6FHm2a/uknO0ZWsst+hlMETthq6NxiyJID2xNRDVZUFTLwjVWJaET3niHbHZrLnoanJFhRaQxBbMFOkR5aQ23pooFJdgasvQkJTZIeLrEk0mK38SUOVVRWIiwHgOfGIBupXsSQKBgHCEJpDYlbS4GcVTnI85b/3aY8j4V0VNQ3bWuWgmF9If4b0Z/1IcA2VJi3l2Qhp/1cAcRD3r7leGuaMsS1F3wPN+4JK6x7w1z16jWax9rWxy0o9tR62Pxnpy7okYH06pC2XpFi1uUbLEXimwAt6Z+1nFSBN1PVdZ71KzIUe9/ckhAoGAMz2GdGIEGNoz6l+V2R1gN2C6dHlT6jUW8yeGuJeyaQGJqht/jp/0TAZuypjHDkVRD2B8UQ0XJy2WgpfOa9jRa5GYQUnZkYZ9yScMmTfNKrNiSz7gVjaI/IImx6pC8XlUbuvGzUgxk1oSo4P0maMyjlqJOy1HQ5l4cQYYIS05UAkCgYAn/nK7HcZbHD1HSg+fyorDq5DOI39BgnM86cuK7xWWitH+/MOQqpWnspfrDtPRjyJ5oAwGPkwEneoDE7Q6aJXG9AKrNz/t1epRcY3wDIIePSiIumIGFHy7bARIsSy2q9wdrcP6HNym7YPGpabQp7nyyX03a1MWj28qwwNmq8cLmw==";

		// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
		public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0JDMXvcS1vGd+mfh8VoswIDAKNh2X5BFo8PzQA6Rvm1R3wy2GE9MIQZrJmbrPsoOMWv8v71JrdkFP6zo9or5dNwmrMDWYBuoOCm6LN6Gbv3+79kr6Ccpm8wUEQDCMyYTV54wi8pJV2vliZFupb82PlHZmMDsMGR19/cni5/jetfRGy4CKx4/q7U0Y6tw5+gR/bXREQdVq7z41Ks6QwbWGcFPJd69cDsBIlEN3kk9j7G+0GBjngpcJooC/ZR/Cp/AhCNxG2FJrhOLyDUjLaVJVf1QHvgsfrZYHYppB4dX17ATPK8TO4pMiHmpxjsJltHCZa4FdPTY93qfAZXgoWNECQIDAQAB";

		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String notify_url = "http://n7tzd2.natappfree.cc/notify";

		// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String return_url = "http://n7tzd2.natappfree.cc/return";

		// 签名方式
		public static String sign_type = "RSA2";

		// 字符编码格式
		public static String charset = "utf-8";

		// 支付宝网关
		public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

		// 支付宝网关
		public static String log_path = "/log";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

		/**
		 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
		 * @param sWord 要写入日志里的文本内容
		 */
		public static void logResult(String sWord) {
			FileWriter writer = null;
			try {
				writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
				writer.write(sWord);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					try {
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
}
