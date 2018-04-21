package Database.Tasks;


import POJO.User;

public interface AsyncResponse {
    void processFinish(User response, String password);
}
