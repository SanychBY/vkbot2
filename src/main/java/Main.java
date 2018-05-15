import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.GroupAuthResponse;
/**
 * Created by ssaan on 15.05.2018.
 */
public class Main {
    public static void main(String[] args) {
        int APP_ID = 6474972;
        String code = "4eee23243e2c37dddb";
        String CLIENT_SECRET = "PNkYiS79LdyoYMeGGBAV";
        String REDIRECT_URI = "localhost:8080/VKVerify";
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        int groupId = 159158661;
        GroupAuthResponse authResponse = null;
        try {
            authResponse = vk.oauth()
                    .groupAuthorizationCodeFlow(APP_ID, CLIENT_SECRET, REDIRECT_URI, code)
                    .execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        GroupActor actor = new GroupActor(groupId, authResponse.getAccessTokens().get(groupId));
        vk.messages().send(actor).message("TEST 112");
        System.out.println("end!");
    }
}
