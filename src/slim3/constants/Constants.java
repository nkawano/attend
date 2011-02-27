package slim3.constants;

public class Constants {

    //団員権限セッション格納キー
    public static String SESSION_KEY_LOGIN_USER = "loginUser";

    //団員権限セッション格納キー
    public static String SESSION_KEY_LOGIN_USER_AUTH = "loginUserAuth";

    //団員権限セッションマップ格納キー 出席情報
    public static String MAP_KEY_ATTENDANCE = "attendance";

    //団員権限セッションマップ格納キー 団員情報
    public static String MAP_KEY_MEMBER = "member";

    //団員権限セッションマップ格納キー 練習日情報
    public static String MAP_KEY_PRACTICE = "practice";

    //団員権限セッションマップ格納キー 団員権限情報
    public static String MAP_KEY_MEMBERAUTH = "memberAuth";

    //団員権限 なし
    public static int AUTH_NOTHING = 0;

    //団員権限 参照可
    public static int AUTH_REFER = 1;

    //団員権限 更新可
    public static int AUTH_UPDATE = 2;
}
