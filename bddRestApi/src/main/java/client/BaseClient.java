package client;

import apis.ApiException;
import client.model.Request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public class BaseClient<T> {

    private final String baseUrl;
    private final Client client;
    private final ObjectMapper mapper;

    public BaseClient(String baseUrl) {
        this.mapper = new ObjectMapper();
        this.baseUrl = baseUrl;
        this.client = createClient();
    }

    private Client createClient() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(MultiPartFeature.class);
        clientConfig.register(JacksonFeature.class);
        // Connect and read timeouts in milliseconds
        clientConfig.property(ClientProperties.READ_TIMEOUT, 10000);
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 10000);

        return ClientBuilder.newBuilder().withConfig(clientConfig).build();
    }


    public T doGet(Request request) throws ApiException {
        Invocation.Builder builder = buildApiRequest(request);

        Response response = builder.get();

        return getResponse(request, response);
    }

    public Response doGetAndReturnResponse(Request request) {
        Invocation.Builder builder = buildApiRequest(request);

        return builder.get();
    }

    public T doPost(Request request) throws ApiException {
        Invocation.Builder builder = buildApiRequest(request);

        Response response = builder.post(request.getPayload());

        return getResponse(request, response);
    }

    public T doPut(Request request) throws ApiException {
        Invocation.Builder builder = buildApiRequest(request);

        Response response = builder.put(request.getPayload());

        return getResponse(request, response);
    }

    public T doDelete(Request request) throws ApiException {
        Invocation.Builder builder = buildApiRequest(request);

        Response response = builder.delete();

        return getResponse(request, response);
    }

    private T getResponse(Request request, Response response) throws ApiException {
        if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            return (T) response.readEntity(request.getReturnObjectType());
        } else {
            String stringError = response.readEntity(String.class);
            throw new ApiException(response.getStatusInfo().getStatusCode(), "Error: " + stringError);
        }
    }

    private Invocation.Builder buildApiRequest(Request request) {
        URI parsedUri = URI.create(request.getPath());
        WebTarget target = client.target(this.baseUrl + parsedUri);
        target = buildParamsFromMap(target, request.getParams());
        Invocation.Builder builder = target.request(request.getMediaType());
        buildHeadersFromMap(builder, request.getHeaders());

        return builder;
    }

    private WebTarget buildParamsFromMap(WebTarget target, MultivaluedMap<String, String> params) {
        WebTarget queryTarget = target;
        if (params != null) {
            for (Map.Entry<String, List<String>> entry : params.entrySet()) {
                List<String> paramList = entry.getValue();
                String csvParamList = StringUtils.join(paramList, ',');
                queryTarget = queryTarget.queryParam(entry.getKey(), csvParamList);
            }
        }

        return queryTarget;
    }


    private void buildHeadersFromMap(Invocation.Builder builder, Map<String, String> headers) {
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                builder.header(entry.getKey(), entry.getValue());
            }
        }
    }

    public Object convertFromLinkedHashMap(Class caste, LinkedHashMap object) {
        return mapper.convertValue(object, caste);
    }

}