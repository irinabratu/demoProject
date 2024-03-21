package rest.spotifyApi.test;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import rest.spotifyApi.api.applicationApi.PlaylistApi;
import rest.spotifyApi.pojo.Error;
import rest.spotifyApi.pojo.Playlist;
import rest.spotifyApi.utils.DataLoader;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("Playlist API")
public class PlaylistTest {

    @Description("description here")
    @Test (description="Should be able to create a playlist")
    public void ShouldBeAbleToCreatePlaylist(){
        Playlist requestPlaylist = playlistBuilder("New Playlist 2","Description",false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), 201);
        Playlist responsePlaylist = response.as(Playlist.class);
        assertPlaylistEqual(requestPlaylist,responsePlaylist);
    }

    @Link("https://developer.spotify.com/")
    @Link(name = "allure", type ="mylink")
    @TmsLink("12345")
    @Issue("1234567")
    @Test
    public void ShouldBeAbleToGetPlaylist(){
        Playlist requestPlaylist = playlistBuilder("New Playlist 2","Description",true);
        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        Playlist responsePlaylist = response.as(Playlist.class);
        assertStatusCode(response.statusCode(), 200);
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Story("Story SM-2828")
    @Test
    public void ShouldBeAbleToUpdatePlaylist(){
        Playlist requestPlaylist = playlistBuilder("Updated Playlist 2","Updated Description",true);
        Response response = PlaylistApi.update(requestPlaylist, DataLoader.getInstance().updatePlaylistId());
        assertStatusCode(response.statusCode(), 200);
    }

    @Test
    public void ShouldNotBeAbleToCreatePlaylistWithoutName(){
        Playlist requestPlaylist = playlistBuilder(null,"Description",false);
        Response response = PlaylistApi.post(requestPlaylist);
        assertStatusCode(response.statusCode(), 400);
        Error error = response.as(Error.class);
        assertError(error, 400, "Missing required field: name");

    }

    @Test
    public void ShouldNotBeAbleToCreatePlaylistWithExpiredToken(){
        String invalidToken = "12345";
        Playlist requestPlaylist = playlistBuilder("New Playlist 2","Description",true);
        Response response = PlaylistApi.post(invalidToken, requestPlaylist);
        assertStatusCode(response.statusCode(), 401);
        Error error = response.as(Error.class);
        assertError(error, 401, "Invalid access token");
    }

    @Step
    public Playlist playlistBuilder(String name, String description, boolean _public) {
        return new Playlist().setName(name).setDescription(description).setPublic(_public);
    }

    @Step
    public void assertPlaylistEqual(Playlist requestPlaylist, Playlist responsePlaylist) {
        assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlaylist.getPublic(), equalTo(requestPlaylist.getPublic()));
    }

    @Step
    public void assertStatusCode(int actualStatusCode, int expectedStatusCode) {
        assertThat(actualStatusCode, equalTo(expectedStatusCode));
    }
    @Step
    public void assertError(Error responseErr, int expectedStatusCode, String expectedMessage){
        assertThat(responseErr.getError().getStatus(), equalTo(expectedStatusCode));
        assertThat(responseErr.getError().getMessage(), equalTo(expectedMessage));
    }
}
