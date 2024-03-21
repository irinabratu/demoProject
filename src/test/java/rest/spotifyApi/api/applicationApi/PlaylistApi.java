package rest.spotifyApi.api.applicationApi;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import rest.spotifyApi.api.RestResource;
import rest.spotifyApi.pojo.Playlist;
import rest.spotifyApi.utils.ConfigLoader;

import static rest.spotifyApi.api.Route.PLAYLISTS;
import static rest.spotifyApi.api.Route.USERS;
import static rest.spotifyApi.api.TokenManager.getToken;

public class PlaylistApi {

    @Step
    public static Response post(Playlist playlistRequest) {
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), playlistRequest);
    }

    public static Response post(String token, Playlist playlistRequest) {
        System.out.println("inside");
        return RestResource.post(USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS,token, playlistRequest);
    }

    public static Response get(String playlistId) {
        return RestResource.get(PLAYLISTS + "/" + playlistId, getToken());
    }

    public static Response update(Playlist playlistRequest, String playlistId) {
        return RestResource.update(PLAYLISTS + "/" + playlistId, getToken(), playlistRequest);
    }
}
