import java.net.*;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Example of making a GET request to the HouseCanary API using the Apache HTTP
 * library.
 */
public class HCApiGetExample {

	public static void main(final String[] args) {
		try {
			String host = "api.housecanary.com";

			// build up the URI with query params
			URI uri = new URIBuilder().setScheme("https").setHost(host).setPath("/v2/property/value")
					.setParameter("address", "43 Valmonte Plaza").setParameter("zipcode", "90274").build();

			HttpGet httpGet = new HttpGet(uri);

			String auth = "test_0TAQAK5ESOFQ1ZVLQO9M" + ":" + "rTecWozVb3dGkWkdUX7ElAgTFWqvWDHk";
			byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
			String authHeader = "Basic " + new String(encodedAuth);
			httpGet.addHeader(HttpHeaders.AUTHORIZATION, authHeader);

			CloseableHttpClient httpClient = HttpClients.createDefault();

			CloseableHttpResponse response = httpClient.execute(httpGet);

			try {
				System.out.println(response.getStatusLine());

				HttpEntity entity = response.getEntity();
				System.out.println(EntityUtils.toString(entity));

				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
			return;

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}