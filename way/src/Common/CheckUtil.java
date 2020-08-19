package Common;

import java.rmi.ServerException;

/**
 * @author
 * @date 2020/8/17
 **/
public class CheckUtil {
    public static void checkIsEqual(String params, Boolean targetBoo, Boolean retBoo) throws ServerException {
        if (!targetBoo.equals(retBoo)) {
            throw new ServerException("答案不正确！参数为：" + params);
        }
    }
}
