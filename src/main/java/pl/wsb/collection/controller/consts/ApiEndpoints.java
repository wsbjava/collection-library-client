package pl.wsb.collection.controller.consts;

public class ApiEndpoints {

    public static final String MainPath ="http://127.0.0.1:8080/webapi/";
    public static final String PATH_PARAM_ID = "/{id}";
    public static final String PATH_PARAM_ABBR = "/{abbr}";
    public static final String ABBR = "/byAbbr";
    public static final String PENDING = "/pending";
    public static final String REJECT = "/reject";
    public static final String ACCEPT = "/accept";


    //Definionts of cons path
    public static final String AUTHENTICATE = "/authenticate";

    public static final String COLLECTION_ENTRY = "/collectionEntry";
    public static final String COLLECTION_ENTRY_TYPE = "/type";
    public static final String COLLECTION_ENTRY_GENRE = "/genre";



    public static final String COLLECTION_ENTRY_ID_ACCEPT = ApiEndpoints.PATH_PARAM_ID + ApiEndpoints.ACCEPT;
    public static final String COLLECTION_ENTRY_ID_REJECT = ApiEndpoints.PATH_PARAM_ID + ApiEndpoints.REJECT;
    public static final String COLLECTION_ENTRY_ID_ASSING_GENRE = ApiEndpoints.PATH_PARAM_ID + "/assignGenre" + ApiEndpoints.PATH_PARAM_ABBR;
    public static final String COLLECTION_ENTRY_ID_UNASSING_GENRE = ApiEndpoints.PATH_PARAM_ID + "/unassignGenre" + ApiEndpoints.PATH_PARAM_ABBR;


    public static final String COLLECTION_TYPE = "/collection_type";
    public static final String COLLECTION_LIBRARY = "/collection_library";

    public static final String COLLECTION_LIBRARY_RENT = "/rent";
    public static final String COLLECTION_LIBRARY_RETURN = "/return";

    public static final String GENRE = "/genre";
    public static final String AUTHOR = "/author";
    public static final String PUBLISHER = "/publisher";

    public static final String USER = "/user";
    public static final String USER_SET_ROLE = ApiEndpoints.PATH_PARAM_ID + "/setRole" + ApiEndpoints.PATH_PARAM_ABBR;

    public static final String ROLE = "/role";
    public static final String MESSAGE = "/message";
    public static final String SUGGEST = "/suggest";
    public static final String SUGGESTION = "/suggestion";


    public static final String PARAM_LIMIT = "limit";
    public static final String PARAM_OFFSET = "offset";
    public static final String PARAM_SEARCH = "search";
    public static final String PARAM_ID = "id";
    public static final String PARAM_ABBR = "abbr";

}
