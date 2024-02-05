package hmy.webapp.utils;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Response {

    private boolean flag;
    private Object data;
    private String error;

//    public Response(){}

    public Response(boolean flag) {
        this.flag = flag;
    }

    public Response(boolean flag, Object data) {
        this.flag = flag;
        this.data = data;
    }

    public Response(boolean flag, String errorMsg) {
        this.flag = flag;
        this.error = errorMsg;
    }

    @Override
    public String toString() {
        return "{" +
                    "\"flag\": " + flag + ", " +
                    "\"data\": " +  data + ", " +
                    "\"error\": " + (error == null ? null : "\"" + error + "\"") +
                "}";
    }
}
