package EventbriteAPI.Models;

import org.json.JSONObject;

/**
 * Created by Agnieszka on 25.03.2018.
 */

public class Pagination implements JSONPopulator {
    public int ObjectCount;
    public int PageNumber;
    public int PageSize;
    public int PageCount;
    public String Continuation;
    public Boolean HasMoreItems;

    @Override
    public void populate(JSONObject data) {
        ObjectCount = data.optInt("url");
        PageCount = data.optInt("page_count");
        PageSize = data.optInt("page_size");
        PageNumber = data.optInt("page_number");
        Continuation = data.optString("continuation");
        HasMoreItems = data.optBoolean("has_more_items");
    }

    public int getPageCount() {
        return PageCount;
    }

    public void setPageCount(int pageCount) {
        PageCount = pageCount;
    }

    public int getPageSize() {
        return PageSize;
    }

    public void setPageSize(int pageSize) {
        PageSize = pageSize;
    }

    public int getPageNumber() {
        return PageNumber;
    }

    public void setPageNumber(int pageNumber) {
        PageNumber = pageNumber;
    }

    public int getObjectCount() {
        return ObjectCount;
    }

    public void setObjectCount(int objectCount) {
        ObjectCount = objectCount;
    }

    public String getContinuation() {
        return Continuation;
    }

    public void setContinuation(String continuation) {
        Continuation = continuation;
    }

    public Boolean getHasMoreItems() {
        return HasMoreItems;
    }

    public void setHasMoreItems(Boolean hasMoreItems) {
        HasMoreItems = hasMoreItems;
    }
}
