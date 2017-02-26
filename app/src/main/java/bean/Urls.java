package bean;

public class Urls {

	/** 主页接口 */
	private static String ulrString = "http://www.eduapk.cn";
	/** 每页的显示条数 */
	public static int pageSize = 10;

	/*-------------------------------------------------------------------**/

	/** 广告 */
	private static String bannerString = "/InterfaceForAndr/AdvertisementNew/?count=3";
	/** 获得广告接口标示 */
	public final static int bannerType = 0;

	/** 精品 */
	private static String extractString = "/InterfaceForAndr/ExtractAPP/";
	/** 获得精品接口标示 */
	public final static int extractType = 1;

	/** 排行 */
	private static String rankString = "/InterfaceForAndr/RankApp/";
	/** 获得排行接口标示 */
	public final static int rankType = 2;

	/** 搜索 */
	private static String searchString = "/InterfaceForAndr/SelectAppByKey/";
	/** 获得搜索接口标示 */
	public final static int searchType = 3;

	/** 分类 */
	private static String classString = "/InterfaceForAndr/SelectGradeAndClassify/";
	/** 获得分类接口标示 */
	public final static int classType = 4;

	/** 获取分类软件 （gradeId=1,classifyId=2） */
	private static String getbyclassfityString = "/InterfaceForAndr/SelectAPPByGradeAndClassify/";
	/** 获取分类软件接口标示 */
	public final static int getbyclaafiyType = 5;

	/** 获取软件详情 */
	private static String getappdetailsString = "/InterfaceForAndr/InforForApp/";
	/** 软件详情接口标示 */
	public final static int getappdetailsType = 6;

	/** 获取软件评论 */
	private static String getappcommentString = "/InterfaceForAndr/UserCommentInterfaces/";
	/** 软件评论接口标示 */
	public final static int getappcommentType = 7;

	/** 获取同开发者应用 */
	private static String getappsameString = "/InterfaceForAndr/SameDevelopApp/";
	/** 软件同开发者应用标示 */
	public final static int getappsameType = 8;

	public Urls() {

	}

	/**
	 * 得到网络接口
	 *
	 * @param type
	 * @param pageIndex
	 * @return
	 */
	public static String getUlrString(int type, int pageIndex, String keyString) {

		StringBuffer sBuffer = new StringBuffer();
		switch (type) {

			case bannerType:

				sBuffer.append(ulrString).append(bannerString);
				break;
			case extractType:

				sBuffer.append(ulrString).append(extractString)
						.append("?pageIndex=").append(pageIndex)
						.append("&pageSize=").append(pageSize);
				break;
			case rankType:

				sBuffer.append(ulrString).append(rankString).append("?pageIndex=")
						.append(pageIndex).append("&pageSize=").append(pageSize);
				break;
			case searchType:

				sBuffer.append(ulrString).append(searchString)
						.append("?pageIndex=").append(pageIndex)
						.append("&pageSize=").append(pageSize)
						.append("&searchKey=").append(keyString);
				break;
			case classType:

				sBuffer.append(ulrString).append(classString);
				break;
			case getbyclaafiyType:

				sBuffer.append(ulrString).append(getbyclassfityString)
						.append("?pageIndex=").append(pageIndex)
						.append("&pageSize=").append(pageSize);
				break;
			case getappdetailsType:

				sBuffer.append(ulrString).append(getappdetailsString)
						.append("?sameAppCount=").append(pageSize);
				break;
			case getappcommentType:

				sBuffer.append(ulrString).append(getappcommentString)
						.append("?commentCount=").append(pageSize);
				break;
			case getappsameType:

				sBuffer.append(ulrString).append(getappsameString)
						.append("?pageIndex=").append(pageIndex)
						.append("&pageSize=").append(pageSize);
				break;
			default:
				break;
		}

		return sBuffer.toString();
	}
}
