package TCP;

import com.google.gson.Gson;

public class Response {
private String responseMessage;
private ResponseType responseType;

    public Response(ResponseType responseType, String responseMessage) {
        this.responseType = responseType;
        this.responseMessage = responseMessage;
    }

    public Response() {
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }
}
