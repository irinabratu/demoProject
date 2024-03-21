
package rest.spotifyApi.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error"
})
public class Error {

    @JsonProperty("error")
    private InnerError innerError;

    @JsonProperty("error")
    public InnerError getError() {
        return innerError;
    }

    @JsonProperty("error")
    public void setError(InnerError innerError) {
        this.innerError = innerError;
    }

}
