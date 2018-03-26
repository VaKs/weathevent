package EventbriteAPI.service;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public interface EventbriteServiceCallback {
    void eventbriteServiceSuccess(Object object);
    void eventbriteServiceFailure(Exception exception);
}
