package EventbriteAPI.Models;
import java.util.HashSet;
import java.util.Set;

public class Search {

    //The address of the location you want to search for events around.
    public String locationAddress;
    //The distance you want to search around the given location. This should be an integer followed by "mi" or "km".
    public String locationWithin;
    //Only return events with start dates within the given keyword date range.
    // Keyword options are "this_week", "next_week", "this_weekend", "next_month", "this_month", "tomorrow", "today"
    public String rangeStartKeyWord;
    //Only return events under the given category IDs. This should be a comma delimited string of category IDs.
    public String categories;
    //Parameter you want to sort by - options are "date", "distance" and "best".
    // Prefix with a hyphen to reverse the order, e.g. "-date".
    public String sortBy;
    //Only return events with start dates after the given date.
    //FORMAT 2018-04-20T19:00:00
    public String rangeStart;
    //The latitude of of the location you want to search for events around.
    public String locationLatitude;
    public String locationLongitude;
    public Set<String> categoriesSet = new HashSet<>();
    public Set<String> rangeStartKeyWordSet = new HashSet<>();
    public Set<String> sortBySet = new HashSet<>();
    public boolean hasParameters;

    public Search(String locationAddress, String locationWithin, String rangeStart, String rangeStartKeyWord, String categories, String sortBy) {
        this.locationAddress = locationAddress;
        this.locationWithin = locationWithin;
        this.rangeStart = rangeStart;
        fillSortBy();
        fillCategories();
        fillrangeStartKeyWord();
        if(sortBySet.contains(sortBy)) {
            this.sortBy = sortBy;
        }
        if(categoriesSet.contains(categories)) {
            this.categories = categories;
        }
        if(rangeStartKeyWordSet.contains(rangeStartKeyWord)) {
            this.rangeStartKeyWord = rangeStartKeyWord;
        }
    }

    public Search() {
        fillrangeStartKeyWord();
        fillSortBy();
        fillCategories();
        locationAddress="null";
        locationWithin="null";
        rangeStartKeyWord="null";
        categories="null";
        sortBy="null";
        rangeStart="null";
        hasParameters=false;
        locationLongitude="null";
        locationLatitude="null";
    }

    public void setSortBy(String sortBy) {
        if(sortBySet.contains(sortBy)) {
            this.sortBy = sortBy;
        }
        else this.sortBy = "best";
        hasParameters = true;
    }

    public void fillSortBy(){
        sortBySet.add("date");
        sortBySet.add("distance");
        sortBySet.add("best");
    }

    public void fillCategories(){
        for(int i=101; i<=120; i++) {
            categoriesSet.add(String.valueOf(i));
        }
        categoriesSet.add("199");
    }

    public void fillrangeStartKeyWord(){
        rangeStartKeyWordSet.add("this_week");
        rangeStartKeyWordSet.add("next_week");
        rangeStartKeyWordSet.add("this_weekend");
        rangeStartKeyWordSet.add("next_month");
        rangeStartKeyWordSet.add("this_month");
        rangeStartKeyWordSet.add("tomorrow");
        rangeStartKeyWordSet.add("today");
    }

    public Set<String> getSortBySet() {
        return sortBySet;
    }

    public void setSortBySet(Set<String> sortBySet) {
        this.sortBySet = sortBySet;
    }

    public Set<String> getCategoriesSet() {
        return categoriesSet;
    }

    public void setCategoriesSet(Set<String> categoriesSet) {
        this.categoriesSet = categoriesSet;
    }

    public Set<String> getRangeStartKeyWordSet() {
        return rangeStartKeyWordSet;
    }

    public void setRangeStartKeyWordSet(Set<String> rangeStartKeyWordSet) {
        this.rangeStartKeyWordSet = rangeStartKeyWordSet;
    }

    public String getRangeStartKeyWord() {
        return rangeStartKeyWord;
    }

    public void setRangeStartKeyWord(String rangeStartKeyWord) {
        if(rangeStartKeyWordSet.contains(rangeStartKeyWord)) {
            this.rangeStartKeyWord = rangeStartKeyWord;
            hasParameters = true;
        }else{
            this.rangeStartKeyWord = "null";
        }
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        if(categories.contains(categories)) {
            this.categories = categories;
            hasParameters = true;
        }else {
            this.categories = "null";
        }
    }

    public String getSortBy() {
        return sortBy;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
        hasParameters = true;
    }

    public String getLocationWithin() {
        return locationWithin;
    }

    public void setLocationWithin(String locationWithin) {
        this.locationWithin = locationWithin;
        hasParameters = true;
    }

    public String getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(String rangeStart) {
        this.rangeStart = rangeStart;
        hasParameters = true;
    }
    public boolean isHasParameters() {
        return hasParameters;
    }

    public void setHasParameters(boolean hasParameters) {
        this.hasParameters = hasParameters;
    }

    public String getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(String locationLatitude) {
        this.locationLatitude = locationLatitude;
        hasParameters = true;
    }

    public String getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(String locationLongitude) {
        this.locationLongitude = locationLongitude;
        hasParameters = true;
    }
}
